package com.example.albert.sqlite;

import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText editText;
    TextView textView;
    MyDBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = (EditText) findViewById(R.id.editText);
        textView = (TextView) findViewById(R.id.textView);
        dbHandler = new MyDBHandler(this, null, null, 1);
        printDatabase();
    }

    public void addButtonClicked(View view){
        Products product = new Products(editText.getText().toString());
        dbHandler.addProduct(product);
        printDatabase();
    }

    public void deleteButtonClicked(View view){
        String name = editText.getText().toString();
        dbHandler.deleteProduct(name);
        printDatabase();
    }

    //Print database on screen
    public void printDatabase(){
        String dbString = dbHandler.databaseToString();
        textView.setText(dbString);
        editText.setText("");
    }
}
