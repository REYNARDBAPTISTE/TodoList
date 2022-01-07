package com.example.todolist.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import com.example.todolist.R;
import com.example.todolist.TaskpersoActivity;
import com.example.todolist.databinding.FragmentParametreBinding;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;

private FragmentParametreBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
            ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

    binding = FragmentParametreBinding.inflate(inflater, container, false);
    View root = binding.getRoot();

        ImageButton btnPerso = (ImageButton) root.findViewById(R.id.btnPerso);
        //final TextView textView = binding.textHome;
        btnPerso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startPerso();
            }
        });
        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                //textView.setText(s);
            }
        });
        return root;
    }

@Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
    public void startPerso(){
        Intent intent = new Intent(getContext(), TaskpersoActivity.class);
        startActivity(intent);
    }
}