package ru.kalievmars.sharedpreferencesexample;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Set;

public class MainActivity extends AppCompatActivity  {

    EditText editText;
    Button btnSave, btnLoad;
    SharedPreferences sharedPreferences;
    final String SAVED_TEXT = "saved text";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.editText);
        btnSave = findViewById(R.id.btnSave);
        btnLoad = findViewById(R.id.btnLoad);

//        btnSave.setOnClickListener(this);
//        btnLoad.setOnClickListener(this);

        loadText();
        editText.setOnEditorActionListener(editorActionListener);
    }

    private TextView.OnEditorActionListener editorActionListener = new TextView.OnEditorActionListener() {
        @Override
        public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
            saveText();
            Log.d(SAVED_TEXT, editText.getText().toString());
            return true;
        }
    };

//    @Override
//    public void onClick(View v) {
//        switch (v.getId()) {
//            case R.id.btnSave:
//                saveText();
//                break;
//            case R.id.btnLoad:
//                loadText();
//                break;
//            default:
//                break;
//        }
//    }

    private void loadText() {
        sharedPreferences = getPreferences(MODE_PRIVATE);
        String text = sharedPreferences.getString(SAVED_TEXT, "Ничего не найдено");
        editText.setText(text);
        Toast.makeText(this, "Текст загружен!", Toast.LENGTH_LONG).show();
    }

    private void saveText() {
        sharedPreferences = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor ed = sharedPreferences.edit();
        ed.putString(SAVED_TEXT, editText.getText().toString());
        ed.commit();
        Toast.makeText(this, "Текст сохранен!", Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        saveText();
    }
}