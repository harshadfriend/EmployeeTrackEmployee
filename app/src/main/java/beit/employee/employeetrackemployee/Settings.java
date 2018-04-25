package beit.employee.employeetrackemployee;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.RadioButton;

public class Settings extends AppCompatActivity {

    RadioButton rbOne,rbTwo,rbThree;
    public static int i=3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        rbOne=findViewById(R.id.rbOne);
        rbTwo=findViewById(R.id.rbTwo);
        rbThree=findViewById(R.id.rbThree);

        rbOne.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                i=1;
            }
        });

        rbTwo.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                i=2;
            }
        });

        rbThree.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                i=3;
            }
        });
    }

    @Override
    public void onBackPressed() {
        finish();
        startActivity(new Intent(Settings.this,Home.class));
    }
}
