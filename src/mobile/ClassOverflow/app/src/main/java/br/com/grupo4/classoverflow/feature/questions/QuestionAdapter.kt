package br.com.grupo4.classoverflow.feature.questions

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import br.com.grupo4.classoverflow.data.model.Question
import br.com.grupo4.classoverflow.databinding.ItemQuestionBinding

class QuestionAdapter(private val onClick: (Question) -> Unit) :
    ListAdapter<Question, QuestionAdapter.ViewHolder>(QuestionAdapterDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)

        holder.bind(item, onClick)
    }

    class ViewHolder private constructor(private val binding: ItemQuestionBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Question, onClick: (Question) -> Unit) {
            binding.model = item

            binding.questionCard.setOnClickListener {
                onClick(item)
            }

            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup) = ViewHolder(
                ItemQuestionBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        }
    }
}

class QuestionAdapterDiffCallback : DiffUtil.ItemCallback<Question>() {
    override fun areItemsTheSame(oldItem: Question, newItem: Question): Boolean {
        return oldItem.title == newItem.title
    }

    override fun areContentsTheSame(oldItem: Question, newItem: Question): Boolean {
        return oldItem == newItem
    }
}
