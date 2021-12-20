package kg.geektech.weatherapp.ui.city;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import dagger.hilt.android.AndroidEntryPoint;
import kg.geektech.weatherapp.R;
import kg.geektech.weatherapp.databinding.FragmentCityBinding;

@AndroidEntryPoint
public class CityFragment extends Fragment {

    private FragmentCityBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentCityBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.btnGetWeather.setOnClickListener(v -> {
            String city = binding.etCity.getText().toString();
            NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment);
            navController.navigate(CityFragmentDirections.actionCityFragmentToWeatherFragment(city));
        });
    }
}