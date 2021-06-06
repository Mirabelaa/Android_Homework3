package com.example.homework3.data;

import com.example.homework3.data.tasks.DeleteBookTask;
import com.example.homework3.data.tasks.UpdateBookTask;
import com.example.homework3.models.dbEntities.Book;
import com.example.homework3.ApplicationController;
import com.example.homework3.data.tasks.GetAllBooksTask;
import com.example.homework3.data.tasks.InsertBookTask;

import java.util.List;

public class BookRepository {

    public interface OnGetBooksListener {

        void onSuccess(List<Book> items);
    }

    private final BookDataBase bookDataBase;

    public BookRepository() {
        bookDataBase = ApplicationController.getDataBase();
    }

    public void insertBook(Book book) {
        new InsertBookTask(bookDataBase).execute(book);
    }

    public void updateBook(Book book) {
        new UpdateBookTask(bookDataBase).execute(book);
    }

    public void deleteBook(Book book) {
        new DeleteBookTask(bookDataBase).execute(book);
    }

    public void getAllBooks(OnGetBooksListener listener) {
        new GetAllBooksTask(bookDataBase, listener).execute();
    }
}
