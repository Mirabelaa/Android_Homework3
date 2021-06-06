package com.example.homework3.helpers;

import android.text.TextUtils;

public class UtilsValidators {

    public static boolean isValidTitle(String title){
        return ((!TextUtils.isEmpty(title))&&(title.length()<=100));
    }
    public static boolean isValidAuthor(String author){
        return ((!TextUtils.isEmpty(author))&&(author.length()<=100));
    }
    public static boolean isValidDescription(String description){
        return ((!TextUtils.isEmpty(description))&&(description.length()<=1000));
    }
}
