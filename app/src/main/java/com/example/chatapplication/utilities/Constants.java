package com.example.chatapplication.utilities;

import java.util.HashMap;

public class Constants {
    public static final String KEY_PREFERENCE_NAME = "chatAPPPreference";
    public static final String KEY_IS_SIGN_IN = "isSignIn";

    public static final String KEY_COLLECTION_USERS = "users";
    public static final String KEY_USER = "user";
    public static final String KEY_USER_ID = "userID";
    public static final String KEY_NAME = "name";
    public static final String KEY_EMAIL = "email";
    public static final String KEY_PASSWORD = "password";
    public static final String KEY_FCM_TOKEN = "fcmToken";
    public static final String KEY_AVAILABLILITY = "availability";
    public static final String KEY_IMAGE = "image";
    public static final String KEY_IMAGE_BACKGROUND = "imageBackground";
    public static final String KEY_NUMBER = "number";
    public static final String KEY_BIRTHDAY = "birthday";
    public static final String KEY_GENDER = "gender";
    public static final String KEY_INTRODUCE_YOURSEFT = "introduceYourself";

    public static final String KEY_COLLECTION_CHAT = "chat";
    public static final String KEY_SENDER_ID = "senderId";
    public static final String KEY_RECEIVER_ID = "receiverId";
    public static final String KEY_MESSAGE = "message";
    public static final String KEY_TIMESTAMP = "timestamp";

    public static final String KEY_COLLECTION_CONVERSATIONS = "conversations";
    public static final String KEY_SENDER_NAME = "senderName";
    public static final String KEY_RECEIVER_NAME = "receiverName";
    public static final String KEY_SENDER_IMAGE = "senderImage";
    public static final String KEY_RECEIVER_IMAGE = "receiverImage";
    public static final String KEY_LAST_MESSAGE = "lastMessage";
    public static final String KEY_LAST_USER = "lastUser";
    public static final String KEY_LAST_READ = "lastRead";

    public static final String KEY_COLLECTION_GROUPS = "groups";
    public static final String KEY_GROUP_ID = "groupID";
    public static final String KEY_GROUP_IMAGE_1 = "groupImage1";
    public static final String KEY_GROUP_IMAGE_2 = "groupImage2";
    public static final String KEY_GROUP = "group";
    public static final String KEY_GROUP_ADMIN_ID = "adminID";
    public static final String KEY_GROUP_NAME = "groupName";
    public static final String KEY_COLLECTION_GROUP_MEMBERS = "members";
    public static final String KEY_COLLECTION_GROUP_MESSAGES = "messages";

    public static final String KEY_COLLECTION_FRIENDS = "friends";
    public static final String KEY_COLLECTION_WAIT_FRIENDS = "wait_friends";
    public static final String KEY_COLLECTION_REQUEST_FRIENDS = "request_friends";

    public static final String KEY_COLLECTION_POSTS = "posts";
    public static final String KEY_POST_ID_AUTHOR = "idAuthor";
    public static final String KEY_POST_NAME_AUTHOR = "nameAuthor";
    public static final String KEY_POST_IMAGE_AUTHOR = "imageAuthor";
    public static final String KEY_POST_EMAIL_AUTHOR = "emailAuthor";
    public static final String KEY_POST_TIMESTAMP = "postTimestamp";
    public static final String KEY_POST_TEXT = "postText";
    public static final String KEY_POST_IMAGE = "postImage";

    public static final String REMOTE_MSG_AUTHORIZATION = "Authorization";
    public static final String REMOTE_MSG_CONTENT_TYPE = "Content-Type";
    public static final String REMOTE_MSG_DATA = "data";
    public static final String REMOTE_MSG_REGISTRATION_IDS = "registration_ids";

    public static final String REMOTE_MSG_CALLING_AUTHORIZATION = "Authorization";
    public static final String REMOTE_MSG_CALLING_CONTENT_TYPE = "Content-Type";
    public static final String REMOTE_MSG_CALLING_TYPE = "type";
    public static final String REMOTE_MSG_CALLING_INVITATION = "invitation";
    public static final String REMOTE_MSG_CALLING_MEETING_TYPE = "meetingType";
    public static final String REMOTE_MSG_CALLING_TOKEN = "callingToken";
    public static final String REMOTE_MSG_CALLING_DATA = "data";
    public static final String REMOTE_MSG_CALLING_REGISTRATION_IDS = "registration_ids";

    public static final String REMOTE_MSG_CALLING_INVITATION_RESPONSE = "invitationResponse";
    public static final String REMOTE_MSG_CALLING_INVITATION_ACCEPTED = "accepted";
    public static final String REMOTE_MSG_CALLING_INVITATION_REJECTED = "rejected";
    public static final String REMOTE_MSG_CALLING_INVITATION_CANCELLED = "cancelled";

    public static final String REMOTE_MSG_CALLING_ROOM = "callingRoom";

    public static HashMap<String, String> remoteMegHeaders = null;
    public static HashMap<String, String> getRemoteMsgHeaders() {
        if (remoteMegHeaders == null) {
            remoteMegHeaders = new HashMap<>();
            remoteMegHeaders.put(REMOTE_MSG_AUTHORIZATION, "key=AAAATEW2FzM:APA91bH3N0gwCPPxy8sIDIktMmkvg-7DAbpT4_i_qYOBBw8PwMOTfga4M9c_s4bWsdgIXLmdUbGDVw1VNAPXMVevDDeF5vcwPaaRArhSvsecll-QKNfm5Jx0WzMYzQ5r3aec7aZXp6_l");
            remoteMegHeaders.put(REMOTE_MSG_CONTENT_TYPE, "application/json");
        }
        return remoteMegHeaders;
    }

    public static HashMap<String, String> getRemoteCallingHeaders() {
        HashMap<String, String> remoteCallingHeaders = new HashMap<>();
        remoteCallingHeaders.put(Constants.REMOTE_MSG_CALLING_AUTHORIZATION, "key=AAAATEW2FzM:APA91bHAxjWOQKrl6KApij8IWGk55i3MlIHxA-8AI8RSOoDSt_3PL8K78eF2lNVz-aeThT_n_-WmLsLyMPnEnrK2S2MuV1MqnF8GlUgOA2c2jK-3KLCAiH9nS-MGg00aF2PuGSRxF6SP");
        remoteCallingHeaders.put(Constants.REMOTE_MSG_CALLING_CONTENT_TYPE, "application/json");

        return remoteCallingHeaders;
    }

}
