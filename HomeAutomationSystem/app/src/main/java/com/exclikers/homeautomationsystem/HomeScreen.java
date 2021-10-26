package com.exclikers.homeautomationsystem;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.assist.AssistStructure;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import static com.exclikers.homeautomationsystem.R.id.btnStart;

/**
 * Created by Exclikeras on 3/21/2017.
 */

public class HomeScreen extends Activity {

    Button btnremote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_screen);


        btnremote = (Button) findViewById(R.id.btnremote);


        btnremote.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                /*sendData("LEDOFF");
                Toast.makeText(getBaseContext(), "Turn off LED", Toast.LENGTH_SHORT).show();*/
                Intent i = new Intent(HomeScreen.this, Devices.class);
                startActivity(i);

            }
        });
    }

    public void homeClickButton(View v)
    {
/*        if (v.getId() == R.id.btnStart)
        {*/
            Toast.makeText(HomeScreen.this,"Connecting Bluetooth",Toast.LENGTH_LONG).show();
            Intent i = new Intent(HomeScreen.this, MainActivity.class);
            startActivity(i);

     //   }

    }
    public void btnhelp(View v)
    {

            Intent i = new Intent(HomeScreen.this, Devices.class);
            startActivity(i);


    }



    public void exitClickButton(View v)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(HomeScreen.this);
        builder.setMessage("Do you want to exit?");
        builder.setCancelable(true);
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                finish();

            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }

    public void onBackPressed(){
        AlertDialog.Builder builder = new AlertDialog.Builder(HomeScreen.this);
        builder.setMessage("Do you want to exit?");
        builder.setCancelable(true);
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                finish();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        });
        AlertDialog alert = builder.create();
                alert.show();
    }





}



