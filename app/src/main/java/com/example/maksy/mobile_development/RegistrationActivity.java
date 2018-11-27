package com.example.maksy.mobile_development;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class RegistrationActivity extends AppCompatActivity {
    EditText firstNameEditText;
    EditText lastNameEditText;
    EditText phoneEditText;
    EditText passwordEditText;
    EditText emailEditText;
    EditText confirmPasswordEditText;
    private SharedPreferences sp;
    private SharedPreferences.Editor spEditor;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        sp  = getSharedPreferences("app_settings", Context.MODE_PRIVATE);
        initFields();

    }
    private void saveInfo() {
        final String lastName = lastNameEditText.getText().toString();
        final String firstName = firstNameEditText.getText().toString();
        final String phone = phoneEditText.getText().toString();
        spEditor = sp.edit();
        String list = sp.getString("entry_list", "");
        list += firstName + "|" + lastName + "|" + phone + "&";
        spEditor.putString("entry_list", list);
        spEditor.apply();
        firstNameEditText.setText("");
        lastNameEditText.setText("");
        emailEditText.setText("");
        phoneEditText.setText("");
        passwordEditText.setText("");
        confirmPasswordEditText.setText("");
    }
    private void initFields() {
        firstNameEditText = findViewById(R.id.fname);
        lastNameEditText = findViewById(R.id.lname);
        phoneEditText = findViewById(R.id.phone);
        passwordEditText = findViewById(R.id.password);
        emailEditText = findViewById(R.id.email);
        confirmPasswordEditText = findViewById(R.id.cpassword);
    }

    public void register(View view) {
        final String firstName = firstNameEditText.getText().toString();
        if(!isValidFirstName(firstName)) {
            firstNameEditText.setError("Некореткне ім'я");
        }

        final String lastName = lastNameEditText.getText().toString();
        if(!isValidLastName(lastName)) {
            lastNameEditText.setError("Некореткне прізвище");
        }

        final String email = emailEditText.getText().toString();
        if (!isValidEmail(email)) {
            emailEditText.setError("Неправильно введена пошта");
        }
        final String pass = passwordEditText.getText().toString();
        if (!isValidPassword(pass)) {
            passwordEditText.setError("Пароль повинен бути більше 6 символів");
        }
        final String phone = phoneEditText.getText().toString();
        if (!isValidPhone(phone)) {
            phoneEditText.setError("Неправильно введений номер");
        }
        if (!confirmPasswordEditText.equals(passwordEditText)) {
            confirmPasswordEditText.setError("Перевірте чи паролі співпадають");
        }
        else {
            saveInfo();
        }
    }

    //validating email
    private boolean isValidEmail(String email) {
        Pattern EMAIL_PATTERN = Patterns.EMAIL_ADDRESS;
        Pattern pattern = Pattern.compile(String.valueOf(EMAIL_PATTERN));
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    // validating password
    private boolean isValidPassword(String pass) {
        if (pass.length() < 6) {
            return false;
        }
        final String PASSWORD_PATTERN = "[a-zA-Z0-9]{6,20}";
        Pattern pattern = Pattern.compile(PASSWORD_PATTERN);
        Matcher matcher = pattern.matcher(pass);
        return matcher.matches();
    }

    //validating phone
    private boolean isValidPhone(String phone) {
        String PHONE_PATTERN = "[0-9]{10}";
        Pattern pattern = Pattern.compile(PHONE_PATTERN);
        Matcher matcher = pattern.matcher(phone);
        return matcher.matches();
    }
    // validate firstName
    private boolean isValidFirstName(String firstName) {
        String FIRST_NAME_PATTERN = "[a-z]{1,20}";
        Pattern pattern = Pattern.compile(FIRST_NAME_PATTERN);
        Matcher matcher = pattern.matcher(firstName);
        return matcher.matches();
    }
    //validateLastName
    private boolean isValidLastName(String lastName) {
        String LAST_NAME_PATTERN = "[a-zA-Z]{1,20}";
        Pattern pattern = Pattern.compile(LAST_NAME_PATTERN);
        Matcher matcher = pattern.matcher(lastName);
        return matcher.matches();
    }
    public void onViewList (View view) {
        Intent i = new Intent(this, ListActivity.class);
        startActivity(i);
    }
}

