package beit.employee.employeetrackemployee;

import android.graphics.Color;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.firebase.client.Firebase;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    double x=0,y=0;
    public static String Name="";
    Firebase firebase;
    String dburl="https://employeetracking-1caec.firebaseio.com/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        Firebase.setAndroidContext(this);
        firebase = new Firebase(dburl);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
//Toast.makeText(this, ""+Name, Toast.LENGTH_SHORT).show();
        mMap = googleMap;
        //mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
        DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference();
        //Query query=dbRef.child("person").orderByChild("name").equalTo(etSearch.getText().toString());
//        Query query = dbRef.child("siot").orderByChild("name").equalTo("seema");
//        Query query = dbRef.child("employee").child(Name).orderByChild("name").equalTo(Name);

        Query query = dbRef.child("employee").child("livelocation").orderByChild("name").equalTo(Name);
        query.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
            @Override
            public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {

                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    fbase pson = data.getValue(fbase.class);

                    x=Double.parseDouble(pson.getlat());  //parse the values to double format from string
                    y=Double.parseDouble(pson.getlon());  //parse the values to double format from string

                    //  Toast.makeText(MapsActivity.this,""+x+" "+y, Toast.LENGTH_SHORT).show();  //show coordinates

                    // mMap.clear();  //clear previous markers

                    //mMap.setMaxZoomPreference(5);
                    //mMap.addMarker(new MarkerOptions().position(new LatLng(x,y)).title("You are here !"));  //add marker to desired position
                    mMap.addCircle(new CircleOptions().center(new LatLng(x,y)).radius(3).fillColor(Color.RED).strokeColor(Color.RED));
                    mMap.addPolyline(new PolylineOptions().add(new LatLng(x,y)).color(Color.RED).width(5));
                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(x,y),17));  //move camera along with zoom level, here 17
                    //onMapReady();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                //Toast.makeText(busGrid.this, "Error", Toast.LENGTH_SHORT).show();

            }
        });

        Query query1 = dbRef.child("vehicle").child("livelocation").orderByChild("name").equalTo(Name);
        query1.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
            @Override
            public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {

                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    fbase pson = data.getValue(fbase.class);

                    x=Double.parseDouble(pson.getlat());  //parse the values to double format from string
                    y=Double.parseDouble(pson.getlon());  //parse the values to double format from string

                    //  Toast.makeText(MapsActivity.this,""+x+" "+y, Toast.LENGTH_SHORT).show();  //show coordinates

                    // mMap.clear();  //clear previous markers

                    //mMap.setMaxZoomPreference(5);
                    //mMap.addMarker(new MarkerOptions().position(new LatLng(x,y)).title("You are here !"));  //add marker to desired position
                    mMap.addCircle(new CircleOptions().center(new LatLng(x,y)).radius(3).fillColor(Color.RED).strokeColor(Color.RED));
                    mMap.addPolyline(new PolylineOptions().add(new LatLng(x,y)).color(Color.BLUE).width(5));
                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(x,y),17));  //move camera along with zoom level, here 17
                    //onMapReady();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                //Toast.makeText(busGrid.this, "Error", Toast.LENGTH_SHORT).show();

            }
        });
        // Add a marker in Sydney and move the camera
        //LatLng sydney = new LatLng(x, 351);
        //mMap.animateCamera(CameraUpdateFactory.newLatLng(new LatLng()));
//        mMap.addMarker(new MarkerOptions().position(new LatLng(20,21)).title("You are here !"));
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(20,21)));
        /*x=19.55;y=73.22;
        mMap.addMarker(new MarkerOptions().position(new LatLng(x,y)).title("You are here !"));
        x=19.65;y=73.22;
        mMap.addMarker(new MarkerOptions().position(new LatLng(x,y)).title("You are here !"));
        x=19.75;y=73.22;
//        mMap.addMarker(new MarkerOptions().position(new LatLng(x,y)).title("You are here !"));
        mMap.addCircle(new CircleOptions().center(new LatLng(x,y)).radius(100).fillColor(Color.CYAN).strokeColor(Color.GREEN));
        //mMap.addPolyline(new PolylineOptions().add(new LatLng(x,y)).color(Color.GREEN).addAll());
        x=19.85;y=73.22;
        mMap.addMarker(new MarkerOptions().position(new LatLng(x,y)).title("You are here !"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(x,y),10));*/
    }
}
