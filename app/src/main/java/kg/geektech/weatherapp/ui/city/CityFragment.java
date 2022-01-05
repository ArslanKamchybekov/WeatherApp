package kg.geektech.weatherapp.ui.city;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.MarkerOptions;

import dagger.hilt.android.AndroidEntryPoint;
import kg.geektech.weatherapp.R;
import kg.geektech.weatherapp.databinding.FragmentCityBinding;

@AndroidEntryPoint
public class CityFragment extends Fragment implements OnMapReadyCallback {

    private FragmentCityBinding binding;
    private GoogleMap map;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentCityBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        map = googleMap;

        map.setOnMapClickListener(latLng -> {
            MarkerOptions markerOptions = new MarkerOptions();
            markerOptions.position(latLng);
            map.clear();
            map.addMarker(markerOptions);
            map.animateCamera(CameraUpdateFactory.newCameraPosition(
                    CameraPosition.builder()
                            .zoom(8f)
                            .target(latLng)
                            .bearing(90f)
                            .tilt(40f)
                            .build()
            ));
            map.setOnMarkerClickListener(marker -> {
                double lat = latLng.latitude;
                double lon = latLng.longitude;
                Bundle bundle = new Bundle();
                bundle.putDouble("key1", lat);
                bundle.putDouble("key2", lon);
                NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment);
                navController.navigate(R.id.weatherFragment, bundle);
                return false;
            });
        });
    }
}