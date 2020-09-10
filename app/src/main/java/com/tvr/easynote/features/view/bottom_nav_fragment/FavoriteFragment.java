package com.tvr.easynote.features.view.bottom_nav_fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.tvr.easynote.R;
import com.tvr.easynote.adapter.FavoriteNoteListRecyclerViewAdapter;
import com.tvr.easynote.database.FavoriteNotes;
import com.tvr.easynote.features.model.NoteModel;
import com.tvr.easynote.features.model.NoteModelImplementation;
import com.tvr.easynote.features.viewmodel.NotesViewModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FavoriteFragment extends Fragment {


    @BindView(R.id.favNotesRv)
    RecyclerView favNotesRv;

    public FavoriteFragment() {
    }
    NoteModel model;
    NotesViewModel viewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_favorite, container, false);
        ButterKnife.bind(this,view);

        model = new NoteModelImplementation(getActivity());
        viewModel = ViewModelProviders.of(this).get(NotesViewModel.class);

        getFavData();

        favNotesRv.setLayoutManager(new LinearLayoutManager(getActivity()));

        return view;
    }

    private void getFavData() {
        viewModel.getFavNotes(model);
        viewModel.getFavSuccess.observe(this, new Observer() {
            @Override
            public void onChanged(Object o) {
               favNotesRv.setAdapter(new FavoriteNoteListRecyclerViewAdapter(getActivity(),(List<FavoriteNotes>)o));
            }
        });
        viewModel.getFavFailed.observe(this, new Observer() {
            @Override
            public void onChanged(Object o) {
                Toast.makeText(getActivity(), "Failed to load favorite note", Toast.LENGTH_SHORT).show();
            }
        });
    }

}