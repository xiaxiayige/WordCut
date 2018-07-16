package demo.xiaxiayige.com.wordcut

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import android.util.DisplayMetrics
import android.view.Window
import android.view.WindowManager
import android.util.TypedValue
import demo.xiaxiayige.com.wordcut.BaseUtils.dpToPx


class WordCutRootView(context: Context, attrs: AttributeSet) : FrameLayout(context, attrs) {

    var currentNumber = 0 //记录当前是哪一根线
    var lineWidth = 3

    init {
        lineWidth = dpToPx(context,1.0f).toInt()
    }



    fun lineScorll(xPoint: Int) {
        if (childCount > 0 && currentNumber != 0) {
            var lineView = getChildAt(currentNumber)
            if (lineView is View)
                lineView.translationX = xPoint.toFloat()
        }
        (getChildAt(0) as WordCutView).updateScorllx(xPoint) //通知wordCutView滑动
    }

    fun getRedLine(): View {
        return View(context).apply {
            if (currentNumber > 1) { // 把上一根线更改颜色
                var v = getChildAt(currentNumber - 1)
                if (v is View) {
                    v.setBackgroundColor(Color.BLUE)
                }
            }
            setBackgroundColor(Color.RED)
            var wordCutView = getChildAt(0) as WordCutView
            layoutParams = LayoutParams(lineWidth, wordCutView.height)
        }
    }

    fun getBlueLine(): View {
        return View(context).apply {
            setBackgroundColor(Color.RED)
            layoutParams = LayoutParams(lineWidth, 360)
        }
    }

    /***
     * 添加线
     */
    fun addLine(type: Int) {
        when (type) {
            0 -> {
                currentNumber++
                addView(getRedLine())
            }
            1 -> { //
                addView(getBlueLine())
                addView(getRedLine())
                currentNumber += 2
            }

        }
    }
}