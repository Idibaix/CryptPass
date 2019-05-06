package com.ozbek.cryptpass;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    RecyclerViewAdapter adapter;
    FloatingActionButton floatingActionButton;
    RecyclerView.LayoutManager layoutManager;
    GenerateActivity generateActivity;
    private GenerateViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewModel = ViewModelProviders.of(this).get(GenerateViewModel.class);
        viewModel.getAllEntries().observe(this, new Observer<List<Generate>>() {
            @Override
            public void onChanged(List<Generate> entries) {adapter.setEntries(entries);}
        });

        adapter = new RecyclerViewAdapter();
        layoutManager = new LinearLayoutManager(this);

        recyclerView = findViewById(R.id.userpass_recyclerview);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        generateActivity = new GenerateActivity();

        floatingActionButton = findViewById(R.id.generate_fab);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, GenerateActivity.class);
                startActivity(intent);
            }
        });
    }
}
