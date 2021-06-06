package com.example.homework3.data.tasks;

import android.os.AsyncTask;

import com.example.homework3.models.dbEntities.Book;
import com.example.homework3.data.BookDataBase;

public class InsertBookTask extends AsyncTask<Book,Void,Void> {

    private final BookDataBase bookDataBase;


    public InsertBookTask(BookDataBase bookDataBase){
        this.bookDataBase=bookDataBase;
    }

    @Override
    protected Void doInBackground(Book... books) {
        bookDataBase.bookDAO().insertBook(books[0]);
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
    }
}
