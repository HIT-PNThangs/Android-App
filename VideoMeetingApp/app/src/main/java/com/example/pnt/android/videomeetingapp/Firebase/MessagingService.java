package com.example.pnt.android.videomeetingapp.Firebase;

import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.example.pnt.android.videomeetingapp.Activity.IncomingInvitationActivity;
import com.example.pnt.android.videomeetingapp.Utilities.Constants;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MessagingService extends FirebaseMessagingService {
    @Override
    public void onNewToken(@NonNull String token) {
        super.onNewToken(token);
    }

    @Override
    public void onMessageReceived(@NonNull RemoteMessage message) {
        super.onMessageReceived(message);

        String type = message.getData().get(Constants.REMOTE_MSG_TYPE);

        if (type != null) {
            if (type.equals(Constants.REMOTE_MSG_INVITATION)) {
                Intent intent = new Intent(getApplicationContext(), IncomingInvitationActivity.class);
                intent.putExtra(
                        Constants.REMOTE_MSG_MEETING_TYPE,
                        message.getData().get(Constants.REMOTE_MSG_MEETING_TYPE)
                );

                intent.putExtra(
                        Constants.KEY_FIRST_NAME,
                        message.getData().get(Constants.KEY_FIRST_NAME)
                );

                intent.putExtra(
                        Constants.KEY_LAST_NAME,
                        message.getData().get(Constants.KEY_LAST_NAME)
                );

                intent.putExtra(
                        Constants.KEY_EMAIL,
                        message.getData().get(Constants.KEY_EMAIL)
                );

                intent.putExtra(
                        Constants.REMOTE_MSG_INVITER_TOKEN,
                        message.getData().get(Constants.REMOTE_MSG_INVITER_TOKEN)
                );

                intent.putExtra(
                        Constants.REMOTE_MSG_INVITER_TOKEN,
                        message.getData().get(Constants.REMOTE_MSG_INVITER_TOKEN)
                );

                intent.putExtra(
                        Constants.REMOTE_MSG_MEETING_ROOM,
                        message.getData().get(Constants.REMOTE_MSG_MEETING_ROOM)
                );

                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            } else if (type.equals(Constants.REMOTE_MSG_INVITATION_RESPONSE)) {
                Intent intent = new Intent(Constants.REMOTE_MSG_INVITATION_RESPONSE);

                intent.putExtra(
                        Constants.REMOTE_MSG_INVITATION_RESPONSE,
                        message.getData().get(Constants.REMOTE_MSG_INVITATION_REJECTED)
                );

                LocalBroadcastManager.getInstance(getApplicationContext()).sendBroadcast(intent);
            }
        }
    }
}
