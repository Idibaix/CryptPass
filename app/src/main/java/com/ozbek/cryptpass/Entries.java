package com.ozbek.cryptpass;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "entries_table")
public class Entries {
    @PrimaryKey(autoGenerate = true)
    private int id;

    private String username, hint, password;

    public Entries(String username, String hint, String password){
        this.username = username;
        this.hint = hint;
        this.password = password;
    }

    public Entries(){}

    public int getId() {return id;}

    public void setId(int id) {this.id = id;}

    public String getUsername() {return username;}

    public void setUsername(String username) {this.username = username;}

    public String getHint() {return hint;}

    public void setHint(String hint) {this.hint = hint;}

    public String getPassword() {return password;}

    public void setPassword(String password) {this.password = password;}
}
