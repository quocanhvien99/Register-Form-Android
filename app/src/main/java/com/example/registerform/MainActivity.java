package com.example.registerform;

import android.app.DatePickerDialog;
import android.icu.text.SimpleDateFormat;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    public Button datePickerButton, submit;
    public TextView validationNotice;

    DatePickerDialog.OnDateSetListener date;
    Calendar calendar = Calendar.getInstance();
    EditText firstNameEditText, lastNameEditText, emailEditText, addressEditText;
    TextView birthdayTextView;
    RadioButton radMale, radFemale;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        birthdayTextView = findViewById(R.id.birthday);
        firstNameEditText = findViewById(R.id.fistName);
        lastNameEditText = findViewById(R.id.lastName);
        emailEditText = findViewById(R.id.email);
        addressEditText = findViewById(R.id.address);
        validationNotice = findViewById(R.id.validationNotice);
        radMale = findViewById(R.id.radMale);
        radFemale = findViewById(R.id.radFemale);

        submit = findViewById(R.id.submit);
        datePickerButton = findViewById(R.id.datePickerButton);

        date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, month);
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }
        };

        datePickerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(MainActivity.this,
                        date,
                        calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)
                ).show();
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String currtext = "";
                if (firstNameEditText.getText().toString().matches("")) {
                    currtext += "Please Enter Your First Name \n";
                }
                if (lastNameEditText.getText().toString().matches("")) {
                    currtext += "Please Enter Your Last Name \n";
                }
                if (!radMale.isChecked() && !radFemale.isChecked()) {
                    currtext += "Please Choose Your Gender \n";
                }
                if (birthdayTextView.getText().toString().matches("")) {
                    currtext += "Please Enter Your Birthday \n";
                }
                if (emailEditText.getText().toString().matches("")) {
                    currtext += "Please Enter Your Email \n";
                }
                if (emailEditText.getText().toString().matches("")) {
                    currtext += "Please Enter Your Address \n";
                }
                validationNotice.setText(currtext);
            }
        });

    }

    private void updateLabel() {
        String myFormat = "YYYY-MM-dd"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        birthdayTextView.setText(sdf.format(calendar.getTime()));
    }
}
