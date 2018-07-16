package demo.xiaxiayige.com.wordcut

import android.content.Context
import android.graphics.*
import android.support.v4.math.MathUtils
import android.util.AttributeSet
import android.util.TypedValue
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
class WordCutView(context: Context, attrs: AttributeSet?) : LinearLayout(context, attrs) {

    var defaultString = "abcdefghijk"
    val defaultSize = 16
    val bigSize = 20
    var leftMargin = 8
    var rightMargin = 8

    init {
        orientation = LinearLayout.HORIZONTAL
        gravity = Gravity.CENTER

        leftMargin = BaseUtils.dpToPx(context, 8.0f).toInt()
        rightMargin = BaseUtils.dpToPx(context, 8.0f).toInt()

        addWorld(defaultString)
    }

    private fun addWorld(defaultString: String) {
        var singleWorlds = defaultString.toCharArray()
        singleWorlds.forEach {
            addView(createTextView(it))
        }
    }

    fun Number.spToPx(context: Context) = TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_SP, this.toFloat(), context.resources.displayMetrics)

    fun createTextView(word: Char): TextView {
        return TextView(context).apply {
            text = word.toString()
            textSize = defaultSize.spToPx(context)
            setTextColor(Color.BLACK)
            layoutParams = LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT).apply {
                setMargins(leftMargin, 0, rightMargin, 0)
            }
//            setBackgroundColor(Color.BLACK)
        }
    }

    /***
     * 监听滑动位置
     */
    fun updateScorllx(x: Int): String {
        var list = ArrayList<Pair<Float, Float>>()
        var selectWord = ""
        //遍历每个text View的坐标位置
        for (item in 0..childCount) {
            var txtv = getChildAt(item)
            if (txtv != null) {
                txtv = txtv as TextView
                if (item == 0) {
                    list.add(Pair(txtv.x - leftMargin, txtv.x + txtv.width + leftMargin + rightMargin)) // 第一个坐标
                } else if (item == childCount - 1) { //最后一个
                    list.add(Pair(txtv.x, txtv.x + txtv.width))
                } else {
                    list.add(Pair(txtv.x, txtv.x + txtv.width + leftMargin + rightMargin)) //
                }
            }
        }
        var wordIndex = -1
        //变化坐标位置的view
        list.forEachIndexed { index, pair ->
            if (x >= pair.first && x <= pair.second) {
                wordIndex = index
                var textView = getChildAt(wordIndex) as TextView
                textView.textSize = bigSize.spToPx(context)
                //滑动到中间位置 左右2测的字符都变大
                if (index + 1 < list.size) {
                    var textView = getChildAt(index + 1) as TextView
                    textView.textSize = bigSize.spToPx(context)
                }
            } else {
                var textView = getChildAt(index) as TextView
                textView.textSize = defaultSize.spToPx(context)
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