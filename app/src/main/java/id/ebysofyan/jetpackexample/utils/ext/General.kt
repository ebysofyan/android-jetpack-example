package gdk.ebysofyan.mpvexample.utils.ext

import android.content.Context
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.*

fun String.dateFormat(format: String? = "EEEE, dd MMMM yyyy"): String {
    val date = SimpleDateFormat("yyyy-MM-dd").parse(this)
    return SimpleDateFormat(format, Locale("en", "UK")).format(date)
}

fun Context.longToast(msg: String) {
    Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
}