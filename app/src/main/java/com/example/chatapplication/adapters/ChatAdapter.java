package com.example.chatapplication.adapters;

import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chatapplication.databinding.ItemContainerReceivedMessageBinding;
import com.example.chatapplication.databinding.ItemContainerSentImageBinding;
import com.example.chatapplication.databinding.ItemContainerSentMessageBinding;
import com.example.chatapplication.models.ChatMessage;

import java.util.List;

public class ChatAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private final List<ChatMessage> chatMessages;
    private Bitmap receivierProfileImage;
    private final String senderId;

    public static final int VIEW_TYPE_SENT = 1;
    public static final int VIEW_TYPE_SENT_IMAGE = 3;
    public static final int VIEW_TYPE_RECEIVED = 2;

    public void setReceivierProfileImage(Bitmap bitmap) {
        receivierProfileImage = bitmap;
    }

    public ChatAdapter(List<ChatMessage> chatMessages, Bitmap receivierProfileImage, String senderId) {
        this.chatMessages = chatMessages;
        this.receivierProfileImage = receivierProfileImage;
        this.senderId = senderId;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_SENT) {
            return new SentMessageViewHolder(
                    ItemContainerSentMessageBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false)
            );
        } else if(viewType == VIEW_TYPE_SENT_IMAGE) {
            return new SentImageViewHolder(
                    ItemContainerSentImageBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false)
            );
        } else {
            return new ReceivedMessageViewHolder(
                    ItemContainerReceivedMessageBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false)
            );
        }

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if(getItemViewType(position) == VIEW_TYPE_SENT) {
            ((SentMessageViewHolder) holder).setData(chatMessages.get(position));
        } else if(getItemViewType(position) == VIEW_TYPE_SENT_IMAGE) {
            ((SentImageViewHolder) holder).setData(chatMessages.get(position), receivierProfileImage);
        } else {
            ((ReceivedMessageViewHolder) holder).setData(chatMessages.get(position), receivierProfileImage);
        }
    }

    @Override
    public int getItemCount() {
        return chatMessages.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (chatMessages.get(position).senderId.equals(senderId)) {
            if (chatMessages.get(position).image.equals("1")) {
                return VIEW_TYPE_SENT_IMAGE;
            } else {
                return VIEW_TYPE_SENT;
            }
        } else {
            return VIEW_TYPE_RECEIVED;
        }
    }

    static class SentMessageViewHolder extends RecyclerView.ViewHolder {
        private final ItemContainerSentMessageBinding binding;

        SentMessageViewHolder(ItemContainerSentMessageBinding itemContainerSentMessageBinding) {
            super(itemContainerSentMessageBinding.getRoot());
            binding = itemContainerSentMessageBinding;
        }

        void setData(ChatMessage chatMessage) {
            binding.textMessage.setText(chatMessage.messafe);
            binding.textDateTime.setText(chatMessage.dataTime);
        }
    }

    static class SentImageViewHolder extends RecyclerView.ViewHolder {
        private final ItemContainerSentImageBinding binding;

        SentImageViewHolder(ItemContainerSentImageBinding itemContainerSentImageBinding) {
            super(itemContainerSentImageBinding.getRoot());
            binding = itemContainerSentImageBinding;
        }

        void setData(ChatMessage chatMessage, Bitmap receivierProfileImage) {
            binding.textMessage.setImageBitmap(receivierProfileImage);
            binding.textDateTime.setText(chatMessage.dataTime);
        }
    }

    static class ReceivedMessageViewHolder extends RecyclerView.ViewHolder {

        private final ItemContainerReceivedMessageBinding binding;

        ReceivedMessageViewHolder(ItemContainerReceivedMessageBinding itemContainerReceivedMessageBinding) {
            super(itemContainerReceivedMessageBinding.getRoot());
            binding = itemContainerReceivedMessageBinding;
        }

        void setData(ChatMessage chatMessage, Bitmap receivierProfileImage) {
            binding.textMessage.setText(chatMessage.messafe);
            binding.textDateTime.setText(chatMessage.dataTime);
            if (receivierProfileImage != null) {
                binding.imageProfile.setImageBitmap(receivierProfileImage);
            }

        }
    }

}