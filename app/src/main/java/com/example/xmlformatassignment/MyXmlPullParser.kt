package com.example.xmlformatassignment

import org.xmlpull.v1.XmlPullParserException
import org.xmlpull.v1.XmlPullParser
import org.xmlpull.v1.XmlPullParserFactory
import java.io.IOException
import java.io.InputStream

class MyXmlPullParserHandler {
    private val Students = ArrayList<Student>()
    private var text: String? = null


    private var id = 0
    private var SName = ""
    private var Smark = 0

    fun parse(inputStream: InputStream): List<Student> {
        try {
            val factory = XmlPullParserFactory.newInstance()
            val parser = factory.newPullParser()
            parser.setInput(inputStream, null)
            var eventType = parser.eventType
            while (eventType != XmlPullParser.END_DOCUMENT) {
                val tagName = parser.name
                when (eventType) {
                    XmlPullParser.TEXT -> text = parser.text
                    XmlPullParser.END_TAG -> when {
                        tagName.equals("id", ignoreCase = true) -> {
                            id = text!!.toInt()
                        }
                        tagName.equals("name", ignoreCase = true) -> {
                            SName = text.toString()
                        }
                        tagName.equals("marks", ignoreCase = true) -> {
                            Smark = text!!.toInt()
                        }
                        else -> Students.add(Student( id , SName , Smark))
                    }

                    else -> {
                    }
                }
                eventType = parser.next()
            }

        } catch (e: XmlPullParserException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        }

        return Students
    }
}