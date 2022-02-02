package kg.geektech.weatherapp.ui.onBoard;

import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabLayoutMediator;

import kg.geektech.weatherapp.R;
import kg.geektech.weatherapp.databinding.FragmentOnboardBinding;
import kg.geektech.weatherapp.prefs.Prefs;

public class OnBoardFragment extends Fragment {

    private FragmentOnboardBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentOnboardBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initAdapter();
        initListeners();

        new TabLayoutMediator(binding.tab, binding.viewPager, ((tab, position) -> {
        })).attach();

    }

    private void initListeners() {
        binding.btnSkip.setOnClickListener(view -> close());
        requireActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(), new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                requireActivity().finish();
            }
        });
    }

    private void initAdapter() {
        BoardAdapter adapter = new BoardAdapter();
        binding.viewPager.setAdapter(adapter);
    }

    private void close() {
        Prefs prefs = new Prefs(requireContext());
        prefs.saveBoardState();
        NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_activity_main);
        navController.navigate(R.id.weatherFragment);
    }
}