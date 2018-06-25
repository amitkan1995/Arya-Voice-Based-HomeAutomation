package com.example.root.arduinoconnectfinal;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.Set;

public class WelcomeScreen extends AppCompatActivity {

    BluetoothAdapter bluetoothAdapter;
    public static Set<BluetoothDevice> devices;
    public static String devicelist[],deviceaddress[];
    ArrayList<String> availlistarraylist;
    Button seepaireddevices;
    Set<BluetoothDevice> newDevices;
    //static public ListView devicelistLV;
    String values[];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_screen);
        //enabledisable = (Button) findViewById(R.id.enabledisableBT);
        //seepaireddevices = (Button) findViewById(R.id.seepaireddevicesBT);
        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        //devicelistLV=(ListView)findViewById(R.id.devicelistLV);
        if (!bluetoothAdapter.isEnabled()) {
            Intent intn1 = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivity(intn1);
        } else if (bluetoothAdapter.isEnabled()) {
        }

    }
    public void seepaireddevices(View view)
    {
        devices = bluetoothAdapter.getBondedDevices();
        devicelist = new String[devices.size()];
        deviceaddress = new String[devices.size()];
        int j = 0;
        Log.i("Tag","Page 1");
        for (BluetoothDevice device : devices) {
            devicelist[j] = device.getName().toString();
            deviceaddress[j]=device.getAddress().toString();
            j++;
        }
        Log.i("Tag","Page 1 1");
        Bundle bundle = new Bundle();
        bundle.putStringArray("paireddevices", devicelist);
        bundle.putStringArray("pairedaddress",deviceaddress);
        //Command.devicelistLV.setAdapter(customAdapter);
        Log.i("Tag","Page 1 2");
        Intent intn = new Intent(WelcomeScreen.this,Command.class);
        intn.putExtras(bundle);
        Log.i("Tag","Page 1 3");
        startActivity(intn);
        Log.i("Tag","Page 1 4");
    }
}
