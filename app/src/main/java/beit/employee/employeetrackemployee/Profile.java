package beit.employee.employeetrackemployee;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Profile extends AppCompatActivity {

    TextView tvName,tvMobile,tvAddress,tvImei;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        tvName=findViewById(R.id.tvName);
        tvAddress=findViewById(R.id.tvAddress);
        tvMobile=findViewById(R.id.tvMobile);
        tvImei=findViewById(R.id.tvImei);

        tvName.setText(Home.name);
        tvAddress.setText(Home.address);
        tvMobile.setText(Home.mobile);
        tvImei.setText(Home.ime);
    }
}
