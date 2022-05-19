package br.com.grupo4.classoverflow.core

import android.view.View
import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import br.com.grupo4.classoverflow.R
import com.google.android.material.textview.MaterialTextView
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@BindingAdapter("android:goneUnless")
fun View.goneUnless(visible: Boolean) {
    this.visibility = if (visible) View.VISIBLE else View.GONE
}

@BindingAdapter("android:textDate")
fun MaterialTextView.setTextDate(date: LocalDateTime?) {
    this.text = if (date == null) "" else date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
}

@BindingAdapter("android:textHashtags")
fun MaterialTextView.setHashtags(hashtags: List<String>) {
    this.text = hashtags.joinToString(" ")
}

@BindingAdapter("android:setLike")
fun AppCompatImageView.setLike(isLiked: Boolean) {
    this.setBackgroundResource(if (isLiked) R.drawable.ic_favorite_active else R.drawable.ic_favorite_unactive)
}
