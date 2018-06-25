package com.example.root.arduinoconnectfinal;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.io.IOException;
import java.util.Locale;

public class InstrtuctionPre extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    TextToSpeech toSpeech;
    int result2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instrtuction_pre);
        android.support.v4.app.Fragment fragment=null;
        fragment=new CommandFragment();
        android.support.v4.app.FragmentManager fragmentManager=getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.frames,fragment).commit();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        toSpeech=new TextToSpeech(InstrtuctionPre.this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {
                if(i== TextToSpeech.SUCCESS)
                {
                    result2=toSpeech.setLanguage(Locale.getDefault());
                }
                else
                {
                    Toast.makeText(InstrtuctionPre.this,"Your Device doesn't support TextToSpeech", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            doExit();
            //super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.instrtuction_pre, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Log.i("Inside","Menu Button");
            try
            {
                Command.mBtSocket.close();
            }
            catch (IOException ie)
            {
                Toast.makeText(this,"Unable To Disconnect",Toast.LENGTH_SHORT).show();
            }
            startActivity(new Intent(InstrtuctionPre.this,WelcomeScreen.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        android.support.v4.app.Fragment fragment=null;
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_vcontrol) {
            // Handle the camera action
            fragment=new CommandFragment();

        } else if (id == R.id.nav_schedule) {
            fragment=new SchedulerFragment();

        } else if (id == R.id.nav_connect) {
            //startActivity(new Intent(InstrtuctionPre.this,Command.class));
            //fragment=new ConnectFragment();
            try
            {
                Command.mBtSocket.close();
            }
            catch (IOException ie)
            {
                Toast.makeText(this,"Unable To Disconnect",Toast.LENGTH_SHORT).show();
            }
            startActivity(new Intent(InstrtuctionPre.this,WelcomeScreen.class));
            TTS("Tap To See Connected Devices");

        }
        if(fragment!=null)
        {
            Log.i("Reached","Inside Fragment");
            android.support.v4.app.FragmentManager fm=getSupportFragmentManager();
            FragmentTransaction ft= fm.beginTransaction();
            ft.replace(R.id.frames,fragment);
            ft.commit();
        }
        /*else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }*/

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    private void doExit() {

        AlertDialog.Builder alertDialog = new AlertDialog.Builder(
                InstrtuctionPre.this);

        alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

                Command.mConnectedThread.write("*all off");
                try
                {
                    Command.mBtSocket.close();
                }
                catch (IOException ie)
                {

                }
                //Command.mConnectedThread.write("S");
                Command.mBluetoothAdarpter.disable();
                finish();
                Intent intn3=new Intent(InstrtuctionPre.this,MainActivity.class);
                startActivity(intn3);
                moveTaskToBack(true);
            }
        });

        alertDialog.setNegativeButton("No", null);

        alertDialog.setMessage("Do you Want to Exit?");
        alertDialog.setTitle("ArduinoConnect");
        alertDialog.show();
    }
    public void TTS(String s)
    {
        if(result2== TextToSpeech.LANG_MISSING_DATA || result2== TextToSpeech.LANG_NOT_SUPPORTED)
        {
            Toast.makeText(InstrtuctionPre.this,"You dont know", Toast.LENGTH_LONG).show();
        }
        else
        {
            //text=s.toString();
            toSpeech.speak(s, TextToSpeech.QUEUE_FLUSH,null);
        }
    }
}
/*<item android:title="Communicate">
        <menu>
            <item
                android:id="@+id/nav_share"
                android:icon="@drawable/ic_menu_share"
                android:title="Share" />
            <item
                android:id="@+id/nav_send"
                android:icon="@drawable/ic_menu_send"
                android:title="Send" />
        </menu>
    </item>*/

