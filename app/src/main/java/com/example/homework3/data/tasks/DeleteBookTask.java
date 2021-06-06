package com.example.homework3.data.tasks;

import android.os.AsyncTask;

import com.example.homework3.models.dbEntities.Book;
import com.example.homework3.data.BookDataBase;

public class DeleteBookTask extends AsyncTask<Book,Void,Void>{


        private final BookDataBase bookDataBase;
        public DeleteBookTask(BookDataBase toDoDataBase)
        {
            this.bookDataBase=toDoDataBase;
        }

        @Override
        protected Void doInBackground(Book... books) {
            bookDataBase.bookDAO().delete(books[0]);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
        }

}
