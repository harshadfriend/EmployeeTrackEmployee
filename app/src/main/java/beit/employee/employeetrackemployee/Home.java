package beit.employee.employeetrackemployee;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.firebase.client.Firebase;
import com.firebase.client.Query;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;

public class Home extends AppCompatActivity {

    Button btnProfile, btnSettings, btnHelp, btnLoginLogout, btnAttend, btnLocation;
    int day, month, year;
    int hour, minute, sec;

    String latitude, longitude, imei;
    String time;
    int i;

    TelephonyManager telephonyManager;
    DatabaseReference dbref;
    Firebase fbref;
    fbase obj;
    fbase obj2;
    String dburl = "https://employeetracking-1caec.firebaseio.com/";
    public static String name,address,mobile,ime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //  Snackbar.make(findViewById(android.R.id.content),"Login Successful !",Snackbar.LENGTH_SHORT).show();

        /*Bundle extras=getIntent().getExtras();
        name=extras.getString("name");
        ime=extras.getString("imei");
        address=extras.getString("address");
        mobile=extras.getString("mobile");*/

        Firebase.setAndroidContext(this);
        fbref = new Firebase(dburl);
        dbref = FirebaseDatabase.getInstance().getReference();
        telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);

        com.google.firebase.database.Query q = dbref.child("employee").child("profile").orderByChild("imei").equalTo(telephonyManager.getImei());
        q.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot data:dataSnapshot.getChildren()){
                    fbase f=data.getValue(fbase.class);
                    name=f.getName();
                    ime=f.getImei();
                    address=f.getAddress();
                    mobile=f.getMobile();
                    }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        obj = new fbase();
        obj2 = new fbase();

        btnProfile = findViewById(R.id.btnProfile);
        btnSettings = findViewById(R.id.btnSettings);
        btnHelp = findViewById(R.id.btnHelp);
        btnLoginLogout = findViewById(R.id.btnLoginLogout);
        btnAttend = findViewById(R.id.btnAttnd);
        btnLocation = findViewById(R.id.btnLocation);

        if(Settings.i==1){
            btnProfile.setTextSize(15);
            btnAttend.setTextSize(15);
            btnLocation.setTextSize(15);
            btnSettings.setTextSize(15);
            btnHelp.setTextSize(15);
            btnLoginLogout.setTextSize(15);
        }

        if(Settings.i==2){
            btnProfile.setTextSize(18);
            btnAttend.setTextSize(18);
            btnLocation.setTextSize(18);
            btnSettings.setTextSize(18);
            btnHelp.setTextSize(18);
            btnLoginLogout.setTextSize(18);
        }


        telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);


        imei = telephonyManager.getImei();

        i = 0;

        Calendar cal=Calendar.getInstance();
        day=cal.get(Calendar.DAY_OF_MONTH);
        month=cal.get(Calendar.MONTH);
        year=cal.get(Calendar.YEAR);



        final String date=day+"-"+(month+1)+"-"+year;

        btnProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Home.this,Profile.class));
            }
        });

        btnSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(new Intent(Home.this,Settings.class));

            }
        });

        btnHelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Home.this,Help.class));

            }
        });

        btnAttend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Home.this,Attendance.class);
                i.putExtra("imei",imei);
                startActivity(i);
            }
        });

        btnLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MapsActivity.Name=imei;
                startActivity(new Intent(Home.this,MapsActivity.class));
            }
        });

        btnLoginLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (i == 0) {
                    LocationManager locationManager=(LocationManager)getSystemService(Context.LOCATION_SERVICE);
                    if(!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)){
                        new AlertDialog.Builder(Home.this).setTitle("GPS not Enabled !").
                                setMessage("Enable GPS ?").
                                setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        startActivity(new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                                    }
                                }).
                                setNegativeButton("No", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {

                                    }
                                }).show();
                    }
                    //Toast.makeText(getBaseContext(), ""+telephonyManager.getImei(), Toast.LENGTH_SHORT).show();
                    else{
                        btnLoginLogout.setText("Logout");

                        Calendar c=Calendar.getInstance();
                        hour=c.get(Calendar.HOUR_OF_DAY);
                        minute=c.get(Calendar.MINUTE);
                        sec=c.get(Calendar.SECOND);
                        time=hour+"-"+minute+"-"+sec;
                        //obj.settime(DateFormat.getDateTimeInstance().format(new Date()));
//                    obj.settime(time);
                   /* obj.setSession("Login");
                    obj.setImei(""+telephonyManager.getImei());*/

                        LocationListener ll=new LocationListener() {
                            @Override
                            public void onLocationChanged(Location location) {
                                latitude=String.valueOf(location.getLatitude()).substring(0,7);
                                longitude=String.valueOf(location.getLongitude()).substring(0,7);



                                obj.setlat(" "+latitude);
                                obj.setlon(" "+longitude);

                                obj2.setlat("" + latitude);
                                obj2.setlon("" + longitude);
                               obj2.setName(telephonyManager.getImei());
                                fbref.child("employee").child("livelocation").child(telephonyManager.getImei()).setValue(obj2);
//                                fbref.child("employee").child(telephonyManager.getImei()).child(date).child("Login "+time).setValue(obj);

                            }

                            @Override
                            public void onStatusChanged(String s, int i, Bundle bundle) {

                            }

                            @Override
                            public void onProviderEnabled(String s) {

                            }

                            @Override
                            public void onProviderDisabled(String s) {

                            }
                        };
                        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,5000,10,ll);
                        Log.d("before fbref","test "+date+" "+time+" "+telephonyManager.getImei());
                        fbref.child("employee").child("attendance").child(telephonyManager.getImei()).child(date).child("Login "+time).setValue(obj);
                        i = 1;

                    }
                }
                else {
                    //Toast.makeText(getBaseContext(), ""+telephonyManager.getImei(), Toast.LENGTH_SHORT).show();
                    btnLoginLogout.setText("Login");
                    Calendar c=Calendar.getInstance();
                    hour=c.get(Calendar.HOUR_OF_DAY);
                    minute=c.get(Calendar.MINUTE);
                    sec=c.get(Calendar.SECOND);
                    String time=hour+"-"+minute+"-"+sec;
                    //obj.settime(DateFormat.getDateTimeInstance().format(new Date()));
//                    obj.settime(time);
                    /*obj.setSession("Logout");
                    obj.setImei(""+telephonyManager.getImei());*/
                    obj.setlat(" "+latitude);
                    obj.setlon(" "+longitude);
                    fbref.child("employee").child("attendance").child(telephonyManager.getImei()).child(date).child("Logout "+time).setValue(obj);
                    i = 0;
                }



            }});


    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(Home.this).setTitle("Logout ?")
                .setMessage("Are you sure, you want to logout and close this app ?")
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                }).show();

    }
}
