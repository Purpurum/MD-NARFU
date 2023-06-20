package study.android.kotlindbnotes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_stat.*

class StatActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stat)

        val dbHelper = SimpleDBHelper(this)
        var data = dbHelper.getAll("RESULT DESC")
        var capSumm = 0
        var compNum = 0
        var avgNum = 0
        var engNum = 0
        var compHighestCapNum = 0
        var compHighestCap = ""
        var compLongestName = ""

        for (r in data) {
            capSumm += r.result
            compNum += 1
            if (Regex("[a-zA-Z]+").matches(r.name)) { engNum += 1 }
            if (r.result > compHighestCapNum) { compHighestCapNum = r.result; compHighestCap = r.name }
            if (r.name.count() > compLongestName.count()) { compLongestName = r.name }
        }

        var avg = capSumm/compNum

        for (r in data) {
            if (r.result >= avg) { avgNum += 1 }
        }

        money.text = capSumm.toString()
        good.text = avgNum.toString()
        english.text = engNum.toString()
        best.text = compHighestCap
        longest.text = compLongestName
    }
}