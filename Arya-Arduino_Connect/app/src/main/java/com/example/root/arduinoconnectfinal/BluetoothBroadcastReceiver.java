package com.example.root.arduinoconnectfinal;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.Toast;

import static android.content.Context.NOTIFICATION_SERVICE;

public class BluetoothBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        String action = intent.getAction();
        Log.d("BroadcastActions", "Action "+action+"received");
        int state;
        BluetoothDevice bluetoothDevice;

        switch(action)
        {
            case BluetoothAdapter.ACTION_STATE_CHANGED:
                state = intent.getIntExtra(BluetoothAdapter.EXTRA_STATE, -1);
                if (state == BluetoothAdapter.STATE_OFF)
                {
                    Toast.makeText(context, "Bluetooth is off", Toast.LENGTH_SHORT).show();
                    Log.d("BroadcastActions", "Bluetooth is off");
                }
                else if (state == BluetoothAdapter.STATE_TURNING_OFF)
                {
                    Toast.makeText(context, "Bluetooth is turning off", Toast.LENGTH_SHORT).show();
                    Log.d("BroadcastActions", "Bluetooth is turning off");
                }
                else if(state == BluetoothAdapter.STATE_TURNING_ON)
                {
                    Toast.makeText(context,"Bluetooth is turning on",Toast.LENGTH_SHORT).show();
                    Log.d("BroadcastActions", "Bluetooth is turning on");
                }
                else if(state == BluetoothAdapter.STATE_ON)
                {
                    Toast.makeText(context,"Bluetooth is on",Toast.LENGTH_SHORT).show();
                    Log.d("BroadcastActions", "Bluetooth is on");
                    /*PackageManager pm = context.getPackageManager();
                    Intent launchIntent = pm.getLaunchIntentForPackage("com.example.root.arduinoconnectfinal");
                   launchIntent.setComponent(new ComponentName("com.example.root.arduinoconnectfinal", "com.example.root.arduinoconnectfinal.WelcomeScreen"));
                    context.startActivity(launchIntent);*/
                    final int MY_NOTIFICATION_ID=1;
                    NotificationManager notificationManager;
                    Notification myNotification;

                    Intent myIntent = new Intent(context, WelcomeScreen.class);
                    PendingIntent pendingIntent = PendingIntent.getActivity(
                            context,
                            0,
                            myIntent,
                            0);

                    myNotification = new NotificationCompat.Builder(context)
                            .setContentTitle("Click To Connect Arya: The Hoshiyar Home")
                            .setTicker("Notification!")
                            .setWhen(System.currentTimeMillis())
                            .setContentIntent(pendingIntent)
                            .setDefaults(Notification.DEFAULT_SOUND)
                            .setAutoCancel(true)
                            .setSmallIcon(R.drawable.ic_launcher)
                            .build();

                    notificationManager =
                            (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
                    notificationManager.notify(MY_NOTIFICATION_ID, myNotification);
                }
                break;

            case BluetoothDevice.ACTION_ACL_CONNECTED:
                bluetoothDevice = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                Toast.makeText(context, "Connected to "+bluetoothDevice.getName(),
                        Toast.LENGTH_SHORT).show();
                Log.d("BroadcastActions", "Connected to "+bluetoothDevice.getName());
                break;

            /*case BluetoothDevice.ACTION_ACL_DISCONNECTED:
                bluetoothDevice = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                Toast.makeText(context, "Disconnected from "+bluetoothDevice.getName(),
                        Toast.LENGTH_SHORT).show();
                break;*/
        }
    }
}
