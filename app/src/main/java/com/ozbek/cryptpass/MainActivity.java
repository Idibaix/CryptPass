package com.ozbek.cryptpass;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    RecyclerViewAdapter adapter;
    FloatingActionButton floatingActionButton;
    RecyclerView.LayoutManager layoutManager;
    GenerateActivity generateActivity;
    private GenerateViewModel viewModel;

    public static final int ADD_ENTRY_REQUEST = 1;

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
                startActivityForResult(intent, ADD_ENTRY_REQUEST);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();

        if(itemId == R.id.delete_all){viewModel.deleteAll();}

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == ADD_ENTRY_REQUEST && resultCode == RESULT_OK){
            String username = Objects.requireNonNull(data).getStringExtra(GenerateActivity.EXTRA_USERNAME);
            String password = Objects.requireNonNull(data).getStringExtra(GenerateActivity.EXTRA_PASSWORD);
            String hint = Objects.requireNonNull(data).getStringExtra(GenerateActivity.EXTRA_HINT);

            Generate entry = new Generate(username, hint, password);
            viewModel.insert(entry);

            Toast.makeText(this, "Entry added!", Toast.LENGTH_SHORT).show();
        }
        else{Toast.makeText(this, "Entry not added!", Toast.LENGTH_SHORT).show();}
    }
}
