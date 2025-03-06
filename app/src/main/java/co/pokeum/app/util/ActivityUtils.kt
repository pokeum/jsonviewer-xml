package co.pokeum.app.util

import android.graphics.drawable.ColorDrawable
import androidx.annotation.ColorInt
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity

fun AppCompatActivity.changeActionBarColor(@ColorInt color: Int) {
    val actionBar: ActionBar? = supportActionBar
    val colorDrawable = ColorDrawable(color)
    actionBar?.setBackgroundDrawable(colorDrawable)
}