package com.example.root.arduinoconnectfinal;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

public class TextInst extends AppCompatActivity {

    Switch sw;
    EditText editText;
    Button sendmsg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_inst);
        sw=(Switch)findViewById(R.id.switch1);
        editText=(EditText)findViewById(R.id.devicename);
        sendmsg=(Button)findViewById(R.id.sendmsg);
        sw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b)
                {
                    editText.append(" on");
                    sw.setText("On");
                }
                else
                {
                    editText.append(" off");
                    sw.setText("Off");
                }
            }
        });
        sendmsg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s=editText.getText().toString();
                if((s.equals("all on"))||(s.equals("All on"))||(s.equals("AllOn")))
                {
                    //Command.mConnectedThread.write("F");
                    Command.mConnectedThread.write("*all on");
                }
                else if((s.equals("switch on")||(s.equals("Switch on"))||(s.equals("switch On"))||s.equals("Switch On")))
                {
                    Command.mConnectedThread.write("*switch on");
                }
                else if((s.equals("switch off")||(s.equals("Switch off"))||(s.equals("switch Off"))||s.equals("Switch Off")))
                {
                    Command.mConnectedThread.write("*switch off");
                }
                else if((s.equals("all off")||(s.equals("All off"))||(s.equals("all Off"))||s.equals("All Off")))
                {
                    //Command.mConnectedThread.write("S");
                    Command.mConnectedThread.write("*all off");
                }
                else if((s.equals("light on")||(s.equals("Light on"))||(s.equals("light On"))||s.equals("Light On")))
                {
                    Command.mConnectedThread.write("*light on");
                }
                else if((s.equals("light off")||(s.equals("Light off"))||(s.equals("light Off"))||s.equals("Light Off")))
                {
                    Command.mConnectedThread.write("*light off");
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Invalid message, please try again", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private void doExit() {

        AlertDialog.Builder alertDialog = new AlertDialog.Builder(TextInst.this);

        alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

                Command.mConnectedThread.write("*all off");
                Command.mBluetoothAdarpter.disable();
                finish();
                Intent intn3=new Intent(TextInst.this,MainActivity.class);
                startActivity(intn3);
                moveTaskToBack(true);
            }
        });

        alertDialog.setNegativeButton("No", null);

        alertDialog.setMessage("Do you Want to Exit?");
        alertDialog.setTitle("ArduinoConnect");
        alertDialog.show();
    }
    @Override
    public void onBackPressed() {

        doExit();
    }
}
