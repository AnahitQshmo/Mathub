package com.example.arajin.mathub;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.Objects;

public class ProfileFragment extends Fragment {

    LinkedList<String> polytech = new LinkedList<>();
    LinkedList<String> physmath = new LinkedList<>();
    LinkedList<String> quantum = new LinkedList<>();
    LinkedList<String> ayb = new LinkedList<>();
    View view;
    TextView register;
    TextView gmailTextView;
    TextView passwordTextView;
    Button logOut;
    Button addTask;
    TextView logIn;
    Button creater;
    ImageView imageView;

    ActivityResultLauncher<Intent> resultLauncher;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_profile2, container, false);

        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();

        if (currentUser == null) {
            view = inflater.inflate(R.layout.fragment_profile, container, false);
            setupLoginUI();
        } else {
            view = inflater.inflate(R.layout.fragment_profile2, container, false);
            setupLoggedInUI();
            getProfileImageUri();
        }
        return view;
    }

    private void getProfileImageUri() {
        creater = view.findViewById(R.id.creater);
        imageView = view.findViewById(R.id.imageView);
        imageView.setDrawingCacheEnabled(true);
        imageView.buildDrawingCache();
        registerResult();
        creater.setOnClickListener(view -> pickImage());

        String userId = Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid();
        StorageReference storageReference = FirebaseStorage.getInstance().getReference();
        StorageReference profileImageRef = storageReference.child("profile_images/").child(userId);

        profileImageRef.getDownloadUrl().addOnSuccessListener(uri -> {
            Glide.with(requireContext())
                    .asBitmap()
                    .load(uri)
                    .into(new CustomTarget<Bitmap>() {
                        @Override
                        public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {

                            Bitmap circularBitmap = getCircularBitmap(resource);
                            imageView.setImageBitmap(circularBitmap);
                        }

                        @Override
                        public void onLoadCleared(@Nullable Drawable placeholder) {

                        }
                    });
        }).addOnFailureListener(exception -> {

        });

        imageView.setDrawingCacheEnabled(false);

    }


    public void pickImage(){
        Intent intent = new Intent(MediaStore.ACTION_PICK_IMAGES);
        resultLauncher.launch(intent);
    }
    public void registerResult(){
        resultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        try {
                            if (result != null && result.getData() != null && result.getData().getData() != null) {
                                Uri imageUri = result.getData().getData();
                                Bitmap bitmap = getBitmapFromUri(getContext(), imageUri);

                                if (bitmap != null) {
                                    Bitmap circularBitmap = getCircularBitmap(bitmap);
                                    imageView.setImageBitmap(circularBitmap);

                                    saveProfileImageToFirebase(imageUri);
                                } else {
                                    Toast.makeText(getContext(), "Failed to load image. Please try again.", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                Toast.makeText(getContext(), "No image selected", Toast.LENGTH_LONG).show();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
        );
    }

    private void saveProfileImageToFirebase(Uri imageUri) {
        FirebaseStorage firebaseStorage = FirebaseStorage.getInstance();
        StorageReference storageReference = firebaseStorage.getReference();

        String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        StorageReference profileImageRef = storageReference.child("profile_images/" + userId);


        profileImageRef.putFile(imageUri)
                .addOnSuccessListener(taskSnapshot -> {
                    profileImageRef.getDownloadUrl().addOnSuccessListener(uri -> {
                        saveImageUrlToDatabase(uri.toString());
                    });
                })
                .addOnFailureListener(e -> {
                });
    }

    private void saveImageUrlToDatabase(String imageUrl) {
        String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        DatabaseReference userRef = FirebaseDatabase.getInstance().getReference().child("users").child(userId);
        userRef.child("profileImageUrl").setValue(imageUrl)
                .addOnSuccessListener(aVoid -> {
               })
                .addOnFailureListener(e -> {
                });
    }

    private Bitmap getBitmapFromUri(Context context, Uri uri) {
        try {
            InputStream inputStream = context.getContentResolver().openInputStream(uri);
            if (inputStream != null) {
                return BitmapFactory.decodeStream(inputStream);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }


    private Bitmap getCircularBitmap(Bitmap srcBitmap) {
        int width = srcBitmap.getWidth();
        int height = srcBitmap.getHeight();
        Bitmap circularBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(circularBitmap);

        Paint paint = new Paint();
        paint.setAntiAlias(true);
        canvas.drawCircle(width / 2f, height / 2f, Math.min(width, height) / 2f, paint);

        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(srcBitmap, 0, 0, paint);

        srcBitmap.recycle();
        return circularBitmap;
    }

    private void setupLoginUI() {
        gmailTextView = view.findViewById(R.id.text5);
        passwordTextView = view.findViewById(R.id.text7);
        register = view.findViewById(R.id.textSign);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Register.class);
                Bundle bundle2 = new Bundle();
                bundle2.putString("counter", "2");
                intent.putExtras(bundle2);
                startActivity(intent);
            }
        });

        logIn = view.findViewById(R.id.click);
        logIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginUser();
            }
        });
    }

    private void setupLoggedInUI() {
        logOut = view.findViewById(R.id.log_out);
        addTask = view.findViewById(R.id.add_tasks);

        logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(getActivity(), MainActivity.class));
            }
        });

        addTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AddTaskPoster.class);
                startActivity(intent);
            }
        });
        showUserData();
        showAddTaskButton();
    }

    private void showUserData() {
        TextView userName = view.findViewById(R.id.name_surname);
        TextView score = view.findViewById(R.id.score);

        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
        FirebaseUser currentUser = mAuth.getCurrentUser();

        if (currentUser != null) {
            String userPath = "users/" + currentUser.getUid();
            mDatabase.child(userPath).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()) {
                        String scoreData = dataSnapshot.child("score").getValue(String.class);
                        score.setText(getResources().getString(R.string.score) + scoreData);
                        String userNameData = dataSnapshot.child("name").getValue(String.class);
                        userName.setText(userNameData);
                        String testsData = dataSnapshot.child("test").getValue(String.class);
                        String[] passedTests = testsData.split("/]");
                        for(int i = 0; i < passedTests.length;i++){
                            String[] pair = passedTests[i].split("<,>");
                            if(pair[0].equals("Polytech") || pair[0].equals("WPolytech")){
                                polytech.add(getResources().getString(R.string.test) + pair[1]);
                            }
                            if(pair[0].equals("Physmath") || pair[0].equals("WPhysmath")){
                                physmath.add(getResources().getString(R.string.test) + pair[1]);
                            }
                            if(pair[0].equals("Quantum") || pair[0].equals("WQuantum")){
                                quantum.add(getResources().getString(R.string.test) + pair[1]);
                            }
                            if(pair[0].equals("Ayb") || pair[0].equals("WAyb")){
                                ayb.add(getResources().getString(R.string.test) + pair[1]);
                            }

                        }
                        createListView(R.id.polytech_lv,polytech.toArray(new String[polytech.size()]));
                        createListView(R.id.physmath_lv,physmath.toArray(new String[physmath.size()]));
                        createListView(R.id.quantum_lv,quantum.toArray(new String[quantum.size()]));
                        createListView(R.id.ayb_lv,ayb.toArray(new String[ayb.size()]));
                    }
                }
                @Override
                public void onCancelled(DatabaseError databaseError) {
                }
            });
        }
    }
    private void createListView(int id,String[] arr) {
        if(arr != null && arr.length>0){
            ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, arr);
            ListView listView = view.findViewById(id);
            listView.setAdapter(adapter);
        }else{
            String[] newArr = {getResources().getString(R.string.just)};
            ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, newArr);
            ListView listView = view.findViewById(id);
            listView.setAdapter(adapter);
        }
    }


    private void loginUser() {
        String email = gmailTextView.getText().toString().trim();
        String password = passwordTextView.getText().toString().trim();

        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(getContext(), "Please fill in all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(getActivity(), task -> {
                    if (task.isSuccessful()) {
                        Toast.makeText(getContext(), "Login successful", Toast.LENGTH_SHORT).show();
                        getActivity().getSupportFragmentManager().beginTransaction().detach(ProfileFragment.this).attach(ProfileFragment.this).commit();
                    } else {
                        Toast.makeText(getContext(), "Login failed: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void showAddTaskButton() {
        String userEmail = FirebaseAuth.getInstance().getCurrentUser().getEmail();
        if (userEmail != null && userEmail.equals("anahitqishmiryan49@gmail.com")) {
            addTask.setVisibility(View.VISIBLE);
        } else {
            addTask.setVisibility(View.GONE);
        }
    }
}


