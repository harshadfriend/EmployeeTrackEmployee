package beit.employee.employeetrackemployee;


import android.content.Context;
import android.content.Intent;

import android.content.pm.PackageManager;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;


import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.firebase.client.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import static android.Manifest.permission.READ_CONTACTS;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity {

    TelephonyManager telephonyManager;
    AutoCompleteTextView actvUname;
    EditText etPassword;
    Button btnLogin;

    Firebase firebase;
    String dburl = "https://employeetracking-1caec.firebaseio.com/";
    DatabaseReference dbref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Set up the login form.
        Firebase.setAndroidContext(LoginActivity.this);
        firebase = new Firebase(dburl);
        dbref = FirebaseDatabase.getInstance().getReference();

        Query q = dbref.child("employee").child("profile");
        q.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    Log.d("studio", data.getKey());
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);

        actvUname = findViewById(R.id.actvUname);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(LoginActivity.this, ""+telephonyManager.getImei(), Toast.LENGTH_SHORT).show();

                Query q = dbref.child("employee").child("profile").orderByChild("imei").equalTo(telephonyManager.getImei());
                q.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for(DataSnapshot data:dataSnapshot.getChildren()){
                            fbase f=data.getValue(fbase.class);
                            if(f.getUname().equals(actvUname.getText().toString()) &&
                                    f.getPwd().equals(etPassword.getText().toString())){
                                //Toast.makeText(LoginActivity.this, "Login Successful !", Toast.LENGTH_SHORT).show();
                                Intent i=new Intent(LoginActivity.this,WelcomeScreen.class);
                                i.putExtra("name",f.getName());
                                i.putExtra("mobile",f.getMobile());
                                i.putExtra("address",f.getAddress());
                                i.putExtra("imei",f.getImei());
                                //Toast.makeText(LoginActivity.this, "Login true debug", Toast.LENGTH_SHORT).show();
                                startActivity(i);
                                finish();
                            }
                            else
                                Snackbar.make(getCurrentFocus(),"Invalid Username or Password !",Snackbar.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
//                startActivity(new Intent(LoginActivity.this,Home.class));
//                finish();
            }
        });
    }


}

