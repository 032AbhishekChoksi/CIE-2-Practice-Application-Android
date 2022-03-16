package com.abhishekchoksi.cie2practiceapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.AlertDialog;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {

    TextView textViewLogin;
    String CHANNEL_ID = "group_1";
    String CHANNEL_NAME = "group";
    Integer NOTIFICATION_ID = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        textViewLogin = findViewById(R.id.textViewLogin);

        textViewLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                builder.setTitle("Login User");
                LayoutInflater factory = LayoutInflater.from(LoginActivity.this);
                View customView = factory.inflate(R.layout.fragment_login_dialog,null);
                builder.setView(customView);

                AlertDialog alertDialog = builder.create();
                alertDialog.show();

                customView.findViewById(R.id.btn_fragment).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        EditText editTextName = customView.findViewById(R.id.editTextName);
                        EditText editTextPassword = customView.findViewById(R.id.editTextPassword);

                        String Name = editTextName.getText().toString();
                        String Password = editTextPassword.getText().toString();

                        if(Name.equals("Admin") && Password.equals("123"))
                        {
                            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                                NotificationChannel channel = new NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT);
                                NotificationManager manager = getSystemService(NotificationManager.class);
                                manager.createNotificationChannel(channel);
                            }

                            NotificationCompat.Builder builder =
                                    new NotificationCompat.Builder(LoginActivity.this, CHANNEL_ID);
                            builder.setContentTitle("Login user");
                            builder.setContentText("Admin Login Successfully");
                            builder.setSmallIcon(R.drawable.ic_launcher_background);
                            builder.setAutoCancel(true);

                            NotificationManagerCompat managerCompat = NotificationManagerCompat.from(LoginActivity.this);
                            managerCompat.notify(NOTIFICATION_ID, builder.build());
                            startActivity(new Intent(LoginActivity.this,MainActivity.class));
                        }
                        alertDialog.dismiss();
                    }
                });
            }
        });
    }
}