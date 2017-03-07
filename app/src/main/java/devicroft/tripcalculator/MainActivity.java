package devicroft.tripcalculator;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

public class MainActivity extends AppCompatActivity {


    TextView result_cost;
    TextView result_consumption;
    EditText input_distance;
    EditText input_price;
    EditText input_efficiency;

    Boolean canCalculate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

  



    }

    private void setupUi(){
        result_cost = (TextView) findViewById(R.id.result_cost);
        result_consumption = (TextView) findViewById(R.id.result_fuel_consumed);
        input_distance = (EditText) findViewById(R.id.input_distance);
        input_efficiency = (EditText) findViewById(R.id.input_efficiency);
        input_price = (EditText) findViewById(R.id.input_price);

        input_distance.addTextChangedListener(inputDistanceWatcher);
        input_price.addTextChangedListener(inputPriceWatcher);
        input_efficiency.addTextChangedListener(inputEfficiencyWatcher);
    }

    private void initAd(){
        MobileAds.initialize(getApplicationContext(), getString(R.string.banner_ad_app_id));
        AdView adView = (AdView) findViewById(R.id.mainAdView);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);
    }

    private TextWatcher inputDistanceWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    private TextWatcher inputEfficiencyWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    private TextWatcher inputPriceWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };



}
