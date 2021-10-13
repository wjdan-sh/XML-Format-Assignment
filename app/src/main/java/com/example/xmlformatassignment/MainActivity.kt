package com.example.xmlformatassignment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import java.io.IOException

class MainActivity : AppCompatActivity() {

    private lateinit var tvMain: TextView
    private lateinit var Students: List<Student>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvMain = findViewById(R.id.tvMain)

        try{
            val parser = MyXmlPullParserHandler()
            val iStream = assets.open("student.xml")
            Students = parser.parse(iStream)

            var text = ""
            for(Student in Students){
                text += "${Student.id} - ${Student.name} - ${Student.marks}\n"
            }
            tvMain.text = text

        }catch (e: IOException) {
            println("ISSUE: $e")
        }
    }
}