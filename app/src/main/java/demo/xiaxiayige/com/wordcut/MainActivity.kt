package demo.xiaxiayige.com.wordcut

import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewTreeObserver
import android.widget.SeekBar
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        wordRootView.addLine(0)

        txtv_cut.setOnClickListener {
            wordRootView.addLine(0)
        }

        wordRootView.viewTreeObserver.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                var layoutParamets = wordRootView.layoutParams
                seekbar.max = wordRootView.width - wordRootView.paddingLeft - wordRootView.paddingRight-3
                println(cutId.width)
                println("seekbar.max = ${seekbar.max}")
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    wordRootView.viewTreeObserver.removeOnGlobalLayoutListener(this)
                } else {
                    wordRootView.viewTreeObserver.removeGlobalOnLayoutListener(this)
                }
            }
        })


        seekbar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                println("seekBar = [${seekBar}], progress = [${progress}], fromUser = [${fromUser}]")
                wordRootView.lineScorll(progress)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {

            }

        })
    }

    override fun onStart() {
        super.onStart()

    }
}
