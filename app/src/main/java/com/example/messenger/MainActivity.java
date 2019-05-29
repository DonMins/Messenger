package com.example.messenger;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button enter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        enter = (Button)findViewById(R.id.login);
        enter.setOnClickListener(this);


    }

    public void onClick(View v){
        Intent intent = new Intent(this,Main2Activity.class);
        startActivity(intent);
    }

}
