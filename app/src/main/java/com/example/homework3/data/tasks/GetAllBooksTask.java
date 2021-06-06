package com.example.homework3.data.tasks;

import android.os.AsyncTask;

import com.example.homework3.data.BookRepository;
import com.example.homework3.models.dbEntities.Book;
import com.example.homework3.data.BookDataBase;

import java.util.List;

public class GetAllBooksTask extends AsyncTask<Void,Void, List<Book>> {

    private final BookDataBase bookDataBase;
    private final BookRepository.OnGetBooksListener listener;

    public GetAllBooksTask(BookDataBase bookDataBase,BookRepository.OnGetBooksListener listener){

        this.bookDataBase=bookDataBase;
        this.listener=listener;
    }

    @Override
    protected List<Book> doInBackground(Void... voids) {
        return bookDataBase.bookDAO().getAllBooks();
    }

    @Override
    protected void onPostExecute(List<Book> books) {
        super.onPostExecute(books);
        listener.onSuccess(books);
    }
}
