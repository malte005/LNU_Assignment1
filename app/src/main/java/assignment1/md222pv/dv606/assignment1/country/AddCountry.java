package assignment1.md222pv.dv606.assignment1.country;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputFilter;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import assignment1.md222pv.dv606.assignment1.R;
import assignment1.md222pv.dv606.assignment1.utils.InputMinMax;
import assignment1.md222pv.dv606.assignment1.utils.YearHelper;

public class AddCountry extends AppCompatActivity {

    EditText addCountry;
    EditText addYear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_country);

        addCountry = (EditText) findViewById(R.id.addCountry);
        addYear = (EditText) findViewById(R.id.addYear);
        addYear.setFilters(new InputFilter[]{new InputMinMax("1", String.valueOf(YearHelper.getYear()))});
        addYear.setHint(String.valueOf(YearHelper.getYear()));

        //get focus
        addCountry.requestFocus();
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(addCountry, InputMethodManager.SHOW_IMPLICIT);
    }

    public void addEntry(View view) {

        String country = addCountry.getText().toString();
        String year = addYear.getText().toString();

        Intent returnIntent = new Intent();
        returnIntent.putExtra("country", country);
        returnIntent.putExtra("year", year);
        setResult(Activity.RESULT_OK, returnIntent);
        finish();
    }
}
