package assignment1.md222pv.dv606.assignment1.beer;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import assignment1.md222pv.dv606.assignment1.R;


public class BeerFragment extends Fragment {

    private int page, image;
    private String text;

    // newInstance constructor for creating fragment with arguments
    public static BeerFragment newInstance(int page, String text, int imgId) {

        BeerFragment beerFrag = new BeerFragment();
        Bundle args = new Bundle();

        args.putInt("number", page);
        args.putString("text", text);
        args.putInt("img", imgId);

        beerFrag.setArguments(args);
        return beerFrag;
    }

    // Store instance variables based on arguments passed
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        page = getArguments().getInt("number", 0);
        text = getArguments().getString("text");
        image = getArguments().getInt("img");
    }

    // Inflate the view for the fragment based on layout XML
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.beer_fragment, container, false);

        ImageView img = (ImageView) view.findViewById(R.id.beerImage);
        TextView lbl = (TextView) view.findViewById(R.id.beerText);

        img.setImageResource(image);
        lbl.setText(text);

        return view;
    }
}

