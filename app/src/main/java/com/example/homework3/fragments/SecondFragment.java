package com.example.homework3.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.homework3.R;
import com.example.homework3.models.BookElement;

public class SecondFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;
    private BookElement bookElement;
    private TextView title;
    private TextView author;
    private TextView description;

    public SecondFragment(BookElement bookElement)
    {
        this.bookElement=bookElement;
    }

    public static SecondFragment newInstance(String param1, String param2, BookElement bookElement) {
        SecondFragment fragment = new SecondFragment(bookElement);
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
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view= inflater.inflate(R.layout.fragment_second, container, false);
        title =view.findViewById(R.id.tv_title);
        author =view.findViewById(R.id.tv_author);
        description =view.findViewById(R.id.tv_description);

        title.setText(bookElement.getTitle());
        author.setText(bookElement.getAuthor());
        description.setText(bookElement.getDescription());
        return view;
    }

}