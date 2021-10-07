package com.example.androidintents;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    ImageView ivMood, ivPhone, ivWeb, ivLocation;
    Button btnCreateContact;
    final int CREATE_CONTACT = 1;
    String name = "", phoneNumber = "", webAddress = "", location = "", mood = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ivMood = findViewById(R.id.ivMood);
        ivPhone = findViewById(R.id.ivPhone);
        ivWeb = findViewById(R.id.ivWeb);
        ivLocation = findViewById(R.id.ivLocation);
        btnCreateContact = findViewById(R.id.btnCreateContact);

        ivMood.setVisibility(View.GONE);
        ivPhone.setVisibility(View.GONE);
        ivWeb.setVisibility(View.GONE);
        ivLocation.setVisibility(View.GONE);

        ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == Activity.RESULT_OK) {
                            // There are no request codes
                            Intent data = result.getData();

                            ivMood.setVisibility(View.VISIBLE);
                            ivPhone.setVisibility(View.VISIBLE);
                            ivWeb.setVisibility(View.VISIBLE);
                            ivLocation.setVisibility(View.VISIBLE);

                            name = data.getStringExtra("name");
                            phoneNumber = data.getStringExtra("phoneNumber");
                            webAddress = data.getStringExtra("webAddress");
                            location = data.getStringExtra("location");
                            mood = data.getStringExtra("mood");

                            if (mood.equals("happy")) {
                                ivMood.setImageResource(R.drawable.ic_happy);
                            } else if (mood.equals("neutral")) {
                                ivMood.setImageResource(R.drawable.ic_neutral);
                            } else {
                                ivMood.setImageResource(R.drawable.ic_sad);
                            }
                        } else {
                            Toast.makeText(MainActivity.this, "No data was received!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

        btnCreateContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,
                        com.example.androidintents.CreateContactActivity.class);
                activityResultLauncher.launch(intent);
            }
        });

        ivPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phoneNumber));
                startActivity(intent);
            }
        });

        ivWeb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://" + webAddress));
                startActivity(intent);
            }
        });

        ivLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:0,0?q=" + location));
                startActivity(intent);
            }
        });
    }
}