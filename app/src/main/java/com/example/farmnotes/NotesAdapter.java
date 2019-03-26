package com.example.farmnotes;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.amulyakhare.textdrawable.util.ColorGenerator;

import java.util.ArrayList;
import java.util.Random;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.ViewHolder> {
    private ArrayList<NotesModel> notesList;
    private Context context;

    public NotesAdapter(ArrayList<NotesModel> notesList, Context context) {
        this.notesList = notesList;
        this.context = context;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.notes_recycler_view, viewGroup, false);
        return new NotesAdapter.ViewHolder(v);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        final NotesModel notes = notesList.get(position);
        viewHolder.title.setText(notes.getTitle());
        viewHolder.content.setText(notes.getContent());
        viewHolder.date.setText(notes.getDate());

//        Random r = new Random();
//        int red=r.nextInt(255 - 0 + 1)+0;
//        int green=r.nextInt(255 - 0 + 1)+0;
//        int blue=r.nextInt(255 - 0 + 1)+0;
//
//        GradientDrawable draw = new GradientDrawable();
//        draw.setShape(GradientDrawable.RECTANGLE);
//        draw.setColor(Color.rgb(red,green,blue));
//        viewHolder.con_notes.setBackground(draw);

        ColorGenerator generator = ColorGenerator.MATERIAL;
        int color1 = generator.getRandomColor();


        viewHolder.con1.setBackgroundColor(color1);

        viewHolder.con_notes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,NotesCreation.class);
                Bundle bundle = new Bundle();
                bundle.putString("title", notes.getTitle());
                bundle.putString("content", notes.getContent());
                bundle.putString("date", notes.getDate());
                bundle.putInt("note_id", notes.getId());
                intent.putExtras(bundle);
                context.startActivity(intent);

            }
        });





    }

    @Override
    public int getItemCount() {
        return notesList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView title, content,date;
        ConstraintLayout con_notes,con1;



        public ViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.tv_note_title);
            content = itemView.findViewById(R.id.tv_note_content);
            date = itemView.findViewById(R.id.tv_note_creation_date);
            con_notes = itemView.findViewById(R.id.con_notes);
            con1 = itemView.findViewById(R.id.con1);



        }
    }
}
