package com.example.root.arduinoconnectfinal;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Calendar;

import static android.content.Context.ALARM_SERVICE;

public class SchedulerFragment extends Fragment {
    Button b1;
    EditText timeinput;
    public static Spinner dropdown;
    ImageView settimer;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_scheduler,null);

        dropdown =(Spinner)view.findViewById(R.id.listdevicelv);
        timeinput=(EditText)view.findViewById(R.id.timeinput);
        settimer=(ImageView)view.findViewById(R.id.settimer);
        String[] items = new String[]{"Light One","All Devices"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, items);
//set the spinners adapter to the previously created one.
        dropdown.setAdapter(adapter);
        /*text = (EditText) view.findViewById(R.id.time);
        b1=(Button)view.findViewById(R.id.schedule);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startAlert(v);
            }
        });*/
        settimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(),dropdown.getSelectedItem().toString()+" will be turned off in "+timeinput.getText().toString()+" seconds",Toast.LENGTH_SHORT).show();
                String text=dropdown.getSelectedItem().toString();
                int timeperiod=Integer.parseInt(timeinput.getText().toString());
                startAlert(v,text,timeperiod);
            }
        });

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
    public void startAlert(View view,String text, int timeperiod) {
        //int i = Integer.parseInt(text.getText().toString());
        /*Intent intent = new Intent(getActivity(), MyBroadcastReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(getActivity().getApplicationContext(), 234324243, intent, 0);
        AlarmManager alarmManager = (AlarmManager) getActivity().getSystemService(ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis()
                + (i * 1000), pendingIntent);
        Toast.makeText(getActivity(), "Device To Be Turned Off In " + i + " seconds",
                Toast.LENGTH_LONG).show();*/
        Calendar cal=Calendar.getInstance();
        Intent intent = new Intent(getActivity(), ScheduleService.class);
        PendingIntent pintent = PendingIntent.getService(getActivity(), 0, intent, 0);
        AlarmManager alarm = (AlarmManager)getActivity().getSystemService(Context.ALARM_SERVICE);
        alarm.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis()
                + (timeperiod * 1000), pintent);
        //alarm.setRepeating(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), i*1000, pintent);
    }
}

/*THEME FOR SPINNER android:background="@android:drawable/btn_dropdown"*/