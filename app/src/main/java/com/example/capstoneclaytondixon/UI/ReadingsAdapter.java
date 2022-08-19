package com.example.capstoneclaytondixon.UI;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.capstoneclaytondixon.Entity.ReadingEntity;
import com.example.capstoneclaytondixon.R;

import java.util.List;

public class ReadingsAdapter extends RecyclerView.Adapter<ReadingsAdapter.ReadingsViewHolder> {

    class ReadingsViewHolder extends RecyclerView.ViewHolder{
        private final TextView dateView;
        private final TextView timeView;
        private final TextView sugarView;
        private ReadingsViewHolder(View itemView) {
            super(itemView);
            dateView=itemView.findViewById(R.id.dateView);
            timeView=itemView.findViewById(R.id.timeView);
            sugarView=itemView.findViewById(R.id.sugarView);
            itemView.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View v) {
                    int position=getAdapterPosition();
                    final ReadingEntity current=mReadingEntities.get(position);
                    Intent intent = new Intent(context, editReading.class);
                    intent.putExtra("id", current.getReadingID());
                    intent.putExtra("sugar", current.getReadingSugar());
                    intent.putExtra("date", current.getReadingDate());
                    intent.putExtra("time", current.getReadingTime());
                    intent.putExtra("carbs", current.getReadingCarbs());
                    intent.putExtra("insulin", current.getReadingInsulin());
                    intent.putExtra("medicine", current.getMedicineBoolean());
                    intent.putExtra("feeling", current.getReadingFeeling());
                    intent.putExtra("tod", current.getReadingTOD());
                    context.startActivity(intent);
                }
            });
        }
    }

    private List<ReadingEntity> mReadingEntities;
    private final Context context;
    private final LayoutInflater mInflater;

    public ReadingsAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
        this.context = context;
    }

    @NonNull
    @Override
    public ReadingsAdapter.ReadingsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.reading_list_item, parent, false);
        return new ReadingsAdapter.ReadingsViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ReadingsAdapter.ReadingsViewHolder holder, int position) {
        if(mReadingEntities!=null){
            ReadingEntity current = mReadingEntities.get(position);
            String date = current.getReadingDate();
            String time = current.getReadingTime();
            Integer sugar = current.getReadingSugar();
            holder.dateView.setText("Date: " + date);
            holder.timeView.setText("Time: " + time);
            holder.sugarView.setText("Sugar: " +sugar.toString());
        }
        else {
            holder.dateView.setText("No Date");
        }
    }

    public void setReadingEntities(List<ReadingEntity> readingEntities) {
        mReadingEntities = readingEntities;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if(mReadingEntities!=null) {
            return mReadingEntities.size();
        } else return 0;
    }
}
