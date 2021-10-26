package com.exclikers.homeautomationsystem;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.os.Build;
import android.support.constraint.ConstraintLayout;
import android.text.InputFilter;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import static android.view.View.INVISIBLE;
import static android.view.View.VISIBLE;

public class MainActivity extends Activity  {

    private static final String TAG = "bluetooth";


    ToggleButton t1, t2, t3, t4, t5, t6, t7, t8;
    ConstraintLayout devicescreen, remotescreen, remotebuttons, dvdbuttons, projectorbuttons, airconbuttons;

    ArrayAdapter<String> adapter;

    ArrayList<String> addArray = new ArrayList<String>();

    ListView listview, add_list;

    String selectedDevices, selectedbrand, savedremote;

    Button remotebtnadd, offall;

    String[] remote = {
            "TV",
            "DVD PLAYER",
            "PROJECTOR",
            "AIRCON"
    };


    String[] tv = {
            "Samsung",
            "LG",
            "Sony",
            "Panasonic",
            "Philips",
            "Toshiba",
            "Sharp",
            "JVC",
            "Deviant",
            "Bravia"
    };

    String[] dvd = {
            "Samsung",
            "LG",
            "Sony",
            "Panasonic",
            "Philips",
            "Toshiba",
            "Sharp",
            "JVC",
            "Pensonic",
            "Magus"
    };

    String[] projector = {
            "Panasonic",
            "Acer",
            "NEC",
            "Epson",
            "Sanyo",
            "Hitachi",
            "ViewSonic",
            "Sharp",
            "Dell"
    };

    String[] aircon = {
            "Hitachi",
            "LG",
            "Samsung",
            "CARRIER",
            "Panasonic",
            "Sanyo"
    };






    private BluetoothAdapter btAdapter = null;
    private BluetoothSocket btSocket = null;
    private OutputStream outStream = null;

    // SPP UUID service
    private static final UUID MY_UUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");

    // MAC-address of Bluetooth module (you must edit this line)
    private static String address = "98:D3:36:00:C5:D9";


    /**
     * Called when the activity is first created.
     */



    TextView btnlight1, btnlight2, btnlight3, btnlight4, btndevice1, btndevice2, btndevice3, btndevice4;




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        t1 = (ToggleButton) findViewById(R.id.toggleButton3);
        t2 = (ToggleButton) findViewById(R.id.toggleButton2);
        t3 = (ToggleButton) findViewById(R.id.toggleButton1);
        t4 = (ToggleButton) findViewById(R.id.toggleButton4);
        t5 = (ToggleButton) findViewById(R.id.toggleButton5);
        t6 = (ToggleButton) findViewById(R.id.toggleButton6);
        t7 = (ToggleButton) findViewById(R.id.toggleButton7);
        t8 = (ToggleButton) findViewById(R.id.toggleButton8);
        //r = (RelativeLayout) findViewById(R.id.layout);
        devicescreen = (ConstraintLayout) findViewById(R.id.devicescreen);
        remotescreen = (ConstraintLayout) findViewById(R.id.remotescreen);
        remotebuttons = (ConstraintLayout) findViewById(R.id.TVbuttons);
        dvdbuttons = (ConstraintLayout) findViewById(R.id.DVDbuttons);
        projectorbuttons = (ConstraintLayout) findViewById(R.id.PROJECTORbuttons);
        airconbuttons = (ConstraintLayout) findViewById(R.id.AIRCONbuttons);
        //dvdbuttons = (ConstraintLayout) findViewById(R.id.DVDbuttons);
        listview = (ListView)findViewById(R.id.remote_list);
        add_list = (ListView) findViewById(R.id.add_list);

        btnlight1 = (TextView) findViewById(R.id.btnlight1);
        btnlight2 = (TextView) findViewById(R.id.btnlight2);
        btnlight3 = (TextView) findViewById(R.id.btnLight3);
        btnlight4 = (TextView) findViewById(R.id.btnLight4);
        btndevice1 = (TextView) findViewById(R.id.btndevice1);
        btndevice2 = (TextView) findViewById(R.id.btndevice2);
        btndevice3 = (TextView) findViewById(R.id.btndevice3);
        btndevice4 = (TextView) findViewById(R.id.btndevice4);

        remotebtnadd = (Button) findViewById(R.id.remotebtnadd);
        offall = (Button) findViewById(R.id.offall);






        SharedPreferences sharedPreferences=getSharedPreferences("SavedData",Context.MODE_PRIVATE);
        String l1 = sharedPreferences.getString("light1","ADD LIGHT 1");
        String l2 = sharedPreferences.getString("light2","ADD LIGHT 2");
        String l3 = sharedPreferences.getString("light3","ADD LIGHT 3");
        String l4 = sharedPreferences.getString("light4","ADD LIGHT 4");
        String d1 = sharedPreferences.getString("device1","ADD DEVICE 1");
        String d2 = sharedPreferences.getString("device2","ADD DEVICE 2");
        String d3 = sharedPreferences.getString("device3","ADD DEVICE 3");
        String d4 = sharedPreferences.getString("device4","ADD DEVICE 4");
        btnlight1.setText(l1);
        btnlight2.setText(l2);
        btnlight3.setText(l3);
        btnlight4.setText(l4);
        btndevice1.setText(d1);
        btndevice2.setText(d2);
        btndevice3.setText(d3);
        btndevice4.setText(d4);

        String l1_status = sharedPreferences.getString("light1_status","OFF");
        String l2_status = sharedPreferences.getString("light2_status","OFF");
        String l3_status = sharedPreferences.getString("light3_status","OFF");
        String l4_status = sharedPreferences.getString("light4_status","OFF");
        String d1_status = sharedPreferences.getString("device1_status","OFF");
        String d2_status = sharedPreferences.getString("device2_status","OFF");
        String d3_status = sharedPreferences.getString("device3_status","OFF");
        String d4_status = sharedPreferences.getString("device4_status","OFF");




        //SAVED REMOTE SELECT
        add_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                savedremote = parent.getItemAtPosition(position).toString();

                if (savedremote.equals("Samsung TV")){
                    remotebuttons.setVisibility(VISIBLE);
                    remotebtnadd.setVisibility(View.INVISIBLE);
                    listview.setVisibility(INVISIBLE);
                    add_list.setVisibility(INVISIBLE);
                    selectedbrand = "TV";
                    selectedDevices = "Samsung";


                }
                if (savedremote.equals("LG TV")){
                    remotebuttons.setVisibility(VISIBLE);
                    remotebtnadd.setVisibility(View.INVISIBLE);
                    listview.setVisibility(INVISIBLE);
                    add_list.setVisibility(INVISIBLE);
                    selectedbrand = "TV";
                    selectedDevices = "LG";
                }
                if (savedremote.equals("Sony TV")){
                    remotebuttons.setVisibility(VISIBLE);
                    remotebtnadd.setVisibility(View.INVISIBLE);
                    listview.setVisibility(INVISIBLE);
                    add_list.setVisibility(INVISIBLE);
                    selectedbrand = "TV";
                    selectedDevices = "Sony";
                }
                if (savedremote.equals("Panasonic TV")){
                    remotebuttons.setVisibility(VISIBLE);
                    remotebtnadd.setVisibility(View.INVISIBLE);
                    listview.setVisibility(INVISIBLE);
                    add_list.setVisibility(INVISIBLE);
                    selectedbrand = "TV";
                    selectedDevices = "Panasonic";
                 }
                if (savedremote.equals("Philips TV")){
                    remotebuttons.setVisibility(VISIBLE);
                    remotebtnadd.setVisibility(View.INVISIBLE);
                    listview.setVisibility(INVISIBLE);
                    add_list.setVisibility(INVISIBLE);
                    selectedbrand = "TV";
                    selectedDevices = "Philips";
                }
                if (savedremote.equals("Toshiba TV")){
                    remotebuttons.setVisibility(VISIBLE);
                    remotebtnadd.setVisibility(View.INVISIBLE);
                    listview.setVisibility(INVISIBLE);
                    add_list.setVisibility(INVISIBLE);
                    selectedbrand = "TV";
                    selectedDevices = "Toshiba";
                }
                if (savedremote.equals("Sharp TV")){
                    remotebuttons.setVisibility(VISIBLE);
                    remotebtnadd.setVisibility(View.INVISIBLE);
                    listview.setVisibility(INVISIBLE);
                    add_list.setVisibility(INVISIBLE);
                    selectedbrand = "TV";
                    selectedDevices = "Sharp";
                }
                if (savedremote.equals("JVC TV")){
                    remotebuttons.setVisibility(VISIBLE);
                    remotebtnadd.setVisibility(View.INVISIBLE);
                    listview.setVisibility(INVISIBLE);
                    add_list.setVisibility(INVISIBLE);
                    selectedbrand = "TV";
                    selectedDevices = "JVC";
                }
                if (savedremote.equals("Deviant TV")){
                    remotebuttons.setVisibility(VISIBLE);
                    remotebtnadd.setVisibility(View.INVISIBLE);
                    listview.setVisibility(INVISIBLE);
                    add_list.setVisibility(INVISIBLE);
                    selectedbrand = "TV";
                    selectedDevices = "Deviant";
                }
                if (savedremote.equals("Bravia TV")){
                    remotebuttons.setVisibility(VISIBLE);
                    remotebtnadd.setVisibility(View.INVISIBLE);
                    listview.setVisibility(INVISIBLE);
                    add_list.setVisibility(INVISIBLE);
                    selectedbrand = "TV";
                    selectedDevices = "Bravia";
                }
               //DVD
                if (savedremote.equals("Samsung DVD PLAYER")){
                    dvdbuttons.setVisibility(VISIBLE);
                    remotebtnadd.setVisibility(View.INVISIBLE);
                    listview.setVisibility(INVISIBLE);
                    add_list.setVisibility(INVISIBLE);
                    selectedbrand = "DVD PLAYER";
                    selectedDevices = "Samsung";
                }
                if (savedremote.equals("LG DVD PLAYER")){
                    dvdbuttons.setVisibility(VISIBLE);
                    remotebtnadd.setVisibility(View.INVISIBLE);
                    listview.setVisibility(INVISIBLE);
                    add_list.setVisibility(INVISIBLE);
                    selectedbrand = "DVD PLAYER";
                    selectedDevices = "LG";
                }
                if (savedremote.equals("Sony DVD PLAYER")){
                    dvdbuttons.setVisibility(VISIBLE);
                    remotebtnadd.setVisibility(View.INVISIBLE);
                    listview.setVisibility(INVISIBLE);
                    add_list.setVisibility(INVISIBLE);
                    selectedbrand = "DVD PLAYER";
                    selectedDevices = "Sony";
                }
                if (savedremote.equals("Panasonic DVD PLAYER")){
                    dvdbuttons.setVisibility(VISIBLE);
                    remotebtnadd.setVisibility(View.INVISIBLE);
                    listview.setVisibility(INVISIBLE);
                    add_list.setVisibility(INVISIBLE);
                    selectedbrand = "DVD PLAYER";
                    selectedDevices = "Panasonic";
                }
                if (savedremote.equals("Philips DVD PLAYER")){
                    dvdbuttons.setVisibility(VISIBLE);
                    remotebtnadd.setVisibility(View.INVISIBLE);
                    listview.setVisibility(INVISIBLE);
                    add_list.setVisibility(INVISIBLE);
                    selectedbrand = "DVD PLAYER";
                    selectedDevices = "Philips";
                }
                if (savedremote.equals("Toshiba DVD PLAYER")){
                    dvdbuttons.setVisibility(VISIBLE);
                    remotebtnadd.setVisibility(View.INVISIBLE);
                    listview.setVisibility(INVISIBLE);
                    add_list.setVisibility(INVISIBLE);
                    selectedbrand = "DVD PLAYER";
                    selectedDevices = "Toshiba";
                }
                if (savedremote.equals("Sharp DVD PLAYER")){
                    dvdbuttons.setVisibility(VISIBLE);
                    remotebtnadd.setVisibility(View.INVISIBLE);
                    listview.setVisibility(INVISIBLE);
                    add_list.setVisibility(INVISIBLE);
                    selectedbrand = "DVD PLAYER";
                    selectedDevices = "Sharp";
                }
                if (savedremote.equals("JVC DVD PLAYER")){
                    dvdbuttons.setVisibility(VISIBLE);
                    remotebtnadd.setVisibility(View.INVISIBLE);
                    listview.setVisibility(INVISIBLE);
                    add_list.setVisibility(INVISIBLE);
                    selectedbrand = "DVD PLAYER";
                    selectedDevices = "JVC";
                }
                if (savedremote.equals("Pensonic DVD PLAYER")){
                    dvdbuttons.setVisibility(VISIBLE);
                    remotebtnadd.setVisibility(View.INVISIBLE);
                    listview.setVisibility(INVISIBLE);
                    add_list.setVisibility(INVISIBLE);
                    selectedbrand = "DVD PLAYER";
                    selectedDevices = "Pensonic";
                }
                if (savedremote.equals("Magus DVD PLAYER")){
                    dvdbuttons.setVisibility(VISIBLE);
                    remotebtnadd.setVisibility(View.INVISIBLE);
                    listview.setVisibility(INVISIBLE);
                    add_list.setVisibility(INVISIBLE);
                    selectedbrand = "DVD PLAYER";
                    selectedDevices = "Magus";
                }
                //PROJECTOR
                if (savedremote.equals("Panasonic PROJECTOR")){
                    projectorbuttons.setVisibility(VISIBLE);
                    remotebtnadd.setVisibility(View.INVISIBLE);
                    listview.setVisibility(INVISIBLE);
                    add_list.setVisibility(INVISIBLE);
                    selectedbrand =  "PROJECTOR";
                    selectedDevices = "Panasonic";
                }
                if (savedremote.equals("Acer PROJECTOR")){
                    projectorbuttons.setVisibility(VISIBLE);
                    remotebtnadd.setVisibility(View.INVISIBLE);
                    listview.setVisibility(INVISIBLE);
                    add_list.setVisibility(INVISIBLE);
                    selectedbrand = "PROJECTOR";
                    selectedDevices = "Acer";
                }
                if (savedremote.equals("NEC PROJECTOR")){
                    projectorbuttons.setVisibility(VISIBLE);
                    remotebtnadd.setVisibility(View.INVISIBLE);
                    listview.setVisibility(INVISIBLE);
                    add_list.setVisibility(INVISIBLE);
                    selectedbrand = "PROJECTOR";
                    selectedDevices = "NEC";
                }
                if (savedremote.equals("Epson PROJECTOR")){
                    projectorbuttons.setVisibility(VISIBLE);
                    remotebtnadd.setVisibility(View.INVISIBLE);
                    listview.setVisibility(INVISIBLE);
                    add_list.setVisibility(INVISIBLE);
                    selectedbrand = "PROJECTOR";
                    selectedDevices = "Epson";
                }
                if (savedremote.equals("Hitachi PROJECTOR")){
                    projectorbuttons.setVisibility(VISIBLE);
                    remotebtnadd.setVisibility(View.INVISIBLE);
                    listview.setVisibility(INVISIBLE);
                    add_list.setVisibility(INVISIBLE);
                    selectedbrand = "PROJECTOR";
                    selectedDevices = "Hitachi";
                }
                if (savedremote.equals("ViewSonic PROJECTOR")){
                    projectorbuttons.setVisibility(VISIBLE);
                    remotebtnadd.setVisibility(View.INVISIBLE);
                    listview.setVisibility(INVISIBLE);
                    add_list.setVisibility(INVISIBLE);
                    selectedbrand = "PROJECTOR";
                    selectedDevices = "ViewSonic";
                }
                if (savedremote.equals("Sharp PROJECTOR")){
                    projectorbuttons.setVisibility(VISIBLE);
                    remotebtnadd.setVisibility(View.INVISIBLE);
                    listview.setVisibility(INVISIBLE);
                    add_list.setVisibility(INVISIBLE);
                    selectedbrand = "PROJECTOR";
                    selectedDevices = "Sharp";
                }
                if (savedremote.equals("Dell PROJECTOR")){
                    projectorbuttons.setVisibility(VISIBLE);
                    remotebtnadd.setVisibility(View.INVISIBLE);
                    listview.setVisibility(INVISIBLE);
                    add_list.setVisibility(INVISIBLE);
                    selectedbrand = "PROJECTOR";
                    selectedDevices = "Dell";
                }
                //AIRCON
                if (savedremote.equals("Hitachi AIRCON")){
                    airconbuttons.setVisibility(VISIBLE);
                    remotebtnadd.setVisibility(View.INVISIBLE);
                    listview.setVisibility(INVISIBLE);
                    add_list.setVisibility(INVISIBLE);
                    selectedbrand = "AIRCON";
                    selectedDevices = "Hitachi";
                }
                if (savedremote.equals("LG AIRCON")){
                    airconbuttons.setVisibility(VISIBLE);
                    remotebtnadd.setVisibility(View.INVISIBLE);
                    listview.setVisibility(INVISIBLE);
                    add_list.setVisibility(INVISIBLE);
                    selectedbrand = "AIRCON";
                    selectedDevices =  "LG";
                }
                if (savedremote.equals("Samsung AIRCON")){
                    airconbuttons.setVisibility(VISIBLE);
                    remotebtnadd.setVisibility(View.INVISIBLE);
                    listview.setVisibility(INVISIBLE);
                    add_list.setVisibility(INVISIBLE);
                    selectedbrand = "AIRCON";
                    selectedDevices = "Samsung";
                }
                if (savedremote.equals("CARRIER AIRCON")){
                    airconbuttons.setVisibility(VISIBLE);
                    remotebtnadd.setVisibility(View.INVISIBLE);
                    listview.setVisibility(INVISIBLE);
                    add_list.setVisibility(INVISIBLE);
                    selectedbrand = "AIRCON";
                    selectedDevices = "CARRIER";
                }
                if (savedremote.equals("Panasonic AIRCON")){
                    airconbuttons.setVisibility(VISIBLE);
                    remotebtnadd.setVisibility(View.INVISIBLE);
                    listview.setVisibility(INVISIBLE);
                    add_list.setVisibility(INVISIBLE);
                    selectedbrand = "AIRCON";
                    selectedDevices = "Panasonic";
                }
                if (savedremote.equals("Sanyo AIRCON")){
                    airconbuttons.setVisibility(VISIBLE);
                    remotebtnadd.setVisibility(View.INVISIBLE);
                    listview.setVisibility(INVISIBLE);
                    add_list.setVisibility(INVISIBLE);
                    selectedbrand = "AIRCON";
                    selectedDevices = "Sanyo";
                }



            }
        });
        //==================


        //ADD REMOTE

        Set<String> set = sharedPreferences.getStringSet("remote", Collections.<String>emptySet());
        final List<String> addList=new ArrayList<String>(set);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_dropdown_item_1line,addList);
        add_list.setAdapter(adapter);

        //====

        if (l1_status.equals("ON")){
            t1.setChecked(true);
        }else
        {
            t1.setChecked(false);
        }

        if (l2_status.equals("ON")){
            t2.setChecked(true);
        }else
        {
            t2.setChecked(false);
        }
        if (l3_status.equals("ON")){
            t3.setChecked(true);
        }else
        {
            t3.setChecked(false);
        }
        if (l4_status.equals("ON")){
            t4.setChecked(true);
        }else
        {
            t4.setChecked(false);
        }

        if (d1_status.equals("ON")){
            t5.setChecked(true);
        }else
        {
            t5.setChecked(false);
        }

        if (d2_status.equals("ON")){
            t6.setChecked(true);
        }else
        {
            t6.setChecked(false);
        }
        if (d3_status.equals("ON")){
            t7.setChecked(true);
        }else
        {
            t7.setChecked(false);
        }
        if (d4_status.equals("ON")){
            t8.setChecked(true);
        }else
        {
            t8.setChecked(false);
        }

      if(l1 == "ADD LIGHT 1")
        {
            t1.setVisibility(View.INVISIBLE);
            btnlight2.setVisibility(View.INVISIBLE);
        }
        if(l2 == "ADD LIGHT 2")
        {
            t2.setVisibility(View.INVISIBLE);
            btnlight3.setVisibility(View.INVISIBLE);
        }
        if(l3 == "ADD LIGHT 3")
        {
            t3.setVisibility(View.INVISIBLE);
            btnlight4.setVisibility(View.INVISIBLE);
        }
        if(l4 == "ADD LIGHT 4")
        {
            t4.setVisibility(View.INVISIBLE);

        }
        if(d1 == "ADD DEVICE 1")
        {
            t5.setVisibility(View.INVISIBLE);
            btndevice2.setVisibility(View.INVISIBLE);
        }
        if(d2 == "ADD DEVICE 2")
        {
            t6.setVisibility(View.INVISIBLE);
            btndevice3.setVisibility(View.INVISIBLE);
        }
        if(d3 == "ADD DEVICE 3")
        {
            t7.setVisibility(View.INVISIBLE);
            btndevice4.setVisibility(View.INVISIBLE);
        }
        if(d4 == "ADD DEVICE 4")
        {
            t8.setVisibility(View.INVISIBLE);
        }




        t1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (t1.isChecked()) {
                    sendData("A");

                    SharedPreferences sharedPreferences=getSharedPreferences("SavedData", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor=sharedPreferences.edit();
                    editor.putString("light1_status","ON");
                    editor.commit();


                } else {
                    sendData("a");

                    SharedPreferences sharedPreferences=getSharedPreferences("SavedData", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor=sharedPreferences.edit();
                    editor.putString("light1_status","OFF");
                    editor.commit();
                }
            }
        });



        t2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (t2.isChecked()) {
                    sendData("B");

                    SharedPreferences sharedPreferences=getSharedPreferences("SavedData", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor=sharedPreferences.edit();
                    editor.putString("light2_status","ON");
                    editor.commit();



                } else {
                    sendData("b");

                    SharedPreferences sharedPreferences=getSharedPreferences("SavedData", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor=sharedPreferences.edit();
                    editor.putString("light2_status","OFF");
                    editor.commit();
                }
            }
        });

        t3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (t3.isChecked()) {
                    sendData("C");

                    SharedPreferences sharedPreferences=getSharedPreferences("SavedData", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor=sharedPreferences.edit();
                    editor.putString("light3_status","ON");
                    editor.commit();
                } else {
                    sendData("c");

                    SharedPreferences sharedPreferences=getSharedPreferences("SavedData", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor=sharedPreferences.edit();
                    editor.putString("light3_status","OFF");
                    editor.commit();
                }
            }
        });

        t4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (t4.isChecked()) {
                    sendData("D");

                    SharedPreferences sharedPreferences=getSharedPreferences("SavedData", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor=sharedPreferences.edit();
                    editor.putString("light4_status","ON");
                    editor.commit();
                } else {
                    sendData("d");
                    SharedPreferences sharedPreferences=getSharedPreferences("SavedData", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor=sharedPreferences.edit();
                    editor.putString("light4_status","OFF");
                    editor.commit();
                }
            }
        });

        t5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (t5.isChecked()) {
                    sendData("E");
                    SharedPreferences sharedPreferences=getSharedPreferences("SavedData", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor=sharedPreferences.edit();
                    editor.putString("device1_status","ON");
                    editor.commit();
                } else {
                    sendData("e");
                    SharedPreferences sharedPreferences=getSharedPreferences("SavedData", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor=sharedPreferences.edit();
                    editor.putString("device1_status","OFF");
                    editor.commit();
                }
            }
        });

        t6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (t6.isChecked()) {
                    sendData("F");
                    SharedPreferences sharedPreferences=getSharedPreferences("SavedData", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor=sharedPreferences.edit();
                    editor.putString("device2_status","ON");
                    editor.commit();
                } else {
                    sendData("f");
                    SharedPreferences sharedPreferences=getSharedPreferences("SavedData", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor=sharedPreferences.edit();
                    editor.putString("device2_status","OFF");
                    editor.commit();
                }
            }
        });

        t7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (t7.isChecked()) {
                    sendData("G");
                    SharedPreferences sharedPreferences=getSharedPreferences("SavedData", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor=sharedPreferences.edit();
                    editor.putString("device3_status","ON");
                    editor.commit();
                } else {
                    sendData("g");
                    SharedPreferences sharedPreferences=getSharedPreferences("SavedData", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor=sharedPreferences.edit();
                    editor.putString("device3_status","OFF");
                    editor.commit();
                }
            }
        });

        t8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (t8.isChecked()) {
                    sendData("H");
                    SharedPreferences sharedPreferences=getSharedPreferences("SavedData", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor=sharedPreferences.edit();
                    editor.putString("device4_status","ON");
                    editor.commit();
                } else {
                    sendData("h");
                    SharedPreferences sharedPreferences=getSharedPreferences("SavedData", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor=sharedPreferences.edit();
                    editor.putString("device4_status","OFF");
                    editor.commit();
                }
            }
        });




        btAdapter = BluetoothAdapter.getDefaultAdapter();
        checkBTState();





        //Code on Long Press
        add_list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {



                //Dialog Box
                android.app.AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Are you sure you want to delete?");

                //Decision Button
                builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        //Code to remove item on ListView
                        addArray.remove(position);
                        ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_dropdown_item_1line,addArray);
                        add_list.setAdapter(adapter);
                        adapter.notifyDataSetChanged();
                        adapter.notifyDataSetInvalidated();
                        Toast.makeText(MainActivity.this,"Deleted!",Toast.LENGTH_LONG).show();

                        SharedPreferences prefs=MainActivity.this.getSharedPreferences("SavedData",Context.MODE_PRIVATE);
                        SharedPreferences.Editor edit=prefs.edit();

                        Set<String> set = new HashSet<String>();

                        set.addAll(addArray);
                        edit.putStringSet("remote", set);
                        edit.commit();

                        devicescreen.setVisibility(View.INVISIBLE);
                        remotescreen.setVisibility(View.VISIBLE);

                        listview.setVisibility(INVISIBLE);
                        remotebtnadd.setVisibility(VISIBLE);
                        add_list.setVisibility(VISIBLE);
                        remotebuttons.setVisibility(INVISIBLE);
                        dvdbuttons.setVisibility(INVISIBLE);
                        projectorbuttons.setVisibility(INVISIBLE);
                        airconbuttons.setVisibility(INVISIBLE);

                    }
                });
                builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                builder.show();



                return false;
            }
        });



    }



    public void btnoffall (View view)
    {
        t1.setChecked(false);
        t2.setChecked(false);
        t3.setChecked(false);
        t4.setChecked(false);
        t5.setChecked(false);
        t6.setChecked(false);
        t7.setChecked(false);
        t8.setChecked(false);
        sendData("Z");
    }



    public void remoteaddbtn (View view)
    {
      remotebtnadd.setVisibility(View.INVISIBLE);
        add_list.setVisibility(INVISIBLE);

        listview.setVisibility(VISIBLE);

        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line,remote);
        listview.setAdapter(adapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                selectedDevices = parent.getItemAtPosition(position).toString();
                Toast.makeText(getBaseContext(),selectedDevices+ " is selected",Toast.LENGTH_LONG).show();
                selectedbrand = selectedDevices;

                if(selectedDevices.equals("TV")) {

                    adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_dropdown_item_1line, tv);
                    listview.setAdapter(adapter);

                    listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {



                                selectedDevices = parent.getItemAtPosition(position).toString();




                            if (addArray.contains(selectedDevices + " " + selectedbrand)){
                                Toast.makeText(getBaseContext(), "Remote Already Added", Toast.LENGTH_SHORT).show();
                            }
                            else {
                                addArray.add(selectedDevices + " " + selectedbrand);

                                ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_dropdown_item_1line, addArray);
                                add_list.setAdapter(adapter);


                                SharedPreferences sharedPreferences = MainActivity.this.getSharedPreferences("SavedData", Context.MODE_PRIVATE);
                                SharedPreferences.Editor edit = sharedPreferences.edit();

                                Set<String> set = new HashSet<String>();

                                set.addAll(addArray);
                                edit.putStringSet("remote", set);
                                edit.commit();
                                Toast.makeText(getBaseContext(), selectedDevices + " " + selectedbrand + " Successfuly Added", Toast.LENGTH_LONG).show();

                                remotebtnadd.setVisibility(View.VISIBLE);
                                listview.setVisibility(INVISIBLE);
                                add_list.setVisibility(VISIBLE);

                            }
                        }
                    });
                }
                if(selectedDevices.equals("DVD PLAYER")) {

                    adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_dropdown_item_1line, dvd);
                    listview.setAdapter(adapter);

                    listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                            selectedDevices = parent.getItemAtPosition(position).toString();
                            Toast.makeText(getBaseContext(), selectedDevices + " is selected", Toast.LENGTH_LONG).show();

                            if (addArray.contains(selectedDevices + " " + selectedbrand)) {
                                Toast.makeText(getBaseContext(), "Remote Already Added", Toast.LENGTH_SHORT).show();
                            } else {
                                addArray.add(selectedDevices + " " + selectedbrand);


                                ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_dropdown_item_1line, addArray);
                                add_list.setAdapter(adapter);


                                SharedPreferences sharedPreferences = MainActivity.this.getSharedPreferences("SavedData", Context.MODE_PRIVATE);
                                SharedPreferences.Editor edit = sharedPreferences.edit();

                                Set<String> set = new HashSet<String>();

                                set.addAll(addArray);
                                edit.putStringSet("remote", set);
                                edit.commit();
                                Toast.makeText(getBaseContext(), selectedDevices + " " + selectedbrand + " Successfuly Added", Toast.LENGTH_LONG).show();

                                remotebtnadd.setVisibility(View.VISIBLE);
                                listview.setVisibility(INVISIBLE);
                                add_list.setVisibility(VISIBLE);
                            }
                        }
                    });
                }
                if(selectedDevices.equals("PROJECTOR")) {

                    adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_dropdown_item_1line, projector);
                    listview.setAdapter(adapter);

                    listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                            selectedDevices = parent.getItemAtPosition(position).toString();
                            Toast.makeText(getBaseContext(), selectedDevices + " is selected", Toast.LENGTH_LONG).show();

                            if (addArray.contains(selectedDevices + " " + selectedbrand)){
                                Toast.makeText(getBaseContext(), "Remote Already Added", Toast.LENGTH_SHORT).show();
                            }
                            else {
                                addArray.add(selectedDevices + " " + selectedbrand);


                                ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_dropdown_item_1line, addArray);
                                add_list.setAdapter(adapter);


                                SharedPreferences sharedPreferences = MainActivity.this.getSharedPreferences("SavedData", Context.MODE_PRIVATE);
                                SharedPreferences.Editor edit = sharedPreferences.edit();

                                Set<String> set = new HashSet<String>();

                                set.addAll(addArray);
                                edit.putStringSet("remote", set);
                                edit.commit();
                                Toast.makeText(getBaseContext(), selectedDevices + " " + selectedbrand + " Successfuly Added", Toast.LENGTH_LONG).show();

                                remotebtnadd.setVisibility(View.VISIBLE);
                                listview.setVisibility(INVISIBLE);
                                add_list.setVisibility(VISIBLE);
                            }
                        }
                    });
                }
                if(selectedDevices.equals("AIRCON")){

                    adapter = new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_dropdown_item_1line,aircon);
                    listview.setAdapter(adapter);

                    listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                            selectedDevices = parent.getItemAtPosition(position).toString();
                            Toast.makeText(getBaseContext(), selectedDevices + " is selected", Toast.LENGTH_LONG).show();

                            if (addArray.contains(selectedDevices + " " + selectedbrand)){
                                Toast.makeText(getBaseContext(), "Remote Already Added", Toast.LENGTH_SHORT).show();
                            }
                            else {
                                addArray.add(selectedDevices + " " + selectedbrand);


                                ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_dropdown_item_1line, addArray);
                                add_list.setAdapter(adapter);


                                SharedPreferences sharedPreferences = MainActivity.this.getSharedPreferences("SavedData", Context.MODE_PRIVATE);
                                SharedPreferences.Editor edit = sharedPreferences.edit();

                                Set<String> set = new HashSet<String>();

                                set.addAll(addArray);
                                edit.putStringSet("remote", set);
                                edit.commit();
                                Toast.makeText(getBaseContext(), selectedDevices + " " + selectedbrand + " Successfuly Added", Toast.LENGTH_LONG).show();

                                remotebtnadd.setVisibility(View.VISIBLE);
                                listview.setVisibility(INVISIBLE);
                                add_list.setVisibility(VISIBLE);
                            }
                        }
                    });

                }



            }
        });


    }




    public void btndevices (View view)
    {
        devicescreen.setVisibility(View.VISIBLE);
        remotescreen.setVisibility(View.INVISIBLE);
        offall.setVisibility(VISIBLE);

    }


    public void btnremote (View view)
    {
        devicescreen.setVisibility(View.INVISIBLE);
        remotescreen.setVisibility(View.VISIBLE);
        offall.setVisibility(INVISIBLE);

        listview.setVisibility(INVISIBLE);
        remotebtnadd.setVisibility(VISIBLE);
        add_list.setVisibility(VISIBLE);
        remotebuttons.setVisibility(INVISIBLE);
        dvdbuttons.setVisibility(INVISIBLE);
        projectorbuttons.setVisibility(INVISIBLE);
        airconbuttons.setVisibility(INVISIBLE);



/*



        //==========================================================
        //TV LIST VIEW
        //==========================================================

        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line,remote);
        listview.setAdapter(adapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                selectedDevices = parent.getItemAtPosition(position).toString();
                Toast.makeText(getBaseContext(),selectedDevices+ " is selected",Toast.LENGTH_LONG).show();
                selectedbrand = selectedDevices;

                if(selectedDevices.equals("TV")) {

                    adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_dropdown_item_1line, tv);
                    listview.setAdapter(adapter);

                    listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                            selectedDevices = parent.getItemAtPosition(position).toString();
                            Toast.makeText(getBaseContext(), selectedDevices + " is selected", Toast.LENGTH_LONG).show();



                            if(selectedbrand.equals("TV")){
                                listview.setVisibility(INVISIBLE);
                                remotebuttons.setVisibility(VISIBLE);
                            }
                        }
                    });
                }
                if(selectedDevices.equals("DVD PLAYER")) {

                    adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_dropdown_item_1line, dvd);
                    listview.setAdapter(adapter);

                    listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                            selectedDevices = parent.getItemAtPosition(position).toString();
                            Toast.makeText(getBaseContext(), selectedDevices + " is selected", Toast.LENGTH_LONG).show();

                            if(selectedbrand.equals("DVD PLAYER")){
                                listview.setVisibility(INVISIBLE);
                               dvdbuttons.setVisibility(VISIBLE);
                            }
                        }
                    });
                }
                if(selectedDevices.equals("PROJECTOR")) {

                    adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_dropdown_item_1line, projector);
                    listview.setAdapter(adapter);

                    listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                            selectedDevices = parent.getItemAtPosition(position).toString();
                            Toast.makeText(getBaseContext(), selectedDevices + " is selected", Toast.LENGTH_LONG).show();

                            if(selectedbrand.equals("PROJECTOR")) {
                                listview.setVisibility(INVISIBLE);
                                projectorbuttons.setVisibility(VISIBLE);
                            }
                        }
                    });
                }
                if(selectedDevices.equals("AIRCON")){

                    adapter = new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_dropdown_item_1line,aircon);
                    listview.setAdapter(adapter);

                    listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                            selectedDevices = parent.getItemAtPosition(position).toString();
                            Toast.makeText(getBaseContext(), selectedDevices + " is selected", Toast.LENGTH_LONG).show();

                            if(selectedbrand.equals("AIRCON")) {
                                listview.setVisibility(INVISIBLE);
                                airconbuttons.setVisibility(VISIBLE);
                            }
                        }
                    });

                }



            }
        });*/
        //===========================================================
    }

    //=======================================================================
    //TV BUTTONS
    //=======================================================================
    public void tvbtnpower (View view) {
        if (selectedDevices.equals("Sharp")){
            sendData("tvsharppower");
        }
        if (selectedDevices.equals("Toshiba")){
            sendData("tvtoshibapower");
        }
    }
    public void tvbtninput (View view) {
        if (selectedDevices.equals("Sharp")){
            sendData("tvsharpinput");
        }
        if (selectedDevices.equals("Toshiba")){
            sendData("tvtoshibainput");
        }

    }
    public void tvbtn1 (View view) {
        if (selectedDevices.equals("Sharp")){
            sendData("tvsharp1");
        }if (selectedDevices.equals("Toshiba")){
            sendData("tvtoshiba1");
        }
    }
    public void tvbtn2 (View view) {
        if (selectedDevices.equals("Sharp")){
            sendData("tvsharp2");
        }if (selectedDevices.equals("Toshiba")){
            sendData("tvtoshiba2");
        }
    }
    public void tvbtn3 (View view) {
        if (selectedDevices.equals("Sharp")){
            sendData("tvsharp3");
        }if (selectedDevices.equals("Toshiba")){
            sendData("tvtoshiba3");
        }
    }
    public void tvbtn4 (View view) {
        if (selectedDevices.equals("Sharp")){
            sendData("tvsharp4");
        }if (selectedDevices.equals("Toshiba")){
            sendData("tvtoshiba4");
        }
    }
    public void tvbtn5 (View view) {
        if (selectedDevices.equals("Sharp")){
            sendData("tvsharp5");
        }if (selectedDevices.equals("Toshiba")){
            sendData("tvtoshiba5");
        }
    }
    public void tvbtn6 (View view) {
        if (selectedDevices.equals("Sharp")){
            sendData("tvsharp6");
        }if (selectedDevices.equals("Toshiba")){
            sendData("tvtoshiba6");
        }
    }
    public void tvbtn7 (View view) {
        if (selectedDevices.equals("Sharp")){
            sendData("tvsharp7");
        }if (selectedDevices.equals("Toshiba")){
            sendData("tvtoshiba7");
        }
    }
    public void tvbtn8 (View view) {
        if (selectedDevices.equals("Sharp")){
            sendData("tvsharp8");
        }if (selectedDevices.equals("Toshiba")){
            sendData("tvtoshiba8");
        }
    }
    public void tvbtn9 (View view) {
        if (selectedDevices.equals("Sharp")){
            sendData("tvsharp9");
        }if (selectedDevices.equals("Toshiba")){
            sendData("tvtoshiba9");
        }
    }
    public void tvbtn0 (View view) {
        if (selectedDevices.equals("Sharp")){
            sendData("tvsharp0");
        }if (selectedDevices.equals("Toshiba")){
            sendData("tvtoshiba0");
        }
    }
    public void tvbtnmute (View view) {
        if (selectedDevices.equals("Sharp")){
            sendData("tvsharpmute");
        }if (selectedDevices.equals("Toshiba")){
            sendData("tvtoshibamute");
        }
    }
    public void tvbtnmenu (View view) {
        if (selectedDevices.equals("Sharp")){
            sendData("tvsharpmenu");
        }if (selectedDevices.equals("Toshiba")){
            sendData("tvtoshibamenu");
        }
    }
    public void tvbtnvolup (View view) {
        if (selectedDevices.equals("Sharp")){
            sendData("tvsharpvolup");
        }if (selectedDevices.equals("Toshiba")){
            sendData("tvtoshibavolup");
        }
    }
    public void tvbtnvoldown (View view) {
        if (selectedDevices.equals("Sharp")){
            sendData("tvsharpvoldown");
        }if (selectedDevices.equals("Toshiba")){
            sendData("tvtoshibavoldown");
        }
    }
    public void tvbtnchup (View view) {
        if (selectedDevices.equals("Sharp")){
            sendData("tvsharpchup");
        }if (selectedDevices.equals("Toshiba")){
            sendData("tvtoshibachup");
        }
    }
    public void tvbtnchdown (View view) {
        if (selectedDevices.equals("Sharp")){
            sendData("tvsharpchdown");
        }if (selectedDevices.equals("Toshiba")){
            sendData("tvtoshibachdown");
        }
    }
    //==========================================================================

    //=======================================================================
    //DVD BUTTONS
    //=======================================================================
    public void dvdeject (View view) {
        if (selectedDevices.equals("Pensonic")){
            sendData("dvdpensoniceject");
        }  if (selectedDevices.equals("Magus")){
            sendData("dvdmaguseject");
        }
    }
    public void dvdbtn0 (View view) {
        if (selectedDevices.equals("Pensonic")){
            sendData("dvdpensonic0");
        }if (selectedDevices.equals("Magus")){
            sendData("dvdmagus0");
        }
    }
    public void dvdbtn1 (View view) {
        if (selectedDevices.equals("Pensonic")){
            sendData("dvdpensonic1");
        }if (selectedDevices.equals("Magus")){
            sendData("dvdmagus1");
        }
    }
    public void dvdbtn2 (View view) {
        if (selectedDevices.equals("Pensonic")){
            sendData("dvdpensonic2");
        }if (selectedDevices.equals("Magus")){
            sendData("dvdmagus2");
        }
    }
    public void dvdbtn3 (View view) {
        if (selectedDevices.equals("Pensonic")){
            sendData("dvdpensonic3");
        }if (selectedDevices.equals("Magus")){
            sendData("dvdmagus3");
        }
    }
    public void dvdbtn4 (View view) {
        if (selectedDevices.equals("Pensonic")){
            sendData("dvdpensonic4");
        }if (selectedDevices.equals("Magus")){
            sendData("dvdmagus4");
        }
    }
    public void dvdbtn5 (View view) {
        if (selectedDevices.equals("Pensonic")){
            sendData("dvdpensonic5");
        }if (selectedDevices.equals("Magus")){
            sendData("dvdmagus5");
        }
    }
    public void dvdbtn6 (View view) {
        if (selectedDevices.equals("Pensonic")){
            sendData("dvdpensonic6");
        }if (selectedDevices.equals("Magus")){
            sendData("dvdmagus6");
        }
    }
    public void dvdbtn7 (View view) {
        if (selectedDevices.equals("Pensonic")){
            sendData("dvdpensonic7");
        }if (selectedDevices.equals("Magus")){
            sendData("dvdmagus7");
        }
    }
    public void dvdbtn8 (View view) {
        if (selectedDevices.equals("Pensonic")){
            sendData("dvdpensonic8");
        }if (selectedDevices.equals("Magus")){
            sendData("dvdmagus8");
        }
    }
    public void dvdbtn9 (View view) {
        if (selectedDevices.equals("Pensonic")){
            sendData("dvdpensonic9");
        }if (selectedDevices.equals("Magus")){
            sendData("dvdmagus9");
        }
    }
    public void dvdtenup (View view) {
        if (selectedDevices.equals("Pensonic")){
            sendData("dvdpensonictenup");
        }if (selectedDevices.equals("Magus")){
            sendData("dvdmagustenup");
        }
    }
    public void dvdvolup (View view) {
        if (selectedDevices.equals("Pensonic")){
            sendData("dvdpensonicvolup");
        }if (selectedDevices.equals("Magus")){
            sendData("dvdmagusvolup");
        }
    }
    public void dvdvoldown (View view) {
        if (selectedDevices.equals("Pensonic")){
            sendData("dvdpensonicvoldown");
        }if (selectedDevices.equals("Magus")){
            sendData("dvdmagusvoldown");
        }
    }
    public void dvdmute (View view) {
        if (selectedDevices.equals("Pensonic")){
            sendData("dvdpensonicmute");
        }if (selectedDevices.equals("Magus")){
            sendData("dvdmagusmute");
        }
    }
    public void dvdbtnup (View view) {
        if (selectedDevices.equals("Pensonic")){
            sendData("dvdpensonicup");
        }if (selectedDevices.equals("Magus")){
            sendData("dvdmagusup");
        }
    }
    public void dvdstop (View view) {
        if (selectedDevices.equals("Pensonic")){
            sendData("dvdpensonicstop");
        }if (selectedDevices.equals("Magus")){
            sendData("dvdmagusstop");
        }
    }
    public void dvdplay (View view) {
        if (selectedDevices.equals("Pensonic")){
            sendData("dvdpensonicplay");
        }if (selectedDevices.equals("Magus")){
            sendData("dvdmagusplay");
        }
    }
    public void dvdpause (View view) {
        if (selectedDevices.equals("Pensonic")){
            sendData("dvdpensonicpause");
        }if (selectedDevices.equals("Magus")){
            sendData("dvdmaguspause");
        }
    }
    public void dvdbtndown (View view) {
        if (selectedDevices.equals("Pensonic")){
            sendData("dvdpensonicdown");
        }if (selectedDevices.equals("Magus")){
            sendData("dvdmagusdown");
        }
    }
    public void dvdbtnright (View view) {
        if (selectedDevices.equals("Pensonic")){
            sendData("dvdpensonicright");
        }if (selectedDevices.equals("Magus")){
            sendData("dvdmagusright");
        }
    }
    public void dvdbtnleft (View view) {
        if (selectedDevices.equals("Pensonic")){
            sendData("dvdpensonicleft");
        }if (selectedDevices.equals("Magus")){
            sendData("dvdmagusleft");
        }
    }
    public void dvdbtnok (View view) {
        if (selectedDevices.equals("Pensonic")){
            sendData("dvdpensonicok");
        }if (selectedDevices.equals("Magus")){
            sendData("dvdmagusok");
        }
    }
    public void dvdprev (View view) {
        if (selectedDevices.equals("Pensonic")){
            sendData("dvdpensonicprev");
        }if (selectedDevices.equals("Magus")){
            sendData("dvdmagusprev");
        }
    }
    public void dvdnext (View view) {
        if (selectedDevices.equals("Pensonic")){
            sendData("dvdpensonicnext");
        }if (selectedDevices.equals("Magus")){
            sendData("dvdmagusnext");
        }
    }
    public void dvdrew (View view) {
        if (selectedDevices.equals("Pensonic")){
            sendData("dvdpensonicrew");
        }if (selectedDevices.equals("Magus")){
            sendData("dvdmagusrew");
        }
    }
    public void dvdff (View view) {
        if (selectedDevices.equals("Pensonic")){
            sendData("dvdpensonicff");
        }if (selectedDevices.equals("Magus")){
            sendData("dvdmagusff");
        }
    }
    public void dvdmenu (View view) {
        if (selectedDevices.equals("Pensonic")){
            sendData("dvdpensonicmenu");
        }if (selectedDevices.equals("Magus")){
            sendData("dvdmagusmenu");
        }
    }
    public void dvdpower (View view) {
        if (selectedDevices.equals("Pensonic")){
            sendData("dvdpensonicpower");
        }if (selectedDevices.equals("Magus")){
            sendData("dvdmaguspower");
        }
    }


    //=======================================================================
    //PROJECTOR BUTTONS
    //=======================================================================
    public void projbtnpower (View view) {
        if (selectedDevices.equals("Epson")){
            sendData("projepsonpower");
        }
    }
    public void projbtninput (View view) {
        if (selectedDevices.equals("Epson")){
            sendData("projepsoninput");
        }
    }
    public void projbtnup (View view) {
        if (selectedDevices.equals("Epson")){
            sendData("projepsonup");
        }
    }
    public void projbtnright (View view) {
        if (selectedDevices.equals("Epson")){
            sendData("projepsonright");
        }
    }
    public void projbtnexit (View view) {
        if (selectedDevices.equals("Epson")){
            sendData("projepsonexit");
        }
    }
    public void projbtndown (View view) {
        if (selectedDevices.equals("Epson")){
            sendData("projepsondown");
        }
    }
    public void projbtnleft (View view) {
        if (selectedDevices.equals("Epson")){
            sendData("projepsonleft");
        }
    }
    public void projbtnmenu (View view) {
        if (selectedDevices.equals("Epson")){
            sendData("projepsonmenu");
        }
    }
    public void projbtnok (View view) {
        if (selectedDevices.equals("Epson")){
            sendData("projepsonok");
        }
    }

    //=======================================================================
    //AIRCON BUTTONS
    //=======================================================================
    public void airconbtnfc (View view) {
        if (selectedDevices.equals("CARRIER")){
            sendData("airconcarrierfc");
        }
    }
    public void airconbtnfan (View view) {
        if (selectedDevices.equals("CARRIER")){
            sendData("airconcarrierfan");
        }
    }
    public void airconbtnmode (View view) {
        if (selectedDevices.equals("CARRIER")){
            sendData("airconcarriermode");
        }
    }
    public void airconbtntempdown (View view) {
        if (selectedDevices.equals("CARRIER")){
            sendData("airconcarriertempdown");
        }
    }
    public void airconbtntempup (View view) {
        if (selectedDevices.equals("CARRIER")){
            sendData("airconcarriertempup");
        }
    }
    public void airconbtnpower (View view) {
        if (selectedDevices.equals("CARRIER")){
            sendData("airconcarrierpower");
        }
    }
    //==========================================================================

    private String m_Text = "";

    public void btn_light1 (View view)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Device Name: (L1)");

        // Set up the input
        final EditText input = new EditText(this);
        // Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        input.setFilters(new InputFilter[] { new InputFilter.LengthFilter(20)});
        builder.setView(input);

        // Set up the buttons
        builder.setPositiveButton("SAVE", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //m_Text = input.getText().toString();
                SharedPreferences sharedPreferences=getSharedPreferences("SavedData", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor=sharedPreferences.edit();
                editor.putString("light1",m_Text = input.getText().toString());
                editor.commit();
                Toast.makeText(MainActivity.this,"Saved!",Toast.LENGTH_LONG).show();
                btnlight1.setText(m_Text);

                t1.setVisibility(View.VISIBLE);
                btnlight2.setVisibility(View.VISIBLE);
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();

    }

    public void btn_light2 (View view)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Device Name: (L2)");

        // Set up the input
        final EditText input = new EditText(this);
        // Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        input.setFilters(new InputFilter[] { new InputFilter.LengthFilter(20)});
        builder.setView(input);

        // Set up the buttons
        builder.setPositiveButton("SAVE", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //m_Text = input.getText().toString();
                SharedPreferences sharedPreferences=getSharedPreferences("SavedData", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor=sharedPreferences.edit();
                editor.putString("light2",m_Text = input.getText().toString());
                editor.commit();
                Toast.makeText(MainActivity.this,"Saved!",Toast.LENGTH_LONG).show();
                btnlight2.setText(m_Text);
              t2.setVisibility(View.VISIBLE);
                btnlight3.setVisibility(View.VISIBLE);
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();

    }

    public void btn_light3 (View view)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Device Name: (L3)");

        // Set up the input
        final EditText input = new EditText(this);
        // Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        input.setFilters(new InputFilter[] { new InputFilter.LengthFilter(20)});
        builder.setView(input);

        // Set up the buttons
        builder.setPositiveButton("SAVE", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //m_Text = input.getText().toString();
                SharedPreferences sharedPreferences=getSharedPreferences("SavedData", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor=sharedPreferences.edit();
                editor.putString("light3",m_Text = input.getText().toString());
                editor.commit();
                Toast.makeText(MainActivity.this,"Saved!",Toast.LENGTH_LONG).show();
                btnlight3.setText(m_Text);
               t3.setVisibility(View.VISIBLE);
                btnlight4.setVisibility(View.VISIBLE);
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();

    }

    public void btn_light4 (View view)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Device Name: (L4)");

        // Set up the input
        final EditText input = new EditText(this);
        // Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        input.setFilters(new InputFilter[] { new InputFilter.LengthFilter(20)});
        builder.setView(input);

        // Set up the buttons
        builder.setPositiveButton("SAVE", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //m_Text = input.getText().toString();
                SharedPreferences sharedPreferences=getSharedPreferences("SavedData", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor=sharedPreferences.edit();
                editor.putString("light4",m_Text = input.getText().toString());
                editor.commit();
                Toast.makeText(MainActivity.this,"Saved!",Toast.LENGTH_LONG).show();
                btnlight4.setText(m_Text);
              t4.setVisibility(View.VISIBLE);
                btndevice1.setVisibility(View.VISIBLE);
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();

    }

    public void btn_device1 (View view)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Device Name: (D1)");

        // Set up the input
        final EditText input = new EditText(this);
        // Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        input.setFilters(new InputFilter[] { new InputFilter.LengthFilter(20)});
        builder.setView(input);

        // Set up the buttons
        builder.setPositiveButton("SAVE", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //m_Text = input.getText().toString();
                SharedPreferences sharedPreferences=getSharedPreferences("SavedData", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor=sharedPreferences.edit();
                editor.putString("device1",m_Text = input.getText().toString());
                editor.commit();
                Toast.makeText(MainActivity.this,"Saved!",Toast.LENGTH_LONG).show();
                btndevice1.setText(m_Text);
               t5.setVisibility(View.VISIBLE);
                btndevice2.setVisibility(View.VISIBLE);
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();

    }

    public void btn_device2 (View view)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Device Name: (D2)");

        // Set up the input
        final EditText input = new EditText(this);
        // Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        input.setFilters(new InputFilter[] { new InputFilter.LengthFilter(20)});
        builder.setView(input);

        // Set up the buttons
        builder.setPositiveButton("SAVE", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //m_Text = input.getText().toString();
                SharedPreferences sharedPreferences=getSharedPreferences("SavedData", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor=sharedPreferences.edit();
                editor.putString("device2",m_Text = input.getText().toString());
                editor.commit();
                Toast.makeText(MainActivity.this,"Saved!",Toast.LENGTH_LONG).show();
                btndevice2.setText(m_Text);
               t6.setVisibility(View.VISIBLE);
                btndevice3.setVisibility(View.VISIBLE);
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();

    }

    public void btn_device3 (View view)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Device Name: (D3)");

        // Set up the input
        final EditText input = new EditText(this);
        // Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        input.setFilters(new InputFilter[] { new InputFilter.LengthFilter(20)});
        builder.setView(input);

        // Set up the buttons
        builder.setPositiveButton("SAVE", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //m_Text = input.getText().toString();
                SharedPreferences sharedPreferences=getSharedPreferences("SavedData", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor=sharedPreferences.edit();
                editor.putString("device3",m_Text = input.getText().toString());
                editor.commit();
                Toast.makeText(MainActivity.this,"Saved!",Toast.LENGTH_LONG).show();
                btndevice3.setText(m_Text);
               t7.setVisibility(View.VISIBLE);
                btndevice4.setVisibility(View.VISIBLE);
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();

    }

    public void btn_device4 (View view)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Device Name: (D4)");

        // Set up the input
        final EditText input = new EditText(this);
        // Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        input.setFilters(new InputFilter[] { new InputFilter.LengthFilter(20)});
        builder.setView(input);

        // Set up the buttons
        builder.setPositiveButton("SAVE", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //m_Text = input.getText().toString();
                SharedPreferences sharedPreferences=getSharedPreferences("SavedData", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor=sharedPreferences.edit();
                editor.putString("device4",m_Text = input.getText().toString());
                editor.commit();
                Toast.makeText(MainActivity.this,"Saved!",Toast.LENGTH_LONG).show();
                btndevice4.setText(m_Text);
              t8.setVisibility(View.VISIBLE);
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();

    }





    private BluetoothSocket createBluetoothSocket(BluetoothDevice device) throws IOException {
        if (Build.VERSION.SDK_INT >= 10) {
            try {
                final Method m = device.getClass().getMethod("createInsecureRfcommSocketToServiceRecord", new Class[]{UUID.class});
                return (BluetoothSocket) m.invoke(device, MY_UUID);
            } catch (Exception e) {
                Log.e(TAG, "Could not create Insecure RFComm Connection", e);
            }
        }
        return device.createRfcommSocketToServiceRecord(MY_UUID);
    }

    @Override
    public void onResume() {
        super.onResume();

        Log.d(TAG, "...onResume - try connect...");

        // Set up a pointer to the remote node using it's address.
        BluetoothDevice device = btAdapter.getRemoteDevice(address);

        // Two things are needed to make a connection:
        //   A MAC address, which we got above.
        //   A Service ID or UUID.  In this case we are using the
        //     UUID for SPP.

        try {
            btSocket = createBluetoothSocket(device);
        } catch (IOException e1) {
            errorExit("Fatal Error", "In onResume() and socket create failed: " + e1.getMessage() + ".");
        }

        // Discovery is resource intensive.  Make sure it isn't going on
        // when you attempt to connect and pass your message.
        btAdapter.cancelDiscovery();

        // Establish the connection.  This will block until it connects.
        Log.d(TAG, "...Connecting...");
        try {
            btSocket.connect();
            Log.d(TAG, "...Connection ok...");
        } catch (IOException e) {
            try {
                btSocket.close();
            } catch (IOException e2) {
                errorExit("Fatal Error", "In onResume() and unable to close socket during connection failure" + e2.getMessage() + ".");
            }
        }

        // Create a data stream so we can talk to server.
        Log.d(TAG, "...Create Socket...");

        try {
            outStream = btSocket.getOutputStream();
        } catch (IOException e) {
            errorExit("Fatal Error", "In onResume() and output stream creation failed:" + e.getMessage() + ".");
        }
    }

    @Override
    public void onPause() {
        super.onPause();

        Log.d(TAG, "...In onPause()...");

        if (outStream != null) {
            try {
                outStream.flush();
            } catch (IOException e) {
                errorExit("Bluetooth Error: ", "Make sure the bluetooth is on. ");
            }
        }

        try {
            btSocket.close();
        } catch (IOException e2) {
            errorExit("Fatal Error", "In onPause() and failed to close socket." + e2.getMessage() + ".");
        }
    }

    private void checkBTState() {
        // Check for Bluetooth support and then check to make sure it is turned on
        // Emulator doesn't support Bluetooth and will return null
        if (btAdapter == null) {
            errorExit("Fatal Error", "Bluetooth not support");
        } else {
            if (btAdapter.isEnabled()) {
                Log.d(TAG, "...Bluetooth ON...");
            } else {
                //Prompt user to turn on Bluetooth
                Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                startActivityForResult(enableBtIntent, 1);
            }
        }
    }

    private void errorExit(String title, String message) {
        Toast.makeText(getBaseContext(), title + " - " + message, Toast.LENGTH_LONG).show();
        finish();
    }

    private void sendData(String message) {
        byte[] msgBuffer = message.getBytes();

        Log.d(TAG, "...Send data: " + message + "...");

        try {
            outStream.write(msgBuffer);
        } catch (IOException e) {
            String msg = "Please check device's Bluetooth Module: " + e.getMessage();
            if (address.equals("00:00:00:00:00:00"))
                msg = msg + ".\n\nUpdate your server address from 00:00:00:00:00:00 to the correct address on line 35 in the java code";
          //  msg = msg + ".\n\nCheck that the SPP UUID: " + MY_UUID.toString() + " exists on server.\n\n";

            errorExit("Connection Failed: ", msg);
        }
    }




/*    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean t1) {

        if(t1)
        {

            //r.setBackgroundColor(Color.BLACK);
            sendData("B");
            Toast.makeText(getBaseContext(), "Turn on LED", Toast.LENGTH_SHORT).show();
        }
        else
        {
            //r.setBackgroundColor(Color.WHITE);
            sendData("b");
            Toast.makeText(getBaseContext(), "Turn off LED", Toast.LENGTH_SHORT).show();
        }

    }*/




}

