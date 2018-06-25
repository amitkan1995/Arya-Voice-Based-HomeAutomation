package com.example.root.arduinoconnectfinal;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class OptionsMenu extends AppCompatActivity {

    Button text,voice;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options_menu);
        text=(Button)findViewById(R.id.btntext);
        voice=(Button)findViewById(R.id.btnvoice);
        text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gototext(view);
            }
        });
        voice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotovoice(view);
            }
        });
    }
    public void gototext(View view)
    {
        Intent intn3=new Intent(OptionsMenu.this, TextInst.class);
        startActivity(intn3);
    }
    public void gotovoice(View view)
    {
        Intent intn2=new Intent(OptionsMenu.this,Instruction.class);
        startActivity(intn2);
    }
}
