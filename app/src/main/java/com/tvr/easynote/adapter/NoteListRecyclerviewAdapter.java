package com.tvr.easynote.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tvr.easynote.R;
import com.tvr.easynote.database.Notes;
import com.tvr.easynote.features.view.NoteCreateAndUpdateActivity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NoteListRecyclerviewAdapter extends RecyclerView.Adapter<NoteListRecyclerviewAdapter.ViewHolder> {
    Context context;
    List<Notes> list;
    ArrayList<String> colorList;
    public NoteListRecyclerviewAdapter(Context context, List<Notes> list) {
        colorList = new ArrayList<>();
        this.context = context;
        this.list = list;

        colorList.add("#F57824");
        colorList.add("#6F4FF1");
        colorList.add("#F52439");
        colorList.add("#FBD24C");
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.shape_notelist_rv, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
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

        Notes notes = list.get(position);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(new Intent(context, NoteCreateAndUpdateActivity.class).
                        putExtra("tag","update").
                        putExtra("notes", notes)
                );
            }
        });

        holder.view.setBackgroundColor(Color.parseColor(color));
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
