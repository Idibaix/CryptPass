package com.ozbek.cryptpass;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "generate_table")
public class Generate {
    @PrimaryKey(autoGenerate = true)
    private int id;

    private String username, hint, password;

    public Generate(String username, String hint, String password){
        this.username = username;
        this.hint = hint;
        this.password = password;
    }

    public Generate(){}

    public int getId() {return id;}

    public void setId(int id) {this.id = id;}

    public String getUsername() {return username;}

    public void setUsername(String username) {this.username = username;}

    public String getHint() {return hint;}

    public void setHint(String hint) {this.hint = hint;}

    public String getPassword() {return password;}

    public void setPassword(String password) {this.password = password;}
}
