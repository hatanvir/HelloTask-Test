package com.tvr.easynote.features.view.bottom_nav_fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.tvr.easynote.R;
import com.tvr.easynote.adapter.NoteListRecyclerviewAdapter;
import com.tvr.easynote.database.Notes;
import com.tvr.easynote.features.model.NoteModel;
import com.tvr.easynote.features.model.NoteModelImplementation;
import com.tvr.easynote.features.view.NoteCreateAndUpdateActivity;
import com.tvr.easynote.features.viewmodel.NotesViewModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class NoteFragment extends Fragment {

    @BindView(R.id.imageView2)
    ImageButton imageView2;

    NoteModel model;
    NotesViewModel viewModel;
    @BindView(R.id.notesRv)
    RecyclerView notesRv;

    public NoteFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_note, container, false);
        ButterKnife.bind(this, view);

        model = new NoteModelImplementation(getActivity());
        viewModel = ViewModelProviders.of(this).get(NotesViewModel.class);

        notesRv.setLayoutManager(new LinearLayoutManager(getActivity()));

        imageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), NoteCreateAndUpdateActivity.class).putExtra("tag","save"));
            }
        });
        return view;
    }

    private void getNotes() {
        viewModel.getNotes(model);
        viewModel.getNoteSuccess.observe(this, new Observer() {
            @Override
            public void onChanged(Object o) {
                notesRv.setAdapter(new NoteListRecyclerviewAdapter(getActivity(),((List<Notes>) o)));
            }
        });
        viewModel.getNoteFailed.observe(this, new Observer() {
            @Override
            public void onChanged(Object o) {
                Toast.makeText(getActivity(), "Failed", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        getNotes();
    }
}