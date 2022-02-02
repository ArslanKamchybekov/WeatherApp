package kg.geektech.weatherapp.ui.onBoard;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import kg.geektech.weatherapp.R;
import kg.geektech.weatherapp.databinding.ListBoardBinding;
import kg.geektech.weatherapp.prefs.Prefs;

public class BoardAdapter extends RecyclerView.Adapter<BoardAdapter.ViewHolder> {

    private final String[] titles = new String[]{"Find out the weather anywhere", "Fast and Convenient", "Do not forget to turn on WI-FI and GPS before using app"};
    private final int[] anims = new int[]{R.raw.first, R.raw.second, R.raw.third};
    private ListBoardBinding binding;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = ListBoardBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return titles.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(@NonNull ListBoardBinding itemView) {
            super(itemView.getRoot());
            binding.btnStart.setOnClickListener(view -> {
                Prefs prefs = new Prefs(view.getContext());
                prefs.saveBoardState();
                Navigation.findNavController(itemView.getRoot()).navigate(R.id.weatherFragment);
            });
        }

        public void bind(int position) {
            binding.textTitle.setText(titles[position]);
            binding.animationView.setAnimation(anims[position]);
            if (position == titles.length - 1) {
                binding.btnStart.setVisibility(View.VISIBLE);
            } else {
                binding.btnStart.setVisibility(View.GONE);
            }
        }
    }
}
