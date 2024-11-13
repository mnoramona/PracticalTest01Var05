package ro.pub.cs.systems.eim.practicaltest01var05;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

public class PracticalTest01Var05MainActivity extends AppCompatActivity {

    private EditText editText;
    private StringBuilder textBuilder = new StringBuilder();
    private int totalButtonClicks = 0; // Contor pentru numărul total de apăsări
    private static final String TOTAL_CLICKS_KEY = "total_clicks"; // Cheia pentru salvarea/restaurarea stării

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test01_var05_main);

        // Restaurare valoare totalButtonClicks dacă este cazul
        if (savedInstanceState != null) {
            totalButtonClicks = savedInstanceState.getInt(TOTAL_CLICKS_KEY, 0);
            Toast.makeText(this, "Total button clicks restored: " + totalButtonClicks, Toast.LENGTH_LONG).show();
        }

        editText = findViewById(R.id.editTextText);
        Button topLeftButton = findViewById(R.id.top_left_button);
        Button topRightButton = findViewById(R.id.top_right_button);
        Button centerButton = findViewById(R.id.center_button);
        Button bottomLeftButton = findViewById(R.id.bottom_left_button);
        Button bottomRightButton = findViewById(R.id.bottom_right_button);

        topLeftButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                handleButtonClick("Top Left");
            }
        });
        topRightButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                handleButtonClick("Top Right");
            }
        });
        centerButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                handleButtonClick("Center");
            }
        });
        bottomLeftButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                handleButtonClick("Bottom Left");
            }
        });
        bottomRightButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                handleButtonClick("Bottom Right");
            }
        });
    }

    private void handleButtonClick(String text) {
        concatenateText(text);
        totalButtonClicks++;
    }

    private void concatenateText(String text) {
        String currentText = editText.getText().toString();
        if (!currentText.equals(textBuilder.toString())) {
            textBuilder = new StringBuilder(currentText);
        }

        // Adaugă noul text
        if (textBuilder.length() > 0) {
            textBuilder.append(", ");
        }
        textBuilder.append(text);
        editText.setText(textBuilder.toString());
    }

    // Salvarea stării activității
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(TOTAL_CLICKS_KEY, totalButtonClicks); // Salvare valoare totalButtonClicks
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        totalButtonClicks = savedInstanceState.getInt(TOTAL_CLICKS_KEY, 0); // Restaurare valoare

        Log.d("Lifecycle", "onRestoreInstanceState called with totalButtonClicks: " + totalButtonClicks); // Verificare

        // Afișare Toast pentru a arăta valoarea restaurată
        Toast.makeText(this, "Total button clicks restored: " + totalButtonClicks, Toast.LENGTH_LONG).show();
    }

}
