package android.uom.gr.aurorapredictions;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import layout.LocationsFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.activity_main, new LocationsFragment())
                    .commit();
        }
    }
}
