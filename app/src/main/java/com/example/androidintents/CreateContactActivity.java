package com.example.androidintents;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class CreateContactActivity extends AppCompatActivity implements View.OnClickListener {

    EditText etName, etPhoneNumber, etWebAddress, etLocation;
    ImageView ivHappy, ivNeutral, ivSad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_contact);

        etName = findViewById(R.id.etName);
        etPhoneNumber = findViewById(R.id.etPhoneNumber);
        etWebAddress = findViewById(R.id.etWebAddress);
        etLocation = findViewById(R.id.etLocation);

        ivHappy = findViewById(R.id.ivHappy);
        ivNeutral = findViewById(R.id.ivNeutral);
        ivSad = findViewById(R.id.ivSad);

        ivHappy.setOnClickListener(this);
        ivNeutral.setOnClickListener(this);
        ivSad.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (etName.getText().toString().isEmpty() || etPhoneNumber.getText().toString().isEmpty() ||
                etWebAddress.getText().toString().isEmpty() || etLocation.getText().toString().isEmpty()) {
            Toast.makeText(this, "Please enter all fields!", Toast.LENGTH_SHORT).show();
        } else {
            Intent intent = new Intent();
            intent.putExtra("name", etName.getText().toString().trim());
            intent.putExtra("phoneNumber", etPhoneNumber.getText().toString().trim());
            intent.putExtra("webAddress", etWebAddress.getText().toString().trim());
            intent.putExtra("location", etLocation.getText().toString().trim());

            if (view.getId() == R.id.ivHappy) {
                intent.putExtra("mood", "happy");
            } else if (view.getId() == R.id.ivNeutral) {
                intent.putExtra("mood", "neutral");
            } else {
                intent.putExtra("mood", "sad");
            }

            setResult(RESULT_OK, intent);
            CreateContactActivity.this.finish();
        }
    }
}