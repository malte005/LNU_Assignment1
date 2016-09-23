package assignment1.md222pv.dv606.assignment1.weather;

/**
 * Created by damma on 04.09.2016.
 */

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import assignment1.md222pv.dv606.assignment1.R;

public class WeatherAdapter extends RecyclerView.Adapter<WeatherAdapter.MyViewHolder> {

    private List<WeatherForecast> forecastList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public CardView cv;
        public TextView temperature, rain, date, time, wind;
        public ImageView icon;

        public MyViewHolder(View view) {
            super(view);
            cv = (CardView) view.findViewById(R.id.card_view);
            temperature = (TextView) view.findViewById(R.id.temperature);
            rain = (TextView) view.findViewById(R.id.rain);
            date = (TextView) view.findViewById(R.id.date);
            time = (TextView) view.findViewById(R.id.time);
            wind = (TextView) view.findViewById(R.id.windspeed);
            icon = (ImageView) view.findViewById(R.id.icon);
        }
    }

    public WeatherAdapter(List<WeatherForecast> forecastList) {
        this.forecastList = forecastList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.weather_card_row, parent, false);

        return new MyViewHolder(itemView);
    }

    private static boolean isBetween(int x, int lower, int upper) {
        return lower <= x && x <= upper;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        WeatherForecast weather = forecastList.get(position);

        holder.temperature.setText(String.valueOf(weather.getTemperature()) + "°");
        holder.rain.setText(String.valueOf(weather.getRain()) + " mm");
        holder.date.setText(String.valueOf(weather.getStartYYMMDD()));
        holder.time.setText(String.valueOf(weather.getStartHHMM() + " / " + weather.getEndHHMM()));
        holder.wind.setText("Windspeed: " + weather.getWindSpeed());

        /*
        int code = weather.getWeatherCode();

        if (code == 1) {
            holder.icon.setImageResource(R.drawable.sun);
        } else if (isBetween(code, 2, 3)) {
            holder.icon.setImageResource(R.drawable.light_cloudy);
        }else if (code == 4) {
            holder.icon.setImageResource(R.drawable.cloud);
        }
*/

        switch (weather.getWeatherName()) {
            case "Lettskyet":
                holder.icon.setImageResource(R.drawable.light_cloudy);
                break;
            case "Skyet":
                holder.icon.setImageResource(R.drawable.cloud);
                break;
            case "Klarvær":
                holder.icon.setImageResource(R.drawable.sun);
                break;
            case "Lette regnbyger":
                holder.icon.setImageResource(R.drawable.drops);
                break;
            case "Regnbyger":
                holder.icon.setImageResource(R.drawable.rain);
                break;
            case "Kraftige regnbyger":
                holder.icon.setImageResource(R.drawable.rain);
                break;
            default:
                holder.icon.setImageResource(R.drawable.light_cloudy);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return forecastList.size();
    }
}