package com.tvr.easynote;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

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