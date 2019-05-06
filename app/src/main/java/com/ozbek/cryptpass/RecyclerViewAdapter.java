package com.ozbek.cryptpass;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private List<Generate> entries = new ArrayList<>();

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Generate currentEntry = entries.get(position);
        holder.hint.setText(currentEntry.getHint());
        holder.username.setText(currentEntry.getUsername());
        holder.password.setText(currentEntry.getPassword());
    }

    @Override
    public int getItemCount() {return entries.size();}

    public void setEntries(List<Generate> entries){
        this.entries = entries;
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView username, password, hint;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            hint = itemView.findViewById(R.id.hint_display);
            username = itemView.findViewById(R.id.username_display);
            password = itemView.findViewById(R.id.password_display);

        }
    }
}
