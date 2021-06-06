package com.example.homework3.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.example.homework3.R;
import com.example.homework3.adapters.BookAdapter;
import com.example.homework3.data.BookRepository;
import com.example.homework3.helpers.UtilsValidators;
import com.example.homework3.interfaces.FragmentCommunication;
import com.example.homework3.interfaces.OnItemClickListener;
import com.example.homework3.models.BookElement;
import com.example.homework3.models.dbEntities.Book;

import java.util.ArrayList;

public class FirstFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private FragmentCommunication fragmentCommunication;
    private final ArrayList<BookElement> books = new ArrayList<>();
    private final BookRepository bookRepository = new BookRepository();
    private EditText authorEt;
    private EditText titleEt;
    private EditText descriptionEt;
    private final BookAdapter bookAdapter = new BookAdapter(books, new OnItemClickListener() {
        @Override
        public void onBookClick(BookElement bookElement) {
            fragmentCommunication.openSecondFragment(bookElement);
        }

        @Override
        public void onDeleteClick(BookElement book) {
            Toast.makeText(getContext(), "Deleted successfully", Toast.LENGTH_SHORT).show();
        }
    },
            bookElement -> {
                AppCompatActivity appCompatActivity = (AppCompatActivity) getView().getContext();
                SecondFragment secondFragment = new SecondFragment(bookElement);
                appCompatActivity.getSupportFragmentManager().beginTransaction().replace(R.id.fragment_edit_books, secondFragment).commit();
            });

    public FirstFragment() { }

    public static FirstFragment newInstance(String param1, String param2) {
        FirstFragment fragment = new FirstFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            String mParam1 = getArguments().getString(ARG_PARAM1);
            String mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    public void validateBookCharacteristics() {
        if (getView() == null)
            return;

        String title = titleEt.getText().toString();
        String author = authorEt.getText().toString();
        String description = descriptionEt.getText().toString();

        if (!UtilsValidators.isValidTitle(title)) {
            titleEt.setError("Invalid title.");
            return;
        } else {
            titleEt.setError(null);
        }

        if (!UtilsValidators.isValidAuthor(author)) {
            authorEt.setError("Invalid author.");
            return;
        } else {
            authorEt.setError(null);
        }

        if (!UtilsValidators.isValidDescription(description)) {
            descriptionEt.setError("Invalid description.");
            return;
        } else {
            descriptionEt.setError(null);
        }
    }

    public void clearEditTexts()
    {
        titleEt.setText("");
        authorEt.setText("");
        descriptionEt.setText("");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_first, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.books);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(bookAdapter);
        authorEt = view.findViewById(R.id.edit_author);
        titleEt = view.findViewById(R.id.edit_title);
        descriptionEt = view.findViewById(R.id.edit_description);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getBooks();
        view.findViewById(R.id.button_add_update).setOnClickListener(v -> {
            validateBookCharacteristics();

            String title = titleEt.getText().toString();
            String author = authorEt.getText().toString();
            String description = descriptionEt.getText().toString();

            boolean existentBook=false;
            int index=-1;
            for (BookElement bookElement: books) {
                if(bookElement.getTitle().equals(title) && bookElement.getAuthor().equals(author))
                {
                    existentBook=true;
                    index= books.indexOf(bookElement);
                    break;
                }
            }
            if(existentBook)
            {
                Book book=new Book(books.get(index).getId(), books.get(index).getTitle(), books.get(index).getAuthor(),description);
                bookRepository.updateBook(book);
                books.get(index).setDescription(description);
                bookAdapter.notifyItemChanged(index);
                Toast.makeText(getContext(), "Updated successfully", Toast.LENGTH_SHORT).show();
                clearEditTexts();
            }
            else
            {
                Book book=new Book(title,author,description);
                bookRepository.insertBook(book);
                books.add(book.convert());
                Toast.makeText(getContext(), "Book added successfully", Toast.LENGTH_SHORT).show();
                clearEditTexts();
            }

            bookAdapter.notifyItemChanged(books.size() - 1);
            getBooks();
        });

    }

    public void getBooks() {
        bookRepository.getAllBooks(booksResult -> {
            books.clear();
            for (Book book : booksResult) {
                books.add(book.convert());
            }
            bookAdapter.notifyDataSetChanged();
        });
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        if (context instanceof FragmentCommunication) {
            fragmentCommunication = (FragmentCommunication) context;
        }
    }
}
