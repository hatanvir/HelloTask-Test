package com.tvr.easynote.features.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.tvr.easynote.R;
import com.tvr.easynote.database.AppDatabase;
import com.tvr.easynote.database.Notes;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableMaybeObserver;
import io.reactivex.schedulers.Schedulers;

public class GetStartActivity extends AppCompatActivity {

    @BindView(R.id.getstartBt)
    Button getStartBt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_getstart);
        ButterKnife.bind(this);

        getSupportActionBar().hide();
        
        getStartBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(GetStartActivity.this,HomeActivity.class));
                finish();
            }
        });
    }
}