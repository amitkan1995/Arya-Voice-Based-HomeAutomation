package com.example.root.arduinoconnectfinal;


import android.app.ProgressDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Set;
import java.util.UUID;

public class ConnectFragment extends Fragment {

    public static BluetoothAdapter mBluetoothAdarpter = BluetoothAdapter.getDefaultAdapter();
    private BluetoothSocket mBtSocket;
    private BluetoothDevice mBTDevice;
    BluetoothAdapter bluetoothAdapter;
    public static Set<BluetoothDevice> devices;
    public static String devicelist[],deviceaddress[];
    private ProgressDialog dialog;

    private static final UUID MY_UUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");

    //private static String address = "98:D3:36:81:0B:22";
    private static String address; //= "00:21:13:01:21:89";

    public static Command.ConnectedThread mConnectedThread;

    static public ListView devicelistLV;

    public String values[],addresses[];
    static ArrayAdapter<String> arrayAdapter;
    CustomAdapterTwo customAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.activity_welcome_screen,null);

        devicelistLV = (ListView) view.findViewById(R.id.devicelistLV);
        //Bundle bn = getIntent().getExtras();
        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
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
        //values = bn.getStringArray("paireddevices");
        //addresses=bn.getStringArray("pairedaddress");
        customAdapter=new CustomAdapterTwo();

        return view;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }
    public class CustomAdapterTwo extends BaseAdapter
    {

        @Override
        public int getCount() {
            return WelcomeScreen.devices.size();
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            view=getLayoutInflater().inflate(R.layout.custom_layout,null);
            TextView tv=(TextView)view.findViewById(R.id.textView9);
            TextView tv2=(TextView)view.findViewById(R.id.textView10);
            tv.setText(devicelist[i]);
            tv2.setText(deviceaddress[i]);
            return view;
        }
    }
}
