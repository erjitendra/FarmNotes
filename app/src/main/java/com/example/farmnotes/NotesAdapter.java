package com.example.farmnotes;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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

        // Colour Set of codes
        List<Integer> colours = Arrays.asList(
                0xffe57373,
                0xfff06292,
                0xffba68c8,
                0xff9575cd,
                0xff7986cb,
                0xff64b5f6,
                0xff4fc3f7,
                0xff4dd0e1,
                0xff4db6ac,
                0xff81c784,
                0xffaed581,
                0xffff8a65,
                0xffd4e157,
                0xffffd54f

        );
        Random mRandom = new Random(System.currentTimeMillis());
        int color1 = colours.get(mRandom.nextInt(colours.size()));

        viewHolder.con1.setBackgroundColor(color1);

        viewHolder.con_notes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, NotesCreation.class);
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

        TextView title, content, date;
        ConstraintLayout con_notes, con1;


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
