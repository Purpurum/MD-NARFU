package study.android.room2


import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import android.widget.AdapterView.OnItemSelectedListener
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.get
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class MainActivity : AppCompatActivity() {

    private lateinit var rbStudent: RadioButton
    private lateinit var rbSubject: RadioButton
    private lateinit var spinner: Spinner
    private lateinit var listCaption: TextView
    private lateinit var recyclerView: RecyclerView
    private lateinit var tvAdapter: CustomRecyclerAdapter


    val db by lazy {
        Room.databaseBuilder(
            this,
            SchoolDatabase::class.java, "school.db"
        ).build()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        rbStudent = findViewById(R.id.rbStudent)
        rbSubject = findViewById(R.id.rbSubject)
        spinner = findViewById(R.id.spinner)
        listCaption = findViewById(R.id.listCaption)
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)

        rbStudent.setOnClickListener{
            listCaption.text = "Student's subjects"
            GlobalScope.launch {
                val li = db.schoolDao.getStudents()
                withContext(Dispatchers.Main) {
                    spinner.adapter = ArrayAdapter(
                        this@MainActivity,
                        android.R.layout.simple_spinner_item, li
                    )}
            }
        }

        rbSubject.setOnClickListener{
            listCaption.text = "Students study"
            GlobalScope.launch {
                val li = db.schoolDao.getSubjects()
                withContext(Dispatchers.Main) {
                spinner.adapter = ArrayAdapter(
                    this@MainActivity,
                    android.R.layout.simple_spinner_item, li
                )}
            }
        }

        spinner.adapter = ArrayAdapter(
            this@MainActivity,
            android.R.layout.simple_spinner_item, arrayOf("Выберите чекпоинт")
        )


        spinner.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>, view: View?,
                position: Int, id: Long
            ) {
                if (rbStudent.isChecked)
                GlobalScope.launch {
                    val l = db.schoolDao.getSubjectsOfStudent(spinner.selectedItem.toString())
                    val new: MutableList<String> = mutableListOf()
                    for (i in l[0].subjects)
                        new.add(i.subjectName)
                    withContext(Dispatchers.Main) {
                        recyclerView.adapter = CustomRecyclerAdapter(new)}
                    }
                if (rbSubject.isChecked)
                GlobalScope.launch {
                    val l = db.schoolDao.getStudentsOfSubject(spinner.selectedItem.toString())
                    val new: MutableList<String> = mutableListOf()
                    for (i in l[0].students)
                        new.add(i.studentName)
                    withContext(Dispatchers.Main) {
                            recyclerView.adapter = CustomRecyclerAdapter(new)}
                    }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // TODO Auto-generated method stub
            }
        }

        lifecycleScope.launch {
            DataExample.directors.forEach { db.schoolDao.insertDirector(it) }
            DataExample.schools.forEach { db.schoolDao.insertSchool(it) }
            DataExample.subjects.forEach { db.schoolDao.insertSubject(it) }
            DataExample.students.forEach { db.schoolDao.insertStudent(it) }
            DataExample.studentSubjectRelations.forEach { db.schoolDao.insertStudentSubjectCrossRef(it) }
        }
    }


}