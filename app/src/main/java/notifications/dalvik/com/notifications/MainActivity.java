package notifications.dalvik.com.notifications;


import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.provider.Settings;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private Button startNotification;
    private Button deletetNotification;
    private Button updateNotification;
    private Button startVibration;

    private NotificationManager mNotificationManager;
    private int notificationID = 100;
    private int numMessages = 0;

    //ImageView img;

    //private int contador = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        startNotification = (Button) findViewById(R.id.start);
        deletetNotification= (Button) findViewById(R.id.cancel);
        updateNotification = (Button) findViewById(R.id.update);
        startVibration = (Button) findViewById(R.id.sv);
    }

    @Override
    protected void onResume() {
        super.onResume();

        startNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayNotification();
            }
        });

        deletetNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancelNotification();
            }
        });

        updateNotification.setOnClickListener(new View.OnClickListener()
        {
           @Override
            public void onClick(View v)
           {
                updateNotification();
           }
        });

        startVibration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                vibrator.vibrate(500);

            }
        });
    }

    protected void displayNotification() {
        Log.i("Start", "notification");
        
        // Invoking the default notification service /
        //NotificationCompat.Builder  mBuilder =
                new NotificationCompat.Builder(this);

        mBuilder.setContentTitle("Taller Android");
        mBuilder.setContentText("Nueva Notificacon");
        mBuilder.setTicker("Taller Android");
        mBuilder.setSmallIcon(R.drawable.dalvik);

	      // Increase notification number every time a new notification arrives /
        mBuilder.setNumber(++numMessages);

        // Creates an explicit intent for an Activity in your app /
        Intent resultIntent = new Intent(this, DetailsNotifications.class);

        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        stackBuilder.addParentStack(DetailsNotifications.class);

        // Adds the Intent that starts the Activity to the top of the stack /
        stackBuilder.addNextIntent(resultIntent);
        PendingIntent resultPendingIntent =
                stackBuilder.getPendingIntent(
                        0,
                        PendingIntent.FLAG_UPDATE_CURRENT
                );

        mBuilder.setContentIntent(resultPendingIntent);

        mNotificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

	      // notificationID allows you to update the notification later on. /
        mNotificationManager.notify(notificationID, mBuilder.build());
    }

    protected void cancelNotification() {
        Log.i("Cancel", "notification");
        mNotificationManager.cancel(notificationID);
    }

    protected void updateNotification() {
        Log.i("Update", "notification");

	      /* Invoking the default notification service */
        NotificationCompat.Builder  mBuilder =
                new NotificationCompat.Builder(this);

        mBuilder.setContentTitle("Dalvik Notifications Update!");
        mBuilder.setContentText("Se actualizo el mensaje");
        mBuilder.setTicker("Updated!");
        mBuilder.setSmallIcon(R.drawable.dalvik);

	     /* Increase notification number every time a new notification arrives */
        mBuilder.setNumber(++numMessages);

	      /* Creates an explicit intent for an Activity in your app */
        Intent resultIntent = new Intent(this, DetailsNotifications.class);

        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        stackBuilder.addParentStack(DetailsNotifications.class);

	      /* Adds the Intent that starts the Activity to the top of the stack */
        stackBuilder.addNextIntent(resultIntent);
        PendingIntent resultPendingIntent =
                stackBuilder.getPendingIntent(
                        0,
                        PendingIntent.FLAG_UPDATE_CURRENT
                );

        mBuilder.setContentIntent(resultPendingIntent);

        mNotificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

	      /* Update the existing notification using same notification ID */
        mNotificationManager.notify(notificationID, mBuilder.build());
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
