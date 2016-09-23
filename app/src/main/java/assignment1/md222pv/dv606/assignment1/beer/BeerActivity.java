package assignment1.md222pv.dv606.assignment1.beer;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import assignment1.md222pv.dv606.assignment1.R;

public class BeerActivity extends AppCompatActivity {

    FragmentPagerAdapter adapterViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beer);

        ViewPager vpPager = (ViewPager) findViewById(R.id.vpPager);
        adapterViewPager = new MyPagerAdapter(getSupportFragmentManager());
        vpPager.setAdapter(adapterViewPager);
    }

    public static class MyPagerAdapter extends FragmentPagerAdapter {
        private static int NUM_ITEMS = 7;

        public MyPagerAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        // Returns total number of pages
        @Override
        public int getCount() {
            return NUM_ITEMS;
        }

        // Returns the fragment to display for that page
        @Override
        public Fragment getItem(int position) {

            switch (position) {
                case 0:
                    return BeerFragment.newInstance(0, BeerStrings.ZERO.toString(), R.drawable.morning_delight);
                case 1:
                    return BeerFragment.newInstance(1, BeerStrings.ONE.toString(), R.drawable.good_morning);
                case 2:
                    return BeerFragment.newInstance(2, BeerStrings.TWO.toString(), R.drawable.pliny_the_younger);
                case 3:
                    return BeerFragment.newInstance(3, BeerStrings.THREE.toString(), R.drawable.heady_topper);
                case 4:
                    return BeerFragment.newInstance(4, BeerStrings.FOUR.toString(), R.drawable.kentucky_brunch_brand_stout);
                case 5:
                    return BeerFragment.newInstance(5, BeerStrings.FIVE.toString(), R.drawable.russian_river_pliny_the_elder);
                case 6:
                    return BeerFragment.newInstance(6, BeerStrings.SIX.toString(), R.drawable.norrlands);
                default:
                    return null;
            }
        }

        // Returns the page title for the top indicator
        @Override
        public CharSequence getPageTitle(int position) {
            return (position + 1) + ". Beer out of 7";
        }
    }
}
