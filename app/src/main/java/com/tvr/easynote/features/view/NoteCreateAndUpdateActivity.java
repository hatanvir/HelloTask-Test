package com.tvr.easynote.features.view;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.tvr.easynote.R;
import com.tvr.easynote.database.AppDatabase;
import com.tvr.easynote.database.DatabaseClient;
import com.tvr.easynote.database.FavoriteNotes;
import com.tvr.easynote.database.Notes;
import com.tvr.easynote.features.model.NoteModel;
import com.tvr.easynote.features.model.NoteModelImplementation;
import com.tvr.easynote.features.viewmodel.NotesViewModel;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NoteCreateAndUpdateActivity extends AppCompatActivity {

    @BindView(R.id.titleEt)
    EditText titleEt;
    @BindView(R.id.descriptionEt)
    EditText descriptionEt;

    NoteModel model;
    NotesViewModel viewModel;

    @BindView(R.id.saveFab)
    FloatingActionButton saveFab;
    @BindView(R.id.favoriteIm)
    ImageButton favoriteIm;
    @BindView(R.id.deleteIm)
    ImageButton deleteIm;

    private String activityStatusTag;

    private String title,des;int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_create_and_update);
        ButterKnife.bind(this);

        getSupportActionBar().hide();

        model = new NoteModelImplementation(this);
        viewModel = ViewModelProviders.of(this).get(NotesViewModel.class);

        activityStatusTag = getIntent().getStringExtra("tag");

        modifyUI();

        Notes notes = (Notes) getIntent().getSerializableExtra("notes");

        deleteIm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewModel.deleteNote(notes,model);

                AlertDialog.Builder builder = new AlertDialog.Builder(NoteCreateAndUpdateActivity.this);
                builder.setMessage("Are you sure to delete this note ?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        viewModel.deleteNoteSuccess.observe(NoteCreateAndUpdateActivity.this, new Observer() {
                            @Override
                            public void onChanged(Object o) {
                                Toast.makeText(NoteCreateAndUpdateActivity.this, "Note deleted", Toast.LENGTH_SHORT).show();
                                finish();
                            }
                        });
                        viewModel.deleteNoteFailed.observe(NoteCreateAndUpdateActivity.this, new Observer() {
                            @Override
                            public void onChanged(Object o) {
                                Toast.makeText(NoteCreateAndUpdateActivity.this, "Note deletion failed", Toast.LENGTH_SHORT).show();
                            }
                        });
                        dialogInterface.dismiss();
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                builder.show();
            }
        });

        favoriteIm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewModel.addFavorite(new FavoriteNotes(notes.getTitle(),notes.getDes(),notes.getDate_time()),model);
                viewModel.addToFavSuccess.observe(NoteCreateAndUpdateActivity.this, new Observer() {
                    @Override
                    public void onChanged(Object o) {
                        viewModel.addToFavSuccess.observe(NoteCreateAndUpdateActivity.this, new Observer() {
                            @Override
                            public void onChanged(Object o) {
                                Toast.makeText(NoteCreateAndUpdateActivity.this, "Added to favorite", Toast.LENGTH_SHORT).show();

                            }
                        });
                        viewModel.addToFavFailed.observe(NoteCreateAndUpdateActivity.this, new Observer() {
                            @Override
                            public void onChanged(Object o) {
                                Toast.makeText(NoteCreateAndUpdateActivity.this, "Failed to add favorite", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });

                viewModel.addNoteFailed.observe(NoteCreateAndUpdateActivity.this, new Observer() {
                    @Override
                    public void onChanged(Object o) {
                        Toast.makeText(NoteCreateAndUpdateActivity.this, "Failed to add favorite", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

    }

    private void modifyUI() {
        AppDatabase appDatabase = DatabaseClient.getInstance(getApplicationContext()).getAppDatabase();
        Date currentTime = Calendar.getInstance().getTime();

        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault());
        String formattedDate = df.format(currentTime);

        if(activityStatusTag.equals("save")){
            favoriteIm.setVisibility(View.GONE);
            deleteIm.setVisibility(View.GONE);

            titleEt.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void afterTextChanged(Editable editable) {
                    if(titleEt.getText().toString().length()>0){
                        saveFab.setVisibility(View.VISIBLE);
                    }else {
                        saveFab.setVisibility(View.GONE);
                    }
                }
            });

            saveFab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    viewModel.addNote(new Notes(titleEt.getText().toString(), descriptionEt.getText().toString(), formattedDate.toString()),appDatabase, model);
                    viewModel.addNoteSuccess.observe(NoteCreateAndUpdateActivity.this, new Observer() {
                        @Override
                        public void onChanged(Object o) {
                            Toast.makeText(NoteCreateAndUpdateActivity.this, "Note added", Toast.LENGTH_SHORT).show();
                        }
                    });
                    viewModel.addNoteFailed.observe(NoteCreateAndUpdateActivity.this, new Observer() {
                        @Override
                        public void onChanged(Object o) {
                            Toast.makeText(NoteCreateAndUpdateActivity.this, "Failed to add note.", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            });

        }else {
            favoriteIm.setVisibility(View.VISIBLE);
            deleteIm.setVisibility(View.VISIBLE);

            Intent intent = getIntent();
            Notes notes = (Notes) intent.getSerializableExtra("notes");

            titleEt.setText(notes.getTitle());
            descriptionEt.setText(notes.getDes());

            titleEt.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void afterTextChanged(Editable editable) {
                    if(!titleEt.getText().toString().equals(title) || !descriptionEt.getText().toString().equals(des)){
                        saveFab.setVisibility(View.VISIBLE);
                    }else {
                        saveFab.setVisibility(View.GONE);
                    }
                }
            });

            descriptionEt.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void afterTextChanged(Editable editable) {
                    if(!titleEt.getText().toString().equals(title) || !descriptionEt.getText().toString().equals(des)){
                        saveFab.setVisibility(View.VISIBLE);
                    }else {
                        saveFab.setVisibility(View.GONE);
                    }
                }
            });

            saveFab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    notes.setTitle(titleEt.getText().toString());
                    notes.setDes(descriptionEt.getText().toString());
                    notes.setDate_time(formattedDate);
                    viewModel.updateNote(notes,appDatabase, model);
                    viewModel.updateNoteSuccess.observe(NoteCreateAndUpdateActivity.this, new Observer() {
                        @Override
                        public void onChanged(Object o) {
                            Toast.makeText(NoteCreateAndUpdateActivity.this, "Note updated"+o.toString(), Toast.LENGTH_SHORT).show();
                        }
                    });
                    viewModel.updateNoteFailed.observe(NoteCreateAndUpdateActivity.this, new Observer() {
                        @Override
                        public void onChanged(Object o) {
                            Toast.makeText(NoteCreateAndUpdateActivity.this, "Failed to add note.", Toast.LENGTH_SHORT).show();
                        }
                    });
                }AppDatabase appDatabase = DatabaseClient.getInstance(getApplicationContext()).getAppDatabase();
            });
        }

    }


}