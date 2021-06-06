package com.example.homework3.models.dbEntities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.example.homework3.models.BookElement;

@Entity
public class Book {
    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name ="title")
    public String title;

    @ColumnInfo(name="author")
    public String author;

    @ColumnInfo(name ="description")
    public String description;


    @Ignore
    public Book(int id,String title,String author,String description)
    {
        this.id=id;
        this.author=author;
        this.title=title;
        this.description=description;
    }

    public Book(String title,String author,String description)
    {
        this.title=title;
        this.author=author;
        this.description=description;
    }
    public BookElement convert(){
        return new BookElement(id,title,author,description);
    }
}
