package com.example.root.arduinoconnectfinal;

import android.app.ProgressDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.UUID;

public class Command extends AppCompatActivity {

    public static BluetoothAdapter mBluetoothAdarpter = BluetoothAdapter.getDefaultAdapter();
    public static BluetoothSocket mBtSocket;
    private BluetoothDevice mBTDevice;
    private ProgressDialog dialog;

    private static final UUID MY_UUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");

    //private static String address = "98:D3:36:81:0B:22";
    private static String address; //= "00:21:13:01:21:89";

    public static ConnectedThread mConnectedThread;

    static public ListView devicelistLV;

    public String values[],addresses[];
    static ArrayAdapter<String> arrayAdapter;
    CustomAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i("Tag","Page 2");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_command);
        devicelistLV = (ListView) findViewById(R.id.devicelistLV);
        Bundle bn = getIntent().getExtras();
        values = bn.getStringArray("paireddevices");
        addresses=bn.getStringArray("pairedaddress");
        customAdapter=new CustomAdapter();
        //arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, values);

        devicelistLV.setAdapter(customAdapter);
        dialog = new ProgressDialog(Command.this);
        devicelistLV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                CustomAdapter value=(CustomAdapter) devicelistLV.getItemAtPosition(i);
                //String select = devicelistLV.getItemAtPosition(i).toString();
                Log.i("Device name:", "Successful");
                address=((TextView)view.findViewById(R.id.textView10)).getText().toString();
                Log.i("Device Address",address);
                LoadImage li= new LoadImage();
                li.execute(address);


            }
        });
    }

    public boolean connectToDevice(String address) {
        try {
            mBTDevice = mBluetoothAdarpter.getRemoteDevice(address);
            //Toast.makeText(Command.this, "Trying to Connect", Toast.LENGTH_SHORT).show();
            mBtSocket = mBTDevice.createRfcommSocketToServiceRecord(MY_UUID);
            mBtSocket.connect();
            //Toast.makeText(Command.this, "Connected", Toast.LENGTH_SHORT).show();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }


    class LoadImage extends AsyncTask<String,Void,Integer>//first is input goes to doinBack ex-URL
    {                                                   //second is progress, goes to onPost
        //third is result, used by doInBack's return
        @Override
        protected void onPostExecute(Integer integ) {
            Log.i("Reached","3");
            //super.onPostExecute(image);
            String s;
            if (dialog.isShowing()) {
                dialog.dismiss();
            }
            if(integ==1)
            {
                s="Device Connected Successfully";
                Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();
                mConnectedThread = new ConnectedThread(mBtSocket);
                Intent intn2=new Intent(Command.this,InstrtuctionPre.class);
                startActivity(intn2);
            }
            else
            {
                s="Unable To Connect, Check Connection";
                Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        protected Integer doInBackground(String... string)//Using string here and above as well as execute gives string
        {
            Log.i("Reached","2");
            Boolean ans = connectToDevice(string[0]);
            Log.i("Reached","21");
            Log.i("Device Address","Successful");
            Log.i("Reached","22");
            if (ans == true) {
                return 1;
                //Toast.makeText(Command.this, "Ready To Test", Toast.LENGTH_SHORT).show()
            } else {
                return 0;
                //Toast.makeText(Command.this, "Device Not Found", Toast.LENGTH_SHORT).show();
            }
            /*Bitmap myimg=null;
            try {
                InputStream imv=new URL(image[0].toString()).openStream();//image same as argument
                myimg= BitmapFactory.decodeStream(imv);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return myimg;*/
            //cant access any view here
        }

        @Override
        protected void onPreExecute() {
            onPause();
            Log.i("Reached","1");
            dialog.setMessage("Coming Live In...");
            dialog.show();
            //super.onPreExecute();
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }
    }
    public class ConnectedThread extends Thread {
        private final OutputStream mmOutStream;

        public ConnectedThread(BluetoothSocket socket) {
            OutputStream tmpOut = null;

            // Get the input and output streams, using temp objects because
            // member streams are final
            try {
                tmpOut = socket.getOutputStream();
            } catch (IOException e) {
            }

            mmOutStream = tmpOut;
        }
        public void write(String message) {
            //Log.d(TAG, "...Data to send: " + message + "...");
            byte[] msgBuffer = message.getBytes();
            try {
                mmOutStream.write(msgBuffer);
            } catch (IOException e) {
                //Log.d(TAG, "...Error data send: " + e.getMessage() + "...");
            }
        }
    }
    public class CustomAdapter extends BaseAdapter
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
            tv.setText(values[i]);
            tv2.setText(addresses[i]);
            return view;
        }
    }

}
