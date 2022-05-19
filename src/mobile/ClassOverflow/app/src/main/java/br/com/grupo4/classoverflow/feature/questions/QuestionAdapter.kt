package br.com.grupo4.classoverflow.feature.questions

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import br.com.grupo4.classoverflow.data.model.QuestionModel
import br.com.grupo4.classoverflow.databinding.ItemQuestionBinding

class QuestionAdapter(private val onClick: (QuestionModel) -> Unit) :
    ListAdapter<QuestionModel, QuestionAdapter.ViewHolder>(QuestionAdapterDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)

        holder.bind(item, onClick)
    }

    class ViewHolder private constructor(private val binding: ItemQuestionBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: QuestionModel, onClick: (QuestionModel) -> Unit) {
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

class QuestionAdapterDiffCallback : DiffUtil.ItemCallback<QuestionModel>() {
    override fun areItemsTheSame(oldItem: QuestionModel, newItem: QuestionModel): Boolean {
        return oldItem.title == newItem.title
    }

    override fun areContentsTheSame(oldItem: QuestionModel, newItem: QuestionModel): Boolean {
        return oldItem == newItem
    }
}
