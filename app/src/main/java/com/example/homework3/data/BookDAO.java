package com.example.homework3.data;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.homework3.models.dbEntities.Book;

import java.util.List;

@Dao
public interface BookDAO {

    @Query("SELECT * FROM book")
    List<Book> getAllBooks();

    @Insert
    void insertBook(Book book);

    @Update
    void update(Book book);

    @Delete
    void delete(Book book);
}
