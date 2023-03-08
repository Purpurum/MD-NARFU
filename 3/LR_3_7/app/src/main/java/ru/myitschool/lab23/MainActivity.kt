package ru.myitschool.lab23

import android.app.Dialog
import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import ru.myitschool.lab23.databinding.ActivityMainBinding
import java.util.logging.Logger.global

var glname = ""
var gldate = ""
var gltime = ""
var glnotes = ""

class MyAlertDialog: DialogFragment(){
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            AlertDialog.Builder(it, )
                .setMessage("Название: $glname\n" +
                            "Дата: $gldate\n" +
                            "Время: $gltime\n" +
                            "Заметки: $glnotes")
                .setPositiveButton("ОК", null)
                .create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }
}
//, name : String, date : String, time : String, notes : String
class MainActivity : AppCompatActivity() {
    private var binding: ActivityMainBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding!!.root)

        var cal = binding!!.content.calendarView
        var name = binding!!.content.editTextTextPersonName
        var time = binding!!.content.editTextTime
        var date = binding!!.content.editTextDate
        var notes = binding!!.content.editTextTextMultiLine

        var okbtn = binding!!.content.save

        cal.setOnDateChangeListener { view, year, month, dayOfMonth -> val Date = (dayOfMonth.toString() + "." + (month + 1) + "." + year)
            date.setText(Date)
        }

        okbtn.setOnClickListener {
            glname = name.text.toString()
            gldate = date.text.toString()
            gltime = time.text.toString()
            glnotes = notes.text.toString()

            MyAlertDialog().show(supportFragmentManager, "EmptyDialog")

            name.text.clear()
            time.text.clear()
            date.text.clear()
            notes.text.clear()


        }
    }
}