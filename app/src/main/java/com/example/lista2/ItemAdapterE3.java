package com.example.lista2;// com.example.lista2.ItemAdapterE3

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.lista2.Data.Exercise;
import com.example.lista2.R;

import java.util.List;

public class ItemAdapterE3 extends RecyclerView.Adapter<ItemAdapterE3.ViewHolder> {

    private List<String> exerciseDetails;

    public ItemAdapterE3(List<String> exerciseDetails) {
        this.exerciseDetails = exerciseDetails;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.item_view3, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String exerciseDetail = exerciseDetails.get(position);

        holder.exerciseDetail.setText(exerciseDetail);
    }

    @Override
    public int getItemCount() {
        return exerciseDetails.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView exerciseDetail;

        public ViewHolder(View itemView) {
            super(itemView);
            exerciseDetail = itemView.findViewById(R.id.textExerciseDetail);
        }
    }
}
