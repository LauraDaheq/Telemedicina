package com.example.telemedicina;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class SurveyActivity extends AppCompatActivity {

    private CheckBox cbFever, cbCough, cbBreath, cbLoss, cbSore, cbFatigue, cbComorbidity;
    private TextView resultView;
    private Button btnEvaluate, btnCall, btnEmail;
    private String eps;
    private int age;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_survey);

        cbFever = findViewById(R.id.cbFever);
        cbCough = findViewById(R.id.cbCough);
        cbBreath = findViewById(R.id.cbBreath);
        cbLoss = findViewById(R.id.cbLoss);
        cbSore = findViewById(R.id.cbSore);
        cbFatigue = findViewById(R.id.cbFatigue);
        cbComorbidity = findViewById(R.id.cbComorbidity);
        resultView = findViewById(R.id.txtResult);
        btnEvaluate = findViewById(R.id.btnEvaluate);
        btnCall = findViewById(R.id.btnCall);
        btnEmail = findViewById(R.id.btnEmail);

        eps = getIntent().getStringExtra("eps");
        age = getIntent().getIntExtra("age", 0);

        btnEvaluate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RiskAssessor.RiskLevel level = RiskAssessor.assess(
                        cbFever.isChecked(), cbCough.isChecked(), cbBreath.isChecked(),
                        cbLoss.isChecked(), cbSore.isChecked(), cbFatigue.isChecked(),
                        age, cbComorbidity.isChecked());

                resultView.setText("Nivel de riesgo: " + level.name());

                // Enable contact buttons according to availability
                EPSMapper.Contact contact = EPSMapper.getContact(SurveyActivity.this, eps);
                if (contact == null) {
                    Toast.makeText(SurveyActivity.this, "Contacto de EPS no encontrado.", Toast.LENGTH_LONG).show();
                    btnCall.setEnabled(false);
                    btnEmail.setEnabled(false);
                } else {
                    btnCall.setEnabled(true);
                    btnEmail.setEnabled(true);
                }

                // Show advisory dialog if high risk
                if (level == RiskAssessor.RiskLevel.HIGH) {
                    new AlertDialog.Builder(SurveyActivity.this)
                            .setTitle("Atención")
                            .setMessage("Se ha detectado un nivel de riesgo alto. Se recomienda contactar a su EPS o acudir a urgencias.")
                            .setPositiveButton("Aceptar", null)
                            .show();
                }
            }
        });

        btnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EPSMapper.Contact contact = EPSMapper.getContact(SurveyActivity.this, eps);
                if (contact != null && contact.phone != null) {
                    Notifier.callPhone(SurveyActivity.this, contact.phone);
                }
            }
        });

        btnEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EPSMapper.Contact contact = EPSMapper.getContact(SurveyActivity.this, eps);
                if (contact != null && contact.email != null) {
                    String summary = buildSummary();
                    Notifier.sendEmail(SurveyActivity.this, contact.email, "Reporte de síntomas", summary);
                }
            }
        });
    }

    private String buildSummary() {
        StringBuilder sb = new StringBuilder();
        sb.append("Síntomas:\n");
        if (cbFever.isChecked()) sb.append("- Fiebre\n");
        if (cbCough.isChecked()) sb.append("- Tos\n");
        if (cbBreath.isChecked()) sb.append("- Dificultad para respirar\n");
        if (cbLoss.isChecked()) sb.append("- Pérdida de olfato/gusto\n");
        if (cbSore.isChecked()) sb.append("- Dolor de garganta\n");
        if (cbFatigue.isChecked()) sb.append("- Fatiga\n");
        sb.append("Edad: ").append(age).append("\n");
        sb.append("Comorbilidad: ").append(cbComorbidity.isChecked() ? "Sí" : "No");
        return sb.toString();
    }
}
