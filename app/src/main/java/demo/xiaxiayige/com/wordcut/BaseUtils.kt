package demo.xiaxiayige.com.wordcut

import android.content.Context
import android.util.TypedValue

object BaseUtils {
    fun dpToPx(context: Context, valueInDp: Float): Float {
        val metrics = context.resources.displayMetrics
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, valueInDp, metrics)
    }

    fun Float.spToPx(context: Context) = TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_SP, this.toFloat(), context.resources.displayMetrics).toInt()
}