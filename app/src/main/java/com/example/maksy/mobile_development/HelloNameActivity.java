package com.example.maksy.mobile_development;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class HelloNameActivity extends AppCompatActivity {
     EditText name;
     TextView textView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hello_name);
        name = findViewById(R.id.edittext);
        textView = findViewById(R.id.text);
    }
    public void clickButton(View view) {
        String text = name.getText().toString();
        textView.setText(String.format("Hello %s", text));
    }

    public void clearButton(View view) {
        name.setText("");
    }
}