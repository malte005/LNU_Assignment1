package assignment1.md222pv.dv606.assignment1.country;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import assignment1.md222pv.dv606.assignment1.R;

public class CountryActivity extends AppCompatActivity {

    private List<Country> countryList = new ArrayList<>();
    private RecyclerView recyclerView;
    private CountryAdapter cAdapter;
    RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        cAdapter = new CountryAdapter(countryList);
        mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(cAdapter);

        prepareMovieData();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 1) {
            if (resultCode == Activity.RESULT_CANCELED || data.getStringExtra("country").isEmpty() || data.getStringExtra("year").isEmpty()) {
                Toast.makeText(this, "Nothing stored...", Toast.LENGTH_SHORT).show();
            } else if (resultCode == Activity.RESULT_OK) {
                String country = data.getStringExtra("country");
                String year = data.getStringExtra("year");

                Country temp = new Country(country, year);
                countryList.add(temp);
                cAdapter.notifyDataSetChanged();
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.option_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add_item:
                Intent intent = new Intent(this, AddCountry.class);
                startActivityForResult(intent, 1);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void prepareMovieData() {
        Country country = new Country("Sweden", "2015");
        countryList.add(country);

        country = new Country("Germany", "2014");
        countryList.add(country);

        country = new Country("Mars", "2000");
        countryList.add(country);

        cAdapter.notifyDataSetChanged();
    }
}
