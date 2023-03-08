package ru.samsung.itschool.a3_9_practical_work

import android.os.Bundle
import android.view.*
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.widget.TextView
import android.widget.Toast

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    public var mActionMode: ActionMode? =null;
    public var mActionModeCallback: ActionMode.Callback? = null;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        mActionModeCallback = object: ActionMode.Callback {
            override fun onCreateActionMode(p0: ActionMode?, p1: Menu?): Boolean {
                p0!!.menuInflater.inflate(R.menu.context_menu,p1)
                return true
            }

            override fun onPrepareActionMode(p0: ActionMode?, p1: Menu?): Boolean {
                return false
            }

            override fun onActionItemClicked(p0: ActionMode?, p1: MenuItem?): Boolean {
                // здесь обрабатываются клики на элементы контекстного AppBar
                if(p1!!.itemId == R.id.search)
                    Toast.makeText(applicationContext, "search", Toast.LENGTH_SHORT).show()
                if(p1!!.itemId == R.id.gallery)
                    Toast.makeText(applicationContext, "gallery", Toast.LENGTH_SHORT).show()
                p0!!.finish()
                return false
            }

            override fun onDestroyActionMode(p0: ActionMode?) {

            }

        }


        fab.setOnClickListener { view ->
            mActionMode= startActionMode(mActionModeCallback);
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        Toast.makeText(applicationContext, "MenuItem click!", Toast.LENGTH_SHORT).show()
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onCreateContextMenu(menu: ContextMenu?, v: View?, menuInfo: ContextMenu.ContextMenuInfo?) {
        super.onCreateContextMenu(menu, v, menuInfo)
        //menuInflater.inflate(R.menu.menu_main, menu)
        menu!!.add(Menu.NONE, 101, Menu.NONE, "Открыть");
        menu!!.add(Menu.NONE, 102, Menu.NONE, "Сохранить");
        menu!!.add(Menu.NONE, 103, Menu.NONE, "Выйти");
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        Toast.makeText(applicationContext, "ContextMenuItem click!", Toast.LENGTH_SHORT).show()
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

}
