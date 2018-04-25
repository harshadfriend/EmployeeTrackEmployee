package beit.employee.employeetrackemployee;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Help extends AppCompatActivity {

    TextView tvProfile,tvAttend,tvLocation,tvSettings,tvLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

        tvProfile=findViewById(R.id.tvProfile);
        tvAttend=findViewById(R.id.tvAttendance);
        tvLocation=findViewById(R.id.tvLocation);
        tvSettings=findViewById(R.id.tvSettings);
        tvLogin=findViewById(R.id.tvLogin);

        if(Settings.i==1){
            tvProfile.setTextSize(15);
            tvAttend.setTextSize(15);
            tvLogin.setTextSize(15);
            tvLocation.setTextSize(15);
            tvSettings.setTextSize(15);
        }
        if(Settings.i==2){
            tvProfile.setTextSize(18);
            tvAttend.setTextSize(18);
            tvLogin.setTextSize(18);
            tvLocation.setTextSize(18);
            tvSettings.setTextSize(18);
        }
        if(Settings.i==3){
            tvProfile.setTextSize(21);
            tvAttend.setTextSize(21);
            tvLogin.setTextSize(21);
            tvLocation.setTextSize(21);
            tvSettings.setTextSize(21);
        }
    }
}
