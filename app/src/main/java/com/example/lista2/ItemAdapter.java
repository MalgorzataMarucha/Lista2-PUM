package com.example.lista2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;
import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder> {

    private List<Data.ExerciseList> exerciseLists;

    public ItemAdapter(List<Data.ExerciseList> exerciseLists) {
        this.exerciseLists = exerciseLists;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.item_view, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Data.ExerciseList exerciseList = exerciseLists.get(position);

        if (exerciseList.subject != null) {
            holder.subject.setText(exerciseList.subject.name);
        } else {
            holder.subject.setText("Brak nazwy przedmiotu");
        }

        holder.listNumber.setText("Lista " + exerciseList.getListNumber());

        holder.exercise.setText("Liczba zadań: " + exerciseList.exercises.size());

        String gradeText = "Ocena: " + exerciseList.grade;
        holder.grade.setText(gradeText);

        holder.itemView.setOnClickListener(view -> {
            Bundle bundle = new Bundle();
            bundle.putParcelableArrayList("EXERCISES", new ArrayList<>(exerciseList.exercises));
            bundle.putString("SUBJECT_NAME", exerciseList.subject.name);
            bundle.putInt("LIST_NUMBER", exerciseList.getListNumber());

            Navigation.findNavController(view)
                    .navigate(R.id.action_E1Fragment_to_E3Fragment, bundle);
        });
    }

    @Override
    public int getItemCount() {
        return exerciseLists.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView subject;
        public TextView exercise;
        public TextView listNumber;
        public TextView grade;

        public ViewHolder(View itemView) {
            super(itemView);

            subject = itemView.findViewById(R.id.textSubjectName);
            exercise = itemView.findViewById(R.id.textNumberOfTasks);
            listNumber = itemView.findViewById(R.id.textListNumber);
            grade = itemView.findViewById(R.id.textGrade);
        }
    }
}