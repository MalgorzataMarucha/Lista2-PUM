package com.example.lista2;

import static com.example.lista2.DataGenerator.generateData;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lista2.databinding.FragmentE1Binding;

import java.util.List;

public class E1 extends Fragment {

    private FragmentE1Binding binding;
    private RecyclerView recyclerView;
    private ItemAdapter itemAdapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentE1Binding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.recyclerView);

        List<Data.ExerciseList> generatedData = generateData(20, 10);

        itemAdapter = new ItemAdapter(generatedData);

        recyclerView.setAdapter(itemAdapter);

        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
    }

}
