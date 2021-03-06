package beit.employee.employeetrackemployee;

import android.graphics.Color;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
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
    ArrayAdapter<String> adp,adp2;
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


        lvAttend=(ListView)findViewById(R.id.lvAttend);


        adp=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1){
            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

                View view =super.getView(position, convertView, parent);
                TextView textView=(TextView) view.findViewById(android.R.id.text1);
                if(Settings.i==1){
                    textView.setTextSize(15);
                }
                else if(Settings.i==2){
                    textView.setTextSize(18);
                }
                else
                    textView.setTextSize(21);
            /*YOUR CHOICE OF COLOR*/
  //              textView.setTextColor(Color.WHITE);
//                textView.setTypeface(null, Typeface.BOLD);

                return view;

            }
        };
        adp.setNotifyOnChange(true);

        adp2=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1);
        adp2.setNotifyOnChange(true);

        lvAttend.setAdapter(adp);

        Query q=dbRef.child("employee").child("attendance").child(imei);
        q.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                adp.clear();
                for(DataSnapshot data:dataSnapshot.getChildren()) {
                    str="";
                    str+="Date: "+data.getKey()+"\n"+"------------------------------";
                    for (DataSnapshot d : data.getChildren()) {
                        fbase f = data.getValue(fbase.class);
                        str+="\n"+d.getKey();
//                    adp.add(f.getName()+" "+f.getAddress()+"\n"+f.getMobile());
                    }
                    str+="\n";
                    adp.add(str);
                }
              //  Toast.makeText(Attendance.this, ""+dataSnapshot.getChildrenCount(), Toast.LENGTH_SHORT).show();
                if(dataSnapshot.getChildrenCount()>0)
                    lvAttend.setAdapter(adp);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        Query q2=dbRef.child("vehicle").child("attendance").child(imei);
        q2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                adp2.clear();
                for(DataSnapshot data:dataSnapshot.getChildren()) {
                    str="";
                    str+="Date: "+data.getKey()+"\n"+"------------------------------";
                    for (DataSnapshot d : data.getChildren()) {
                        fbase f = data.getValue(fbase.class);
                        str+="\n"+d.getKey();
//                    adp.add(f.getName()+" "+f.getAddress()+"\n"+f.getMobile());
                    }
                    str+="\n";
                    adp2.add(str);
                }
             //   Toast.makeText(Attendance.this, "test"+dataSnapshot.getChildrenCount(), Toast.LENGTH_SHORT).show();
                if(dataSnapshot.getChildrenCount()>0)
                    lvAttend.setAdapter(adp2);
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

