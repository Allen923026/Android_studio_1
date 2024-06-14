package COM.TQC.GDD01;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.widget.RemoteViews;

import androidx.core.app.NotificationCompat;

import java.nio.channels.InterruptedByTimeoutException;

public class    NotificationHelper extends ContextWrapper
{
    private NotificationManager manager;
    public static final String PRIMARY_CHANNEL = "default";
    public static final String SECONDARY_CHANNEL = "second";

    public NotificationHelper(Context ctx)
    {
        super(ctx);

        NotificationChannel channelA = null;
        // TO DO
        channelA = new NotificationChannel(PRIMARY_CHANNEL,
                getString(R.string.str_primary_notification),
                NotificationManager.IMPORTANCE_DEFAULT);
        getManager().createNotificationChannel(channelA);

        NotificationChannel channelB = null;
        // TO DO

        channelB = new NotificationChannel(SECONDARY_CHANNEL,
                getString(R.string.str_secondary_notification),
                NotificationManager.IMPORTANCE_DEFAULT);
        getManager().createNotificationChannel(channelB);
    }

    public Notification getNotification1(String title, String body)
    {
        Intent intent = new Intent(this, MyMsgHandler.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        // TO DO
        Bundle bundle = new Bundle();
        bundle.putString(Constants.EXTRA_NOTIFICATION_MSG,body);
        intent.putExtras(bundle);

        PendingIntent pendingintent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        Bitmap icon = BitmapFactory.decodeResource(getResources(), R.drawable.notificationbg);

        Notification notification = null;
        // TO DO
        NotificationCompat.BigPictureStyle bigpic =
                new NotificationCompat.BigPictureStyle().bigPicture(icon);

        notification = new NotificationCompat.Builder(getApplicationContext(),PRIMARY_CHANNEL)
                .setSmallIcon(R.drawable.sms)
                .setContentTitle(title)
                .setContentText(body)
                .setLargeIcon(icon)
                .setStyle(bigpic)
                .setContentIntent(pendingintent)
                .setAutoCancel(true)
                .build();

        return notification;
    }

    public Notification getNotification2(String title, String body, String strUri)
    {
        Intent intent = new Intent(this, MyMsgHandler.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        // TO DO
        Bundle bundle = new Bundle();
        bundle.putString(Constants.EXTRA_NOTIFICATION_MSG,body);
        intent.putExtras(bundle);

        PendingIntent pendingintent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        // TO DO
        RemoteViews expandedView = new RemoteViews(getPackageName(), R.layout.notification_expanded);
        // TO DO
        String curTime = DateUtils.formatDateTime(this,System.currentTimeMillis(),
                DateUtils.FORMAT_ABBREV_TIME);
        expandedView.setTextViewText(R.id.timestamp,curTime);
        expandedView.setTextViewText(R.id.notification_message,body);

        Intent leftIntent = new Intent(this, NotificationIntentService.class);
        // TO DO
        leftIntent.setAction(Constants.EXTRA_REMOTE_LEFT_BUTTON);
        leftIntent.putExtras(bundle);
        PendingIntent pendingintent1 = PendingIntent.getService(this,
                100,leftIntent,PendingIntent.FLAG_CANCEL_CURRENT);
        expandedView.setOnClickPendingIntent(R.id.notification_collapsed_left_button,pendingintent1);

        Intent rightIntent = new Intent(this, NotificationIntentService.class);
        // TO DO
        rightIntent.setAction(Constants.EXTRA_REMOTE_RIGHT_BUTTON);
        bundle.putString(Constants.EXTRA_NOTIFICATION_URI,strUri);
        rightIntent.putExtras(bundle);
        PendingIntent pendingIntent2 = PendingIntent.getService(this,
                200,rightIntent,PendingIntent.FLAG_UPDATE_CURRENT);
        expandedView.setOnClickPendingIntent(R.id.notification_collapsed_right_button,pendingIntent2);

        RemoteViews collapsedView = new RemoteViews(getPackageName(), R.layout.notification_collapsed);
        // TO DO
        collapsedView.setTextViewText(R.id.timestamp,curTime);

        Notification notification = new NotificationCompat.Builder(getApplicationContext(), PRIMARY_CHANNEL)
                .setSmallIcon(R.drawable.sms)
                .setContentTitle(title)
                .setContentText(body)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setCustomContentView(collapsedView)
                .setCustomBigContentView(expandedView)
                .setStyle(new NotificationCompat.DecoratedCustomViewStyle())
                .setContentIntent(pendingintent)
                .setAutoCancel(true)
                .build();
        return notification;
    }

    public void notify(int id, Notification notification)
    {
        getManager().notify(id, notification);
    }

    private NotificationManager getManager()
    {
        if (manager == null)
        {
            manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        }
        return manager;
    }
}
