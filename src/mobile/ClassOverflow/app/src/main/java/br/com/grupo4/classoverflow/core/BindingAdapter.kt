package br.com.grupo4.classoverflow.core

import android.view.View
import androidx.appcompat.widget.AppCompatImageButton
import androidx.databinding.BindingAdapter
import br.com.grupo4.classoverflow.R
import com.google.android.material.textview.MaterialTextView
import java.text.SimpleDateFormat
import java.util.*

@BindingAdapter("android:goneUnless")
fun View.goneUnless(visible: Boolean) {
    this.visibility = if (visible) View.VISIBLE else View.GONE
}

@BindingAdapter("android:textDate")
fun MaterialTextView.setTextDate(date: String?) {
    date?.let {
        val localDate = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
            .parse(date.split('T').first())

        this.text = if (localDate == null) ""
        else SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(localDate)
    }
}

@BindingAdapter("android:textHashtags")
fun MaterialTextView.setHashtags(hashtags: List<String>?) {
    this.text = hashtags?.joinToString(separator = " #", prefix = "#")
}

@BindingAdapter("android:textInt")
fun MaterialTextView.setTextInt(count: Int?) {
    this.text = "${count ?: 0}"
}
