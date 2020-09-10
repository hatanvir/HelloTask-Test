package com.tvr.easynote.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tvr.easynote.R;
import com.tvr.easynote.database.FavoriteNotes;
import com.tvr.easynote.database.Notes;
import com.tvr.easynote.features.view.NoteCreateAndUpdateActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FavoriteNoteListRecyclerViewAdapter extends RecyclerView.Adapter<FavoriteNoteListRecyclerViewAdapter.ViewHolder> {
    Context context;
    List<FavoriteNotes> list;

    ArrayList<String> colorList;

    public FavoriteNoteListRecyclerViewAdapter(Context context, List<FavoriteNotes> list) {
        colorList = new ArrayList<>();
        this.context = context;
        this.list = list;

        colorList.add("#FF3333");
        colorList.add("#2983F6");
        colorList.add("#F629C4");
        colorList.add("#c77de7");
        colorList.add("#9029F6");
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.shape_notelist_rv, parent, false);
        FavoriteNoteListRecyclerViewAdapter.ViewHolder viewHolder = new FavoriteNoteListRecyclerViewAdapter.ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Random random = new Random();
        int listSize = list.size();
        int randomIndex = random.nextInt(listSize);
        String color =  colorList.get(randomIndex);

        String[] date = list.get(position).getDate_time().split("-");
        holder.titleTv.setText(list.get(position).getTitle());
        holder.descriptionTv.setText(list.get(position).getDes());
        holder.dateTv.setText(date[0]);
        holder.monthTv.setText(date[1]);

        FavoriteNotes notes = list.get(position);

        holder.view.setBackgroundColor(Color.parseColor(color));

       /* holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(new Intent(context, NoteCreateAndUpdateActivity.class).
                        putExtra("tag","update").
                        putExtra("notes", notes)
                );
            }
        });*/
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.dateTv)
        TextView dateTv;
        @BindView(R.id.monthTv)
        TextView monthTv;
        @BindView(R.id.view)
        View view;
        @BindView(R.id.titleTv)
        TextView titleTv;
        @BindView(R.id.descriptionTv)
        TextView descriptionTv;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
