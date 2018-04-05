package beit.employee.employeetrackemployee;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.firebase.client.Firebase;

import java.util.Calendar;

public class Home extends AppCompatActivity {

    Button btnProfile,btnSettings,btnHelp,btnLoginLogout,btnAttend,btnLocation;
    int day,month,year;
    int hour,minute,sec;
    TelephonyManager telephonyManager;
    String latitude,longitude;
    String time;
    int i;
    Firebase fbref;
    String dburl="https://employeetracking-1caec.firebaseio.com/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Firebase.setAndroidContext(this);
        final Firebase fbref=new Firebase(dburl);
        final fbase obj=new fbase();
        final fbase obj2=new fbase();

        btnProfile=findViewById(R.id.btnProfile);
        btnSettings=findViewById(R.id.btnSettings);
        btnHelp=findViewById(R.id.btnHelp);
        btnLoginLogout=findViewById(R.id.btnLoginLogout);
        btnAttend=findViewById(R.id.btnAttnd);
        btnLocation=findViewById(R.id.btnLocation);



        telephonyManager=(TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);


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
                startActivity(new Intent(Home.this,Attendance.class));
            }
        });

        btnLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                startActivity(new Intent(Home.this,Settings.class));
            }
        });

        btnLoginLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (i == 0) {
                    LocationManager locationManager=(LocationManager)getSystemService(Context.LOCATION_SERVICE);
                    if(!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)){
                        startActivity(new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS));
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
                                fbref.child("employee").child(telephonyManager.getImei()).child("livelocation").setValue(obj2);
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
                        fbref.child("employee").child(telephonyManager.getImei()).child(date).child("Login "+time).setValue(obj);
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
                    fbref.child("employee").child(telephonyManager.getImei()).child(date).child("Logout "+time).setValue(obj);
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
