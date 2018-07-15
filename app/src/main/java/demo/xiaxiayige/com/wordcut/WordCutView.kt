package demo.xiaxiayige.com.wordcut

import android.content.Context
import android.graphics.*
import android.support.v4.math.MathUtils
import android.util.AttributeSet
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import java.util.ArrayList

/***
 * 外层容器
 */
class WordCutView(context: Context?, attrs: AttributeSet?) : LinearLayout(context, attrs) {

    var defaultString = "abcdefghijk"

    init {
        orientation = LinearLayout.HORIZONTAL
        gravity = Gravity.CENTER
        setBackgroundColor(Color.GRAY)
        addWorld(defaultString)
    }

    private fun addWorld(defaultString: String) {
        var singleWorlds = defaultString.toCharArray()
        singleWorlds.forEach {
            addView(createTextView(it))
        }
    }

    fun createTextView(word: Char): TextView {
        return TextView(context).apply {
            text = word.toString()
            textSize = 24.0f
            setTextColor(Color.WHITE)
            layoutParams = LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT).apply {
                setMargins(8, 0, 8, 0)
            }
            setBackgroundColor(Color.BLACK)
        }
    }

    fun updateScorllx(x: Int): String {
        var list = ArrayList<Pair<Float, Float>>()
        var selectWord = ""
        for (item in 0..childCount) {
            var txtv = getChildAt(item)
            if (txtv != null) {
                txtv = txtv as TextView
                if (item == 0) {
                    list.add(Pair(txtv.x - 8, txtv.x + txtv.width + 16)) // 第一个坐标
                } else if (item == childCount - 1) { //最后一个
                    list.add(Pair(txtv.x, txtv.x + txtv.width))
                } else {
                    list.add(Pair(txtv.x, txtv.x + txtv.width + 16)) //
                }
            }
        }
        var wordIndex = -1
        list.forEachIndexed { index, pair ->
            if (x >= pair.first && x <= pair.second) {
                wordIndex = index
            }
        }
        if (wordIndex != -1) {
            var view = getChildAt(wordIndex)
            if (view != null) {
                var txtv = view as TextView
                selectWord = txtv.text.toString()
            }
        }
        println(selectWord)
        return selectWord
    }
}