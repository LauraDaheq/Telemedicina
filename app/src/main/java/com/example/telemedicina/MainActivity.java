package com.example.telemedicina;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText nameInput, ageInput;
    private Spinner epsSpinner;
    private Button startBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nameInput = findViewById(R.id.editName);
        ageInput = findViewById(R.id.editAge);
        epsSpinner = findViewById(R.id.spinnerEps);
        startBtn = findViewById(R.id.btnStart);

        // Sample EPS list; user can edit eps.json later
        String[] epsList = new String[]{"SaludTotal", "Sura", "Coomeva"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, epsList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        epsSpinner.setAdapter(adapter);

        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, SurveyActivity.class);
                i.putExtra("name", nameInput.getText().toString());
                String ageStr = ageInput.getText().toString();
                int age = 0;
                try { age = Integer.parseInt(ageStr); } catch (NumberFormatException ignored) {}
                i.putExtra("age", age);
                i.putExtra("eps", epsSpinner.getSelectedItem().toString());
                startActivity(i);
            }
        });
    }
}
