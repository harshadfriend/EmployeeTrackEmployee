package beit.employee.employeetrackemployee;

import android.content.Intent;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class WelcomeScreen extends AppCompatActivity {

    String name,imei,address,mobile;
    TextView tvname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_screen);

        Snackbar.make(findViewById(android.R.id.content),"Login Successful !",Snackbar.LENGTH_LONG).show();

        Bundle extras=getIntent().getExtras();
        name=extras.getString("name");
        imei=extras.getString("imei");
        address=extras.getString("address");
        mobile=extras.getString("mobile");



        tvname=findViewById(R.id.tvName);
        tvname.setText(name);
        tvname.setTextColor(Color.rgb(0,175,0));

        new CountDownTimer(5000, 1000) {
            @Override
            public void onTick(long l) {

            }

            @Override
            public void onFinish() {
                finish();
                Intent i=new Intent(WelcomeScreen.this,Home.class);
          /*      i.putExtra("name",name);
                i.putExtra("mobile",mobile);
                i.putExtra("address",address);
                i.putExtra("imei",imei);*/
                startActivity(i);
            }
        }.start();

    }
}
