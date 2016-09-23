package assignment1.md222pv.dv606.assignment1.weather;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import assignment1.md222pv.dv606.assignment1.R;
import assignment1.md222pv.dv606.assignment1.utils.NetworkHelper;

public class WeatherActivity extends AppCompatActivity {

    private WeatherReport report = null;

    private RecyclerView mRecyclerView;
    private WeatherAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<WeatherForecast> forecastList = new ArrayList<>();

    private TextView city, lastUpdate, nextUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

        // Initialize RecyclerView
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mAdapter = new WeatherAdapter(forecastList);
        mLayoutManager = new LinearLayoutManager(getApplicationContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        lastUpdate = (TextView) findViewById(R.id.lastUpdate);
        nextUpdate = (TextView) findViewById(R.id.nextUpdate);
        city = (TextView) findViewById(R.id.city);

        if (NetworkHelper.isOnline(this)) {
            try {
                URL url = new URL("http://www.yr.no/sted/Sverige/Kronoberg/V%E4xj%F6/forecast.xml");
                AsyncTask task = new WeatherRetriever().execute(url);
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        } else {
            Toast.makeText(this, "No internet connection", Toast.LENGTH_SHORT).show();
            city.setText("Please enable internet connection and restart");
            CardView cv = (CardView)findViewById(R.id.card_view_general);
            cv.setCardBackgroundColor(R.color.colorAccent);
        }
    }

    /**
     * fill forecast-list from the report and notify the adapter
     */
    private void prepareData() {
        for (WeatherForecast fcast : report) {
            forecastList.add(fcast);
        }
        mAdapter.notifyDataSetChanged();
    }

    private class WeatherRetriever extends AsyncTask<URL, Void, WeatherReport> {
        protected WeatherReport doInBackground(URL... urls) {
            try {
                return WeatherHandler.getWeatherReport(urls[0]);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        protected void onProgressUpdate(Void... progress) {
        }

        protected void onPostExecute(WeatherReport result) {
            Toast.makeText(getApplicationContext(), "WeatherRetriever task finished", Toast.LENGTH_LONG).show();

            report = result;

            setGeneralData();
            prepareData();
        }
    }

    /**
     * prepare head-card with general information
     */
    private void setGeneralData() {
        lastUpdate.append("Last server-sided weather update: " + report.getLastUpdated());
        nextUpdate.append("Next server-sided weather update: " + report.getNextUpdate());
        city.append("Forecast for: " + report.getCity() + " / " + report.getCountry());
    }

}
