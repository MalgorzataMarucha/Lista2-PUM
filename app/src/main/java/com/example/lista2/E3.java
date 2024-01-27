package com.example.lista2;// com.example.lista2.E3

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lista2.Data;
import com.example.lista2.ItemAdapterE3;
import com.example.lista2.R;
import com.example.lista2.databinding.FragmentE3Binding;

import java.util.ArrayList;
import java.util.List;

public class E3 extends Fragment {

    private FragmentE3Binding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentE3Binding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Bundle bundle = getArguments();
        if (bundle != null) {
            List<Data.Exercise> exercises = bundle.getParcelableArrayList("EXERCISES");
            String subjectName = bundle.getString("SUBJECT_NAME");
            int listNumber = bundle.getInt("LIST_NUMBER");

            binding.textSubjectName.setText(subjectName);
            binding.textListNumber.setText("Lista " + listNumber);

            List<String> exerciseDetails = prepareExerciseDetails(exercises);

            ItemAdapterE3 adapter = new ItemAdapterE3(exerciseDetails);

            binding.recyclerViewE3.setAdapter(adapter);

            binding.recyclerViewE3.setLayoutManager(new LinearLayoutManager(requireContext()));
        }
    }

    private List<String> prepareExerciseDetails(List<Data.Exercise> exercises) {
        List<String> exerciseDetails = new ArrayList<>();
        for (int i = 0; i < exercises.size(); i++) {
            Data.Exercise exercise = exercises.get(i);
            String detail = String.format("Zadanie %d\n%s\nPunkty: %d", i + 1, exercise.content, exercise.points);
            exerciseDetails.add(detail);
        }
        return exerciseDetails;
    }
}
