package com.example.root.arduinoconnectfinal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    ImageView iv,iv2;
    Animation a1, a2, a3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iv = (ImageView) findViewById(R.id.imageView);
        iv2=(ImageView)findViewById(R.id.imageView2);
        a1 = AnimationUtils.loadAnimation(MainActivity.this, R.anim.fade_in);
        a2 = AnimationUtils.loadAnimation(MainActivity.this, R.anim.fade_out);
        a3 = AnimationUtils.loadAnimation(MainActivity.this,R.anim.slide);
        a1.setAnimationListener(new Animation.AnimationListener() {
            public void onAnimationStart(Animation anim)
            {
            };
            public void onAnimationRepeat(Animation anim)
            {
            };
            public void onAnimationEnd(Animation anim)
            {
                iv.startAnimation(a2);
                iv2.startAnimation(a2);
            }
        });
        a2.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                iv.setImageResource(R.drawable.end);
                iv2.setImageResource(R.drawable.end);
                //Intent intn=new Intent(MainActivity.this,WelcomeScreen.class);
                Intent intn=new Intent(MainActivity.this,WelcomeScreen.class);
                startActivity(intn);

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        a3.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        iv.startAnimation(a1);
        iv2.startAnimation(a1);
    }
}
