package edu.birzeit.project;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import androidx.appcompat.app.ActionBar;


public class MainActivity extends AppCompatActivity {
    TextView wel;
    private static int Splash_timeout = 4000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        wel = findViewById(R.id.textView1);
        ActionBar action = getSupportActionBar();
        action.hide();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent splashintent=new Intent(MainActivity.this, HomeActivity.class);
                startActivity(splashintent);
                finish();
            }
        }, Splash_timeout);
        Animation myan = AnimationUtils.loadAnimation(MainActivity.this, R.anim.anination2);
        wel.startAnimation(myan);

    }


}