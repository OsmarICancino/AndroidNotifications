package notifications.dalvik.com.notifications;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

/**
 * Created by alckon on 31/08/16.
 * Copyright Todos los Derechos Reservados | Dalvik 2016
 * Desarrollador: Osmar I. Cancino <o.cancinodiaz@gmail.com>
 */
public class DetailsNotifications extends AppCompatActivity{

    @Override
    public void onCreate(Bundle instanceBundle)
    {
        super.onCreate(instanceBundle);
        setContentView(R.layout.notification);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

}
