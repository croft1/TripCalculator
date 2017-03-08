package devicroft.tripcalculator;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import static java.lang.Thread.sleep;

public class MainActivity extends AppCompatActivity {


    TextView result_cost;
    TextView result_consumption;
    EditText input_distance;
    EditText input_price;
    EditText input_efficiency;
    TextView label_distance;
    TextView label_price;
    TextView label_efficiency;
    TextView label_consumed;
    TextView label_cost;
    ImageButton map_button;
    ImageView logo;

    Trip currentTrip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
        final int SPLASH_LENGTH = 2000;
        //showLogoSplash(true);
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                /* Create an Intent that will start the Menu-Activity. */
                setContentView(R.layout.activity_main);
                setupUiElements();
                initAd();
            }
        }, SPLASH_LENGTH);
        //showLogoSplash(false);






    }

    private void setupUiElements(){
        result_cost = (TextView) findViewById(R.id.result_cost);
        result_consumption = (TextView) findViewById(R.id.result_fuel_consumed);
        input_distance = (EditText) findViewById(R.id.input_distance);
        input_efficiency = (EditText) findViewById(R.id.input_efficiency);
        input_price = (EditText) findViewById(R.id.input_price);
        label_distance = (TextView) findViewById(R.id.label_distance);
        label_efficiency = (TextView) findViewById(R.id.label_efficiency);
        label_price = (TextView) findViewById(R.id.label_price);
        label_cost = (TextView) findViewById(R.id.label_result_cost);
        label_consumed = (TextView) findViewById(R.id.label_result_consumed);
        map_button = (ImageButton) findViewById(R.id.map_button);
        logo = (ImageView) findViewById(R.id.logo_view);


        input_distance.addTextChangedListener(inputWatcher);
        input_price.addTextChangedListener(inputWatcher);
        input_efficiency.addTextChangedListener(inputWatcher);

        result_consumption.setText("");
        result_cost.setText("");

        //TODO Setup locality
        label_price.setText(label_price.getText() + " (" + getString(R.string.locality_currency_dollar) + ")");
        label_efficiency.setText(label_efficiency.getText() + " (" + getString(R.string.locality_volume_liters) + ")");
        label_distance.setText(label_distance.getText() + " (" + getString(R.string.locality_volume_liters) + "/100" + getString(R.string.locality_distance_km) + ")");

    }

    private void initAd(){
        MobileAds.initialize(getApplicationContext(), getString(R.string.banner_ad_app_id));
        AdView adView = (AdView) findViewById(R.id.mainAdView);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);
    }

    Boolean changing = false;
    private TextWatcher inputWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            //disable input of 0 as first character
            if (!changing && input_distance.getText().toString().startsWith("0")){
                changing = true;//stops infinite loop
                input_distance.setText(input_distance.getText().toString().replace("0", ""));
            }
            changing = false;

            if(input_distance.length() > 0 && input_efficiency.length() > 0 && input_price.length() > 0){
                setCurrentTrip();
                updateUiForTrip();
            }
        }
    };

    private View.OnClickListener mapButtonListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            dispatchMapDistanceFetcherIntent();

        }
    };

    private void dispatchMapDistanceFetcherIntent() {
        Toast.makeText(this, getString(R.string.unavailable_feature), Toast.LENGTH_SHORT).show();
    }

    private void setCurrentTrip(){
        double distance = Double.parseDouble(input_distance.getText().toString());
        double price = Double.parseDouble(input_price.getText().toString());
        double efficiency = Double.parseDouble(input_efficiency.getText().toString());

        currentTrip = new Trip(distance, efficiency, price);
    }

    private void updateUiForTrip(){
        result_consumption.setText(String.valueOf(currentTrip.getFuelUsage()) + getString(R.string.locality_volume_liters));
        result_cost.setText(getString(R.string.locality_currency_dollar) + String.valueOf(currentTrip.getTripCost()));
    }


}
