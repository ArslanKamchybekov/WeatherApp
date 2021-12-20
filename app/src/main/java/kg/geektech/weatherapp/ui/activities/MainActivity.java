package kg.geektech.weatherapp.ui.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import dagger.hilt.android.AndroidEntryPoint;
import kg.geektech.weatherapp.R;
@AndroidEntryPoint
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
    }
}