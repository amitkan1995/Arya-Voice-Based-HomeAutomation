package com.example.root.arduinoconnectfinal;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

public class Instruction extends AppCompatActivity {

    TextToSpeech toSpeech;
    int result2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instruction);
        toSpeech=new TextToSpeech(Instruction.this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {
                if(i== TextToSpeech.SUCCESS)
                {
                    result2=toSpeech.setLanguage(Locale.getDefault());
                }
                else
                {
                    Toast.makeText(Instruction.this,"Your Device doesn't support TextToSpeech", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public void writedata(View view)
    {

        Intent intn=new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intn.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intn.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intn.putExtra(RecognizerIntent.EXTRA_MAX_RESULTS, 1);
        intn.putExtra(RecognizerIntent.EXTRA_PARTIAL_RESULTS, true);
        intn.putExtra(RecognizerIntent.EXTRA_SPEECH_INPUT_MINIMUM_LENGTH_MILLIS,new Long(1000));
        intn.putExtra(RecognizerIntent.EXTRA_SPEECH_INPUT_POSSIBLY_COMPLETE_SILENCE_LENGTH_MILLIS, 1000);
        intn.putExtra(RecognizerIntent.EXTRA_SPEECH_INPUT_COMPLETE_SILENCE_LENGTH_MILLIS, 1200);
        intn.putExtra(RecognizerIntent.EXTRA_PROMPT,"Say Something");
        try {
            startActivityForResult(intn, 100);
        }
        catch(Exception e)
        {
            Toast.makeText(Instruction.this,"You dont know "+e, Toast.LENGTH_LONG).show();
        }
    }
    public void onActivityResult(int request_code, int result_code,Intent i)
    {
        super.onActivityResult(request_code,result_code,i);
        switch(request_code)
        {
            case(100):
            {
                if(result_code==RESULT_OK && i!=null)
                {
                    ArrayList<String> result=i.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    Toast.makeText(Instruction.this,result.get(0), Toast.LENGTH_SHORT).show();

                    if(result.get(0).equals("all on")||(result.get(0).equals("allon"))||(result.get(0).equals("all all"))||(result.get(0).equals("allall"))||(result.get(0).equals("on"))||(result.get(0).equals("alln")))
                    {
                        Command.mConnectedThread.write("*all on");
                        //Command.mConnectedThread.write("F");
                        TTS("All devices are now on");
                        //Toast.makeText(Instruction.this,"All On ho jaye to please scream and support the developer",Toast.LENGTH_SHORT).show();
                    }
                    else if(result.get(0).equals("all all")||result.get(0).equals("all off")||(result.get(0).equals("call of"))||(result.get(0).equals("alloff"))||(result.get(0).equals("off"))||(result.get(0).equals("allof"))||(result.get(0).equals("all of"))||(result.get(0).equals("of")))
                    {
                        Command.mConnectedThread.write("*all off");
                        TTS("All devices are now off");
                        //ommand.mConnectedThread.write("S");
                        //Toast.makeText(Instruction.this,"All Off ho jaye to please scream and support the developer",Toast.LENGTH_SHORT).show();
                    }
                    else if((result.get(0).equals("LED on"))||(result.get(0).equals("ledon"))||(result.get(0).equals("LED on"))||(result.get(0).equals("LED On")))
                    {
                        Command.mConnectedThread.write("*led on");
                        TTS("The LED is now on");
                        //Toast.makeText(Instruction.this,"All Off ho jaye to please scream and support the developer",Toast.LENGTH_SHORT).show();
                    }
                    else if((result.get(0).equals("led off"))||(result.get(0).equals("ledoff"))||(result.get(0).equals("LED off"))||(result.get(0).equals("LED Off")))
                    {
                        Command.mConnectedThread.write("*led off");
                        TTS("The LED is now off");
                        //Toast.makeText(Instruction.this,"All Off ho jaye to please scream and support the developer",Toast.LENGTH_SHORT).show();
                    }
                    else if((result.get(0).equals("light on"))||(result.get(0).equals("lite on"))||(result.get(0).equals("Light on")))
                    {
                        Command.mConnectedThread.write("*light on");
                        TTS("The Light Is Now On");
                        //Toast.makeText(Instruction.this,"All Off ho jaye to please scream and support the developer",Toast.LENGTH_SHORT).show();
                    }
                    else if((result.get(0).equals("light off"))||(result.get(0).equals("lightoff"))||(result.get(0).equals("light off")))
                    {
                        Command.mConnectedThread.write("*light off");
                        TTS("The Light Is Now Off");
                        //Toast.makeText(Instruction.this,"All Off ho jaye to please scream and support the developer",Toast.LENGTH_SHORT).show();
                    }
                    else if((result.get(0).equals("switch off"))||(result.get(0).equals("switchoff"))||(result.get(0).equals("switch off")))
                    {
                        Command.mConnectedThread.write("*switch off");
                        TTS("The Switch Is Now Off");
                        //Toast.makeText(Instruction.this,"All Off ho jaye to please scream and support the developer",Toast.LENGTH_SHORT).show();
                    }
                    else if((result.get(0).equals("switch on"))||(result.get(0).equals("switchon"))||(result.get(0).equals("switch on")))
                    {
                        Command.mConnectedThread.write("*switch on");
                        TTS("The Switch Is Now On");
                        //Toast.makeText(Instruction.this,"All Off ho jaye to please scream and support the developer",Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        Toast.makeText(Instruction.this,"Please Try Again", Toast.LENGTH_SHORT).show();
                    }
                    /*if((result.get(0).equals("forward"))||(result.get(0).equals("f"))||(result.get(0).equals("up"))||(result.get(0).equals("aagay")))
                    {
                        Command.mConnectedThread.write("F");
                        TTS("Forward");
                    }
                    else if((result.get(0).equals("backward"))||(result.get(0).equals("b"))||(result.get(0).equals("down"))||(result.get(0).equals("peechay")))
                    {
                        Command.mConnectedThread.write("B");
                        TTS("Back");
                    }
                    else if((result.get(0).equals("stop"))||(result.get(0).equals("s"))||(result.get(0).equals("pause"))||(result.get(0).equals("roko")))
                    {
                        Command.mConnectedThread.write("S");
                        TTS("Stopped");
                    }
                    else if((result.get(0).equals("left"))||(result.get(0).equals("l")))
                    {
                        Command.mConnectedThread.write("L");
                        TTS("Left");
                    }
                    else if((result.get(0).equals("right"))||(result.get(0).equals("r")))
                    {
                        Command.mConnectedThread.write("R");
                        TTS("Right");
                    }*/
                }
                break;
            }
        }
    }
    public void TTS(String s)
    {
            if(result2== TextToSpeech.LANG_MISSING_DATA || result2== TextToSpeech.LANG_NOT_SUPPORTED)
            {
                Toast.makeText(Instruction.this,"You dont know", Toast.LENGTH_LONG).show();
            }
            else
            {
                //text=s.toString();
                toSpeech.speak(s, TextToSpeech.QUEUE_FLUSH,null);
            }
    }
        /*else if(view.getId()==R.id.button2)
        {
            if(toSpeech!=null)
            {
                toSpeech.stop();
            }
        }*/
        private void doExit() {

            AlertDialog.Builder alertDialog = new AlertDialog.Builder(
                    Instruction.this);

            alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {

                    Command.mConnectedThread.write("*all off");
                    //Command.mConnectedThread.write("S");
                    Command.mBluetoothAdarpter.disable();
                    finish();
                    Intent intn3=new Intent(Instruction.this,MainActivity.class);
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
