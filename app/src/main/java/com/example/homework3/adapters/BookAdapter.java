package com.example.homework3.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.homework3.interfaces.FragmentCommunication;
import com.example.homework3.interfaces.OnItemClickListener;
import com.example.homework3.models.dbEntities.Book;
import com.example.homework3.R;
import com.example.homework3.data.BookRepository;
import com.example.homework3.models.BookElement;

import java.util.ArrayList;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.BookViewHolder> {
    ArrayList<BookElement> books;
    OnItemClickListener onItemClickListener;
    FragmentCommunication fragmentCommunication;
    public BookAdapter(ArrayList<BookElement> books, OnItemClickListener onItemClickListener, FragmentCommunication fragmentCommunication)
    {
        this.books = books;
        this.onItemClickListener=onItemClickListener;
        this.fragmentCommunication=fragmentCommunication;
    }

    @NonNull
    @Override
    public BookAdapter.BookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.book_cell, parent, false);
        BookViewHolder bookViewHolder = new BookViewHolder(view);
        return bookViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull BookAdapter.BookViewHolder holder, int position) {
        BookElement bookElement = books.get(position);
        holder.bind(bookElement);
    }

    @Override
    public int getItemCount() {
        return books.size();
    }
    class BookViewHolder extends RecyclerView.ViewHolder {
        private final TextView title;
        private final TextView author;
        private final TextView description;
        private final View view;
        private final Button deleteButton;

        public BookViewHolder(View view) {
            super(view);
            title = view.findViewById(R.id.book_title);
            author=view.findViewById(R.id.book_author);
            description = view.findViewById(R.id.book_description);
            deleteButton =view.findViewById(R.id.button_delete);
            this.view=view;
        }

        public void bind(BookElement bookElement) {
            title.setText(bookElement.getTitle());
            author.setText(bookElement.getAuthor());
            description.setText((bookElement.getDescription()));

            view.setOnClickListener(v -> {
                onItemClickListener.onBookClick(bookElement);
                notifyItemChanged(getAdapterPosition());
            });

            deleteButton.setOnClickListener(v -> {
                BookRepository bookRepository=new BookRepository();
                Book book=new Book(bookElement.getId(),bookElement.getTitle(),bookElement.getAuthor(),bookElement.getDescription());
                bookRepository.deleteBook(book);
                onItemClickListener.onDeleteClick(bookElement);
                books.remove(getAdapterPosition());
                notifyItemChanged(getAdapterPosition());
            });
        }

    }
}
