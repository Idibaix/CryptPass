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
    private List<Entries> entries = new ArrayList<>();
    private OnItemLongClickListener listener;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Entries currentEntry = entries.get(position);
        holder.hint.setText(currentEntry.getHint());
        holder.username.setText(currentEntry.getUsername());
        holder.password.setText(currentEntry.getPassword());
    }

    @Override
    public int getItemCount() {return entries.size();}

    public void setEntries(List<Entries> entries){
        this.entries = entries;
        notifyDataSetChanged();
    }

    public Entries getEntryAt(int position){return entries.get(position);}

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView username, password, hint;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            hint = itemView.findViewById(R.id.hint_display);
            username = itemView.findViewById(R.id.username_display);
            password = itemView.findViewById(R.id.password_display);

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    int position = getAdapterPosition();
                    if(listener != null && position != RecyclerView.NO_POSITION){listener.onItemLongClick(entries.get(position));}
                    return true;
                }
            });
        }
    }

    public interface OnItemLongClickListener {void onItemLongClick(Entries entries);}

    public void setOnItemLongClickListener(OnItemLongClickListener listener){this.listener = listener;}

    /*
    * 1.  When editing, the edit page does not contain the old entry and does not update
    * 2.  Add option to input security questions & answers
    * 3.  On the list, show the hint, username and password as round black symbols
    * */
}
