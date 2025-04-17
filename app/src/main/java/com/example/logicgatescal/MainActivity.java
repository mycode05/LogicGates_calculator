package com.example.logicgatescal;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText num1EditText, num2EditText;
    private Button andButton, orButton, xorButton;
    private TextView resultTextView;
    private RadioGroup numeralSystemRadioGroup;
    private String numeralSystem = "Binary";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        num1EditText = findViewById(R.id.num1EditText);
        num2EditText = findViewById(R.id.num2EditText);
        andButton = findViewById(R.id.andButton);
        orButton = findViewById(R.id.orButton);
        xorButton = findViewById(R.id.xorButton);
        resultTextView = findViewById(R.id.resultTextView);
        numeralSystemRadioGroup = findViewById(R.id.numeralSystemRadioGroup);

        numeralSystemRadioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId == R.id.radioBinary) numeralSystem = "Binary";
            else if (checkedId == R.id.radioOctal) numeralSystem = "Octal";
            else if (checkedId == R.id.radioHexadecimal) numeralSystem = "Hexadecimal";
        });

        andButton.setOnClickListener(v -> performOperation("AND"));
        orButton.setOnClickListener(v -> performOperation("OR"));
        xorButton.setOnClickListener(v -> performOperation("XOR"));
    }

    private void performOperation(String operation) {
        String num1Str = num1EditText.getText().toString();
        String num2Str = num2EditText.getText().toString();

        if (num1Str.isEmpty() || num2Str.isEmpty()) {
            Toast.makeText(this, "Please enter both numbers", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            int num1 = parseNumber(num1Str);
            int num2 = parseNumber(num2Str);
            int result = 0;

            switch (operation) {
                case "AND": result = num1 & num2; break;
                case "OR": result = num1 | num2; break;
                case "XOR": result = num1 ^ num2; break;
            }

            resultTextView.setText("Result: " + formatNumber(result));
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Invalid input for selected numeral system", Toast.LENGTH_SHORT).show();
        }
    }

    private int parseNumber(String number) {
        switch (numeralSystem) {
            case "Binary": return Integer.parseInt(number, 2);
            case "Octal": return Integer.parseInt(number, 8);
            case "Hexadecimal": return Integer.parseInt(number, 16);
            default: throw new NumberFormatException();
        }
    }

    private String formatNumber(int number) {
        switch (numeralSystem) {
            case "Binary": return Integer.toBinaryString(number);
            case "Octal": return Integer.toOctalString(number);
            case "Hexadecimal": return Integer.toHexString(number).toUpperCase();
            default: return String.valueOf(number);
        }
    }
}
