package com.example.sqlite_databasep

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast


class MainActivity : AppCompatActivity() {
    lateinit var getage:TextView
    lateinit var getname:TextView
    lateinit var btnprintname:Button
    lateinit var btnaddname:Button
    lateinit var edtage:EditText
    lateinit var edtname:EditText
    @SuppressLint("Range")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getage=findViewById(R.id.Age)
        getname=findViewById(R.id.Name)
        btnprintname=findViewById(R.id.printName)
        btnaddname=findViewById(R.id.addName)
        edtage=findViewById(R.id.enterAge)
        edtname=findViewById(R.id.enterName)

        // below code is to add on click
        // listener to our add name button
        btnaddname.setOnClickListener{
            // below we have created
            // a new DBHelper class,
            // and passed context to it
            val db = DBHelper(this, null)
            // creating variables for values
            // in name and age edit texts
            val name = edtname.text.toString()
            val age = edtage.text.toString()

            // calling method to add
            // name to our database
            db.addName(name, age)

            // Toast to message on the screen
            Toast.makeText(this, name + " added to database", Toast.LENGTH_LONG).show()
            // at last, clearing edit texts
            edtname.text.clear()
            edtage.text.clear()
            }
        // below code is to add on  click
        // listener to our print name button
        btnprintname.setOnClickListener{
            // creating a DBHelper class
            // and passing context to it
            val db = DBHelper(this, null)
            // below is the variable for cursor
            // we have called method to get
            // all names from our database
            // and add to name text view
            val cursor = db.getName()
            // moving the cursor to first position and
            // appending value in the text view
            cursor!!.moveToFirst()
            getname.append(cursor.getString(cursor.getColumnIndex(DBHelper.NAME_COl)) + "\n")
            getage.append(cursor.getString(cursor.getColumnIndex(DBHelper.AGE_COL)) + "\n")

            // moving our cursor to next
            // position and appending values
            while(cursor.moveToNext()){
            getname.append(cursor.getString(cursor.getColumnIndex(DBHelper.NAME_COl)) + "\n")
            getage.append(cursor.getString(cursor.getColumnIndex(DBHelper.AGE_COL)) + "\n")
            }
            // at last we close our cursor
            cursor.close()
            }
        }
    }

