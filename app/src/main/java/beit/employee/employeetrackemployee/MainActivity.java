package beit.employee.employeetrackemployee;

import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.firebase.client.Firebase;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    Button btnLoginLogout;
    int i;
    String dburl="https://employeetracking-1caec.firebaseio.com/";
    int day,month,year;
    int hour,minute,sec;
    TelephonyManager telephonyManager;
    String latitude,longitude;
    String time;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        LocationManager lm=(LocationManager)getSystemService(Context.LOCATION_SERVICE);


        Firebase.setAndroidContext(this);
        final Firebase fbref=new Firebase(dburl);
        final fbase obj=new fbase();
        final fbase obj2=new fbase();

        telephonyManager=(TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);


        i = 0;

        Calendar cal=Calendar.getInstance();
        day=cal.get(Calendar.DAY_OF_MONTH);
        month=cal.get(Calendar.MONTH);
        year=cal.get(Calendar.YEAR);



        final String date=day+"-"+(month+1)+"-"+year;



        btnLoginLogout = (Button) findViewById(R.id.btnLoginLogout);
        btnLoginLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (i == 0) {
                    LocationManager locationManager=(LocationManager)getSystemService(Context.LOCATION_SERVICE);
                    if(!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)){
                        startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
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
                                obj2.setName("Emp1");
                                fbref.child("Employee").child("emp1").child("livelocation").setValue(obj2);
                                fbref.child("Employee").child("emp1").child(date).child("Login "+time).setValue(obj);
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
                    fbref.child("Employee").child("emp1").child(date).child("Logout "+time).setValue(obj);
                    i = 0;
                }
            }
        });

    }
}