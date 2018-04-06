package beit.employee.employeetrackemployee;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class Attendance extends AppCompatActivity {
    ListView lvAttend;
    ArrayAdapter<String> adp;
    Firebase firebase;
    String dburl="https://employeetracking-1caec.firebaseio.com/";
    DatabaseReference dbRef;
    String imei;
    String str="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance);

        Firebase.setAndroidContext(this);
        firebase=new Firebase(dburl);
        dbRef = FirebaseDatabase.getInstance().getReference();


        Bundle extras=getIntent().getExtras();
        imei=extras.getString("imei");

        lvAttend=findViewById(R.id.lvAttend);


        lvAttend=(ListView)findViewById(R.id.lvAttend);
        adp=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1);
        adp.setNotifyOnChange(true);
        lvAttend.setAdapter(adp);

        Query q=dbRef.child("employee").child("attendance").child(imei);
        q.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot data:dataSnapshot.getChildren()) {
                    str+=data.getKey();
                    for (DataSnapshot d : data.getChildren()) {
                        fbase f = data.getValue(fbase.class);
                        str+=d.getKey();
//                    adp.add(f.getName()+" "+f.getAddress()+"\n"+f.getMobile());
                    }
                }
                Toast.makeText(Attendance.this, ""+str, Toast.LENGTH_SHORT).show();
                lvAttend.setAdapter(adp);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        lvAttend.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
              //  MapsActivity.Name=String.valueOf(parent.getItemAtPosition(position));
                //startActivity(new Intent(getApplicationContext(),MapsActivity.class));
                //Toast.makeText(EmployeeList.this, "test"+parent.getItemAtPosition(position), Toast.LENGTH_SHORT).show();
            }
        });

    }
    }

