package com.example.chatapplication.adapters;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chatapplication.activities.ChatGroupActivity;
import com.example.chatapplication.databinding.ItemContainerRecentGroupBinding;
import com.example.chatapplication.databinding.ItemContainerUserRequestBinding;
import com.example.chatapplication.listeners.GroupListener;
import com.example.chatapplication.listeners.UserListener;
import com.example.chatapplication.models.Group;
import com.example.chatapplication.models.User;
import com.example.chatapplication.utilities.Constants;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.HashMap;
import java.util.List;

public class GroupAdapters extends RecyclerView.Adapter<GroupAdapters.UserViewHolder>{

    private final List<Group> groupsList;
    private final GroupListener groupListener;
    private String currentUserId;

    public GroupAdapters(List<Group> usersList, GroupListener groupListener, String currentUserId) {
        this.groupsList = usersList;
        this.groupListener = groupListener;
        this.currentUserId = currentUserId;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemContainerRecentGroupBinding itemContainerUserBinding = ItemContainerRecentGroupBinding.inflate(
            LayoutInflater.from(parent.getContext()), parent, false
        );

        return new UserViewHolder(itemContainerUserBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        holder.setUserData(groupsList.get(position), currentUserId);
    }

    @Override
    public int getItemCount() {
        return groupsList.size();
    }

    class UserViewHolder extends RecyclerView.ViewHolder {
        ItemContainerRecentGroupBinding binding;

        UserViewHolder(ItemContainerRecentGroupBinding itemContainerUserBinding) {
            super(itemContainerUserBinding.getRoot());
            binding = itemContainerUserBinding;
        }

        void setUserData(Group group, String currentUserId) {
            binding.textName.setText(group.name);
            binding.imageProfile1.setImageBitmap(getGroupImage(group.image1));

//            binding.btnAdd.setOnClickListener(v -> {
//                FirebaseFirestore database = FirebaseFirestore.getInstance();
//
//                HashMap<String, Object> userFriend = new HashMap<>();
//                userFriend.put(Constants.KEY_USER_ID, user.id);
//                userFriend.put(Constants.KEY_NAME, user.name);
//                userFriend.put(Constants.KEY_IMAGE, user.image);
//                userFriend.put(Constants.KEY_EMAIL, user.email);
//
//                database.collection(Constants.KEY_COLLECTION_USERS).document(currentUserId).collection(Constants.KEY_COLLECTION_REQUEST_FRIENDS).whereEqualTo(Constants.KEY_USER_ID, user.id).get().addOnCompleteListener(task -> {
//                    if (task.isSuccessful() && task.getResult() != null) {
//                        for (QueryDocumentSnapshot queryDocumentSnapshot : task.getResult()) {
//                            deleteRequest(queryDocumentSnapshot.getId());
//                        }
//                    }
//                });
//
//                database.collection(Constants.KEY_COLLECTION_USERS).document(user.id).collection(Constants.KEY_COLLECTION_WAIT_FRIENDS).whereEqualTo(Constants.KEY_USER_ID, currentUserId).get().addOnCompleteListener(task -> {
//                    if (task.isSuccessful() && task.getResult() != null) {
//                        for (QueryDocumentSnapshot queryDocumentSnapshot : task.getResult()) {
//                            hashMyUser(user.id, queryDocumentSnapshot.getString(Constants.KEY_NAME), queryDocumentSnapshot.getString(Constants.KEY_IMAGE), queryDocumentSnapshot.getString(Constants.KEY_EMAIL));
//                            deleteWait(user.id, queryDocumentSnapshot.getId());
//                        }
//                    }
//                });
//
////
//                database.collection(Constants.KEY_COLLECTION_USERS).document(currentUserId).collection(Constants.KEY_COLLECTION_FRIENDS).add(userFriend).addOnSuccessListener(documentReference -> {
//                    showToast("Bạn vừa có thêm một người bạn mới ^^");
//                }).addOnFailureListener(exception -> {
//                    showToast(exception.getMessage());
//                });
//
//                binding.getRoot().setVisibility(View.GONE);
//            });

            binding.getRoot().setOnClickListener(v -> groupListener.onGroupClicked(group));


        }

        private void showToast(String message) {
            Toast.makeText(binding.getRoot().getContext(), message, Toast.LENGTH_SHORT).show();
        }

        private void deleteRequest(String string) {
            FirebaseFirestore database = FirebaseFirestore.getInstance();
            database.collection(Constants.KEY_COLLECTION_USERS).document(currentUserId).collection(Constants.KEY_COLLECTION_REQUEST_FRIENDS).document(string).delete().addOnSuccessListener(documentReference -> {
            }).addOnFailureListener(exception -> {
                showToast(exception.getMessage());
            });
        }

        private void deleteWait(String id, String string) {
            FirebaseFirestore database = FirebaseFirestore.getInstance();
            database.collection(Constants.KEY_COLLECTION_USERS).document(id).collection(Constants.KEY_COLLECTION_WAIT_FRIENDS).document(string).delete().addOnSuccessListener(documentReference -> {
            }).addOnFailureListener(exception -> {
                showToast(exception.getMessage());
            });
        }

        private void hashMyUser(String id, String name, String image, String email) {
            HashMap<String, Object> myUser = new HashMap<>();
            myUser.put(Constants.KEY_USER_ID, currentUserId);
            myUser.put(Constants.KEY_NAME, name);
            myUser.put(Constants.KEY_IMAGE, image);
            myUser.put(Constants.KEY_EMAIL, email);

            FirebaseFirestore database = FirebaseFirestore.getInstance();
            database.collection(Constants.KEY_COLLECTION_USERS).document(id).collection(Constants.KEY_COLLECTION_FRIENDS).add(myUser).addOnSuccessListener(documentReference -> {
            }).addOnFailureListener(exception -> {
                showToast(exception.getMessage());
            });

        }

    }

    private Bitmap getGroupImage(String encodedImage) {
        byte[] bytes = Base64.decode(encodedImage, Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
    }

}
