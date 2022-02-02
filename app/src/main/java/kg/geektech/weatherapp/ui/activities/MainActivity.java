package kg.geektech.weatherapp.ui.activities;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import dagger.hilt.android.AndroidEntryPoint;
import kg.geektech.weatherapp.R;
import kg.geektech.weatherapp.databinding.ActivityMainBinding;
import kg.geektech.weatherapp.prefs.Prefs;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        getSupportActionBar().hide();

        Prefs prefs = new Prefs(this);
        if (!prefs.isBoardShown()) {
            navController.navigate(R.id.onBoardFragment);
        } else {
            navController.navigate(R.id.weatherFragment);
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        return navController.navigateUp();
    }

}