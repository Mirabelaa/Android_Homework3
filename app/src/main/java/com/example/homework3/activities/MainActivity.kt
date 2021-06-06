package com.example.homework3.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.homework3.R
import com.example.homework3.fragments.FirstFragment
import com.example.homework3.fragments.SecondFragment
import com.example.homework3.interfaces.FragmentCommunication
import com.example.homework3.models.BookElement

class MainActivity : AppCompatActivity(), FragmentCommunication {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        addEditBooksFragment()
    }

    private fun addEditBooksFragment(){
        val fragmentManager = supportFragmentManager
        val transaction = fragmentManager.beginTransaction()
        val tag = FirstFragment::class.java.name
        val addTransaction = transaction.add(
            R.id.frame_layout, FirstFragment.newInstance("",""), tag
        )
        addTransaction.commit()
    }

    override fun openSecondFragment(bookElement: BookElement?) {
        val fragmentManager = supportFragmentManager
        val transaction = fragmentManager.beginTransaction()
        val tag = SecondFragment::class.java.name
        val replaceTransaction = transaction.replace(
            R.id.frame_layout, SecondFragment.newInstance("","",bookElement), tag
        )
        replaceTransaction.addToBackStack(tag)
        replaceTransaction.commit()
    }

}