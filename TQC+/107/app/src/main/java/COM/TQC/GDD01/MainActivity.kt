package COM.TQC.GDD01

import android.content.Context
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.KeyEvent
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.LinearLayout
import com.lndata.hellosurfaceview.MySurfaceView
import kotlinx.android.synthetic.main.main.*

class MainActivity : AppCompatActivity() {

    companion object
    {
        private val TAG = "HIPPO_DEBUG"
        private lateinit var surfaceViewThread: MySurfaceView
    }

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main)

        init()
    }

    private fun init()
    {
        title = applicationContext.getText(R.string.app_name)
        supportActionBar!!.hide()
        this.window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        val inputManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        surfaceViewThread = MySurfaceView(applicationContext)

        val drawTextCanvas = findViewById<View>(R.id.main_linearLayout) as LinearLayout
        drawTextCanvas.addView(surfaceViewThread)
        val inputText = findViewById<View>(R.id.inputText) as EditText

        inputText.setOnKeyListener{ view, keyCode, keyEvent ->
            if (keyCode == KeyEvent.KEYCODE_ENTER)
            {
                val userInputText = inputText.text.toString()
                surfaceViewThread.text = userInputText
                // TO DO

                hideKeyboard()
            }
            else {
                false
            }
        }
        hideKeyboard()
    }

    private fun hideKeyboard(): Boolean
    {
        // TO DO
        return true
    }

    override fun onConfigurationChanged(newConfig: Configuration?)
    {
        // TO DO
        super.onConfigurationChanged(newConfig)
    }
}