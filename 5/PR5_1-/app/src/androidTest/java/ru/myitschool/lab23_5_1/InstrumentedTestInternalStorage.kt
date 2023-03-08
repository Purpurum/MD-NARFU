package ru.myitschool.lab23_5_1

import androidx.test.espresso.*
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.*
import org.junit.runner.RunWith
import org.junit.runners.MethodSorters
import java.util.*


@RunWith(AndroidJUnit4::class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class InstrumentedTestInternalStorage {
    private val filePath = "/data/user/0/ru.myitschool.lab23/files/words.txt"

}
