package com.example.messenger;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.SignInButton;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    Button enter;
    TextView login;
    TextView password;
    TextView check;
    DatabaseReference myRef = FirebaseDatabase.getInstance().getReference();
    String NAME_DATABASE = "user";

    String getPassword = "";
    String LOGIN = "";
    String PASSWORD = "";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        enter = (Button)findViewById(R.id.login);
        login = (TextView) findViewById(R.id.username);
        password = (TextView) findViewById(R.id.password);
        check = (TextView) findViewById(R.id.check);
        final User user = (User) getApplicationContext();

        enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LOGIN = login.getText().toString();
                PASSWORD = password.getText().toString();
                if ((LOGIN.isEmpty()) || (PASSWORD.isEmpty())){
                    Toast.makeText(getApplicationContext(), "Заполните поля! ", Toast.LENGTH_SHORT).show();
                    return;
                }
                else {

                    DatabaseReference mostafa = myRef.child(NAME_DATABASE).child(LOGIN);
                    mostafa.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            String value = dataSnapshot.getValue(String.class);
                            getPassword = value;
                            System.out.println("==============" + value);
                            System.out.println("++++++++++++"+ PASSWORD);
                            System.out.println("++++++++++++"+ getPassword);
                            System.out.println("++++++++++++"+ PASSWORD.equals(getPassword));

                            if (PASSWORD.equals(getPassword)){

                                user.setLogin(LOGIN);
                                user.setPassword(PASSWORD);


                                startActivity(new Intent(MainActivity.this, Main2Activity.class));
                            }
                            else {
                                login.setText("");
                                password.setText("");
                                Toast.makeText(getApplicationContext(), "Не верный логин или пароль! ", Toast.LENGTH_SHORT).show();
                                return;
                            }

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });



                }




            }
        });

//        myRef.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//
//                String value = dataSnapshot.getValue(String.class);
//                System.out.println("=============="+ value);
//
////                if (dataSnapshot.exists()) {
////                    int i = 0;
////                    for(DataSnapshot d : dataSnapshot.getChildren()) {
////                        LOGIN= d.getKey();
////                        System.out.println(d.getValue());
////                        System.out.println("================================" + LOGIN+ "  " + PASSWORD);
////                        i++;
////                    }
////                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });



        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, chackIn.class));
            }
        });


    }


}
