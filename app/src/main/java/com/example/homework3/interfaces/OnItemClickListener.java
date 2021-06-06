package com.example.homework3.interfaces;

import com.example.homework3.models.BookElement;

public interface OnItemClickListener {
    void onBookClick(BookElement book);

    void onDeleteClick(BookElement book);
}
