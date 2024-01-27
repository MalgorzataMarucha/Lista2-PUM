package com.example.lista2;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lista2.databinding.FragmentE2Binding;

import java.util.List;

public class E2 extends Fragment {

    private FragmentE2Binding binding;
    private RecyclerView recyclerView;
    private ItemAdapterE2 itemAdapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentE2Binding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.recyclerViewE2);

        List<Data.SubjectAverage> subjectAverages = generateSubjectAverages();

        itemAdapter = new ItemAdapterE2(subjectAverages);

        recyclerView.setAdapter(itemAdapter);

        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
    }

    private List<Data.SubjectAverage> generateSubjectAverages() {
        List<Data.ExerciseList> exerciseLists = DataGenerator.generateData(20, 10);

        return DataGenerator.generateSubjectAverages(exerciseLists);
    }
}