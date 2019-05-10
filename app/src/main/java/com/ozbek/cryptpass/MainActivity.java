package com.ozbek.cryptpass;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;
import java.util.Objects;

import io.sentry.Sentry;
import io.sentry.android.AndroidSentryClientFactory;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    RecyclerViewAdapter adapter;
    FloatingActionButton floatingActionButton;
    RecyclerView.LayoutManager layoutManager;
    AddEditEntry addEditEntry;
    Entries entry;
    private EntryViewModel viewModel;

    public static final int ADD_ENTRY_REQUEST = 1;
    public static final int EDIT_ENTRY_REQUEST = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Context ctx = this.getApplicationContext();

        String sentryDsn = "https://93f06d5090d646ac9443a3fc531fc5de@sentry.io/1454826:port/1?options";
        Sentry.init(sentryDsn, new AndroidSentryClientFactory(ctx));
        Sentry.init(new AndroidSentryClientFactory(ctx));

        viewModel = ViewModelProviders.of(this).get(EntryViewModel.class);
        viewModel.getAllEntries().observe(this, new Observer<List<Entries>>() {
            @Override
            public void onChanged(List<Entries> entries) {adapter.setEntries(entries);}
        });

        adapter = new RecyclerViewAdapter();
        layoutManager = new LinearLayoutManager(this);

        recyclerView = findViewById(R.id.userpass_recyclerview);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        addEditEntry = new AddEditEntry();
        entry = new Entries();

        floatingActionButton = findViewById(R.id.generate_fab);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddEditEntry.class);
                startActivityForResult(intent, ADD_ENTRY_REQUEST);
            }
        });

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {return false;}

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {viewModel.delete(adapter.getEntryAt(viewHolder.getAdapterPosition()));}

        }).attachToRecyclerView(recyclerView);

        adapter.setOnItemLongClickListener(new RecyclerViewAdapter.OnItemLongClickListener() {
            @Override
            public void onItemLongClick(Entries entries) {
                Intent intent = new Intent(MainActivity.this, AddEditEntry.class);
                intent.putExtra(AddEditEntry.EXTRA_USERNAME, entry.getUsername());
                intent.putExtra(AddEditEntry.EXTRA_HINT, entry.getHint());
                intent.putExtra(AddEditEntry.EXTRA_PASSWORD, entry.getPassword());
                intent.putExtra(AddEditEntry.EXTRA_ID, entry.getId());
                startActivityForResult(intent, EDIT_ENTRY_REQUEST);
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
            String username = Objects.requireNonNull(data).getStringExtra(AddEditEntry.EXTRA_USERNAME);
            String password = Objects.requireNonNull(data).getStringExtra(AddEditEntry.EXTRA_PASSWORD);
            String hint = Objects.requireNonNull(data).getStringExtra(AddEditEntry.EXTRA_HINT);

            Entries entry = new Entries(username, hint, password);
            viewModel.insert(entry);

            Toast.makeText(this, "Entry added!", Toast.LENGTH_SHORT).show();

        }else if(requestCode == EDIT_ENTRY_REQUEST && resultCode == RESULT_OK){
            int id = Objects.requireNonNull(data).getIntExtra(AddEditEntry.EXTRA_ID, -1);
            String username = Objects.requireNonNull(data).getStringExtra(AddEditEntry.EXTRA_USERNAME);
            String password = Objects.requireNonNull(data).getStringExtra(AddEditEntry.EXTRA_PASSWORD);
            String hint = Objects.requireNonNull(data).getStringExtra(AddEditEntry.EXTRA_HINT);

            if (id == -1){Toast.makeText(addEditEntry, "Something went wrong", Toast.LENGTH_SHORT).show();}

            Entries entry = new Entries(username, hint, password);
            entry.setId(id);
            viewModel.update(entry);

            Toast.makeText(this, "Entry updated", Toast.LENGTH_SHORT).show();

        }else{Toast.makeText(this, "Entry not added!", Toast.LENGTH_SHORT).show();}
    }
}
