package assignment1.md222pv.dv606.assignment1.bmi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import assignment1.md222pv.dv606.assignment1.R;

public class BmiActivity extends AppCompatActivity {

    EditText length;
    EditText weight;
    TextView txtBmi;
    double bmi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi);
    }

    public void compute(View v){
        txtBmi = (TextView)findViewById(R.id.txtBmi);
        length = (EditText)findViewById(R.id.editLenght);
        weight = (EditText)findViewById(R.id.editWeight);

        String l = length.getText().toString();
        String w = weight.getText().toString();

        if(l.matches("") || w.matches("")){
            Toast.makeText(this, "Dude, fill the forms...", Toast.LENGTH_SHORT).show();
        }else {
            double l_m = Double.parseDouble(l) / 100;
            double w_kg = Double.parseDouble(w);

            bmi = Math.round((w_kg / (l_m * l_m) * 100));
            bmi = bmi / 100;
            txtBmi.setText("BMI: " + bmi);
        }
    }

    public void reset(View v){
        txtBmi = (TextView)findViewById(R.id.txtBmi);
        length = (EditText)findViewById(R.id.editLenght);
        weight = (EditText)findViewById(R.id.editWeight);
        txtBmi.setText("BMI");
        weight.setText("");
        length.setText("");
    }
}
