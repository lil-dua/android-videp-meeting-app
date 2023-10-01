package com.example.chatapplication.fragments.userfrag;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Base64;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.example.chatapplication.R;
import com.example.chatapplication.activities.ChangeInfoUserActivity;
import com.example.chatapplication.activities.ProfileActivity;
import com.example.chatapplication.activities.SignInActivity;
import com.example.chatapplication.databinding.FragmentUserBinding;
import com.example.chatapplication.models.User;
import com.example.chatapplication.utilities.Constants;
import com.example.chatapplication.utilities.PreferenceManager;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;

public class UserFragment extends Fragment {

    private FragmentUserBinding binding;
    private PreferenceManager preferenceManager;
    private User user;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentUserBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        preferenceManager = new PreferenceManager(getActivity().getApplicationContext());

        user = new User();
        user.image = preferenceManager.getString(Constants.KEY_IMAGE);
        user.name = preferenceManager.getString(Constants.KEY_NAME);
        user.email = preferenceManager.getString(Constants.KEY_EMAIL);

        loadUserDetails();
        setListeners();
    }

    private void setListeners() {
        binding.imageSignOut.setOnClickListener(v -> signOut());
        binding.textLogout.setOnClickListener(v -> signOut());
        binding.userProfile.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity().getApplicationContext(), ProfileActivity.class);
            intent.putExtra("user", user);
            intent.putExtra("you", "1");
            intent.putExtra("myID", "myID");
            intent.putExtra("myName", "myName");
            intent.putExtra("myImage", "myImage");
            startActivity(intent);
        });

        binding.changeInfoUser.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity().getApplicationContext(), ChangeInfoUserActivity.class);
            intent.putExtra("user", user);
            startActivity(intent);
        });
    }

    private void loadUserDetails() {
        binding.textName.setText(preferenceManager.getString(Constants.KEY_NAME));
        byte[] bytes = Base64.decode(preferenceManager.getString(Constants.KEY_IMAGE), Base64.DEFAULT);
        Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
        binding.imageProfile.setImageBitmap(bitmap);
    }

    private void showToast(String message) {
        Toast.makeText(getActivity().getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

    private void signOut() {
        Dialog dialog = new Dialog(binding.getRoot().getContext());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_out_account);

        Window window = dialog.getWindow();
        if (window == null) {
            return;
        }

        window.setLayout(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        WindowManager.LayoutParams w = window.getAttributes();
        w.gravity = Gravity.CENTER;
        window.setAttributes(w);

        if (Gravity.BOTTOM == Gravity.CENTER) {
            dialog.setCancelable(true);
        } else {
            dialog.setCancelable(false);
        }

        Button no = dialog.findViewById(R.id.btn_no);
        Button yes = dialog.findViewById(R.id.btn_yes);

        no.setOnClickListener(v -> {
            dialog.dismiss();
        });

        yes.setOnClickListener(v -> {
            showToast("Đăng xuất khỏi tài khoản...");
            FirebaseFirestore database = FirebaseFirestore.getInstance();
            DocumentReference documentReference = database.collection(Constants.KEY_COLLECTION_USERS).document(
                    preferenceManager.getString(Constants.KEY_USER_ID)
            );

            HashMap<String, Object> updates = new HashMap<>();
            updates.put(Constants.KEY_FCM_TOKEN, FieldValue.delete());
            documentReference.update(updates).addOnSuccessListener(unused -> {

                preferenceManager.clear();
                startActivity(new Intent(getActivity().getApplicationContext(), SignInActivity.class));
                getActivity().finish();
            }).addOnFailureListener(e -> showToast("Đăng xuất thất bại, hãy xóa dữ liệu ứng dụng và thử lại!"));

        });

        dialog.show();

//        showToast("Đăng xuất khỏi tài khoản...");
//        FirebaseFirestore database = FirebaseFirestore.getInstance();
//        DocumentReference documentReference = database.collection(Constants.KEY_COLLECTION_USERS).document(
//                preferenceManager.getString(Constants.KEY_USER_ID)
//        );
//
//        HashMap<String, Object> updates = new HashMap<>();
//        updates.put(Constants.KEY_FCM_TOKEN, FieldValue.delete());
//        documentReference.update(updates).addOnSuccessListener(unused -> {
//
//            preferenceManager.clear();
//            startActivity(new Intent(getActivity().getApplicationContext(), SignInActivity.class));
//            getActivity().finish();
//        }).addOnFailureListener(e -> showToast("Đăng xuất thất bại, hãy xóa dữ liệu ứng dụng và thử lại!"));
//
    }

}