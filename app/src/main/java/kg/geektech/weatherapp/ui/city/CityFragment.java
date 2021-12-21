package kg.geektech.weatherapp.ui.city;

import android.annotation.SuppressLint;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import dagger.hilt.android.AndroidEntryPoint;
import kg.geektech.weatherapp.R;
import kg.geektech.weatherapp.databinding.FragmentCityBinding;

@AndroidEntryPoint
public class CityFragment extends Fragment {

    private FragmentCityBinding binding;
    private NavHostFragment navHostFragment;
    private NavController navController;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        navHostFragment  = (NavHostFragment) requireActivity().getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
        navController = navHostFragment.getNavController();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentCityBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initListeners();

        Calendar c = Calendar.getInstance();
        int timeOfDay = c.get(Calendar.HOUR_OF_DAY);
        if(timeOfDay >= 0 && timeOfDay < 17){
            binding.animationView.setAnimation(R.raw.day);
        }else if(timeOfDay >= 17 && timeOfDay < 24){
            binding.animationView.setAnimation(R.raw.night);
        }
    }

    private void initListeners() {
        binding.btnGetWeather.setOnClickListener(v -> {
            String city = binding.etCity.getText().toString();
            navController.navigate(CityFragmentDirections.actionCityFragmentToWeatherFragment(city));
        });
//        requireActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(), new OnBackPressedCallback(true) {
//            @Override
//            public void handleOnBackPressed() {
//                navController.navigate(R.id.weatherFragment);
//            }
//        });
    }
}