package com.example.root.arduinoconnectfinal;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.widget.Toast;

/**
 * Created by RooT on 3/26/2018.
 */

public class ScheduleService extends Service {
    static int ij=1;
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //Toast.makeText(getApplicationContext(),"The Service is started", Toast.LENGTH_SHORT).show();
        //Command.mConnectedThread.write("*all on");
        /*if(ij==1)
        {
            Command.mConnectedThread.write("*all off");
            ij=0;
        }
        else if (ij==0)
        {
            Command.mConnectedThread.write("*all on");
            ij=1;
        }*/
        String text=SchedulerFragment.dropdown.getSelectedItem().toString();
        if(text.equals("Light One"))
        {
            Command.mConnectedThread.write("*light off");
        }
        else if(text.equals("All Devices"))
        {
            Command.mConnectedThread.write("*all off");
        }
       // Command.mConnectedThread.write("*all off");
        /*CommandFragment c=new CommandFragment();
        c.TTS("All Devices Are Now Off");*/
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
