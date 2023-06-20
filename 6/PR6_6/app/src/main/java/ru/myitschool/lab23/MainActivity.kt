package ru.myitschool.lab23

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import org.xmlpull.v1.XmlPullParser
import org.xmlpull.v1.XmlPullParserFactory
import java.io.InputStream
import android.os.Handler
import android.os.Looper
import android.view.View
import java.io.InputStreamReader
import java.nio.charset.Charset

class MainActivity : AppCompatActivity() {
    private lateinit var wordTextView: TextView
    private lateinit var nextWordTextView: TextView
    private lateinit var wordList: MutableList<String>
    private lateinit var translationList: MutableList<String>
    private var currentIndex = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        wordTextView = findViewById(R.id.word)
        nextWordTextView = findViewById(R.id.next_word)

        loadWordDataFromXml()
        displayWord()

        wordTextView.setOnClickListener {
            displayTranslation()
        }
    }

    private fun loadWordDataFromXml() {
        wordList = mutableListOf()
        translationList = mutableListOf()

        val xmlInputStream: InputStream = assets.open("korean_default.xml")
        val factory = XmlPullParserFactory.newInstance()
        val parser = factory.newPullParser()
        parser.setInput(InputStreamReader(xmlInputStream, Charset.forName("UTF-16")))

        var eventType = parser.eventType
        while (eventType != XmlPullParser.END_DOCUMENT) {
            if (eventType == XmlPullParser.START_TAG && parser.name == "word") {
                val word = parser.nextText().trim()
                wordList.add(word)
            } else if (eventType == XmlPullParser.START_TAG && parser.name == "translations") {
                while (parser.next() != XmlPullParser.END_TAG) {
                    if (parser.eventType == XmlPullParser.START_TAG && parser.name == "word") {
                        val translation = parser.nextText().trim()
                        translationList.add(translation)
                    }
                }
            }

            eventType = parser.next()
        }
    }

    @SuppressLint("SetTextI18n")
    private fun displayWord() {
        if (currentIndex < wordList.size) {
            val word = wordList[currentIndex]
            wordTextView.text = word
            nextWordTextView.text = if (currentIndex < wordList.size - 1) wordList[currentIndex + 1] else ""
        } else {
            wordTextView.visibility = View.GONE
            nextWordTextView.text = "Слова закончились((("
        }
    }

    private fun displayTranslation() {
        if (currentIndex < wordList.size) {
            val translation = translationList[currentIndex]
            wordTextView.text = translation
        }

        Handler(Looper.getMainLooper()).postDelayed({
            currentIndex++
            displayWord()
        }, 5000)
    }

}