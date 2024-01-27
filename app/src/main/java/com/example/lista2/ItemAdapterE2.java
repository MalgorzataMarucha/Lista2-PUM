package com.example.lista2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.lista2.Data;
import java.util.List;

public class ItemAdapterE2 extends RecyclerView.Adapter<ItemAdapterE2.ViewHolder> {

    private List<Data.SubjectAverage> subjectAverages;

    public ItemAdapterE2(List<Data.SubjectAverage> subjectAverages) {
        this.subjectAverages = subjectAverages;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.item_view2, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Data.SubjectAverage subjectAverage = subjectAverages.get(position);

        holder.subjectName.setText(subjectAverage.getName());

        String averageGradeText = String.format("Średnia: %.2f", subjectAverage.getAverageGrade());
        holder.averageGrade.setText(averageGradeText);

        // Ustawianie liczby list
        holder.listNumber.setText("Liczba list: " + subjectAverage.getListNumber());
    }

    @Override
    public int getItemCount() {
        return subjectAverages.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView subjectName;
        public TextView averageGrade;
        public TextView listNumber;

        public ViewHolder(View itemView) {
            super(itemView);

            subjectName = itemView.findViewById(R.id.textSubjectNameE2);
            averageGrade = itemView.findViewById(R.id.textAverageGradeE2);
            listNumber = itemView.findViewById(R.id.textListNumberE2);
        }
    }
}
