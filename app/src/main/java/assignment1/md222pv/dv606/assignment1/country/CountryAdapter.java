package assignment1.md222pv.dv606.assignment1.country;

/**
 * Created by damma on 31.08.2016.
 */

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import assignment1.md222pv.dv606.assignment1.R;

public class CountryAdapter extends RecyclerView.Adapter<CountryAdapter.MyViewHolder> {

    private List<Country> countryList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, year;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.name);
            year = (TextView) view.findViewById(R.id.year);
        }
    }

    public CountryAdapter(List<Country> countryList) {
        this.countryList = countryList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.country_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Country country = countryList.get(position);
        holder.title.setText(country.getName());
        holder.year.setText(country.getYear());
    }

    @Override
    public int getItemCount() {
        return countryList.size();
    }
}
