package br.com.grupo4.classoverflow.feature.question

import android.content.Context
import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import br.com.grupo4.classoverflow.R
import br.com.grupo4.classoverflow.data.model.CommentModel
import br.com.grupo4.classoverflow.databinding.ItemCommentBinding

class CommentAdapter(
    private val context: Context,
    private val currentUserEmail: String,
    private val onLikeClick: (String, Boolean) -> Unit
) : ListAdapter<CommentModel, CommentAdapter.ViewHolder>(CommentAdapterDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)

        holder.bind(context, item, currentUserEmail, onLikeClick)
    }

    class ViewHolder private constructor(private val binding: ItemCommentBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(
            context: Context,
            item: CommentModel,
            currentUserEmail: String,
            onLikeClick: (String, Boolean) -> Unit
        ) {
            val like = item.likes.find { it.ownerEmail == currentUserEmail }
            val count = item.likes.count { it.isLiked } + item.likes.count { !it.isLiked }
            binding.model = item
            binding.likeCount = count

            like?.let {
                if (it.isLiked) setCommentLikeUp(context)
                else setCommentLikeDown(context)
            }

            binding.commentUpLike.setOnClickListener {
                onLikeClick(item._id, true)
                setCommentLikeUp(context)
                if (like != null && like.isLiked) binding.likeCount = count
                else if(like != null && !like.isLiked) binding.likeCount = count + 2
                else binding.likeCount = count + 1
            }

            binding.commentDownLike.setOnClickListener {
                onLikeClick(item._id, false)
                setCommentLikeDown(context)
                if (like != null && !like.isLiked) binding.likeCount = count
                else if(like != null && like.isLiked) binding.likeCount = count - 2
                else binding.likeCount = count - 1
            }

            binding.executePendingBindings()
        }

        private fun setCommentLikeUp(context: Context) {
            binding.commentUpLike.imageTintList =
                ColorStateList.valueOf(ContextCompat.getColor(context, R.color.color_primary))
            binding.commentDownLike.imageTintList =
                ColorStateList.valueOf(ContextCompat.getColor(context, R.color.color_on_surface))
        }

        private fun setCommentLikeDown(context: Context) {
            binding.commentDownLike.imageTintList =
                ColorStateList.valueOf(ContextCompat.getColor(context, R.color.color_error))
            binding.commentUpLike.imageTintList =
                ColorStateList.valueOf(ContextCompat.getColor(context, R.color.color_on_surface))
        }

        companion object {
            fun from(parent: ViewGroup) = ViewHolder(
                ItemCommentBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        }
    }
}

class CommentAdapterDiffCallback : DiffUtil.ItemCallback<CommentModel>() {
    override fun areItemsTheSame(oldItem: CommentModel, newItem: CommentModel): Boolean {
        return oldItem._id == newItem._id
    }

    override fun areContentsTheSame(oldItem: CommentModel, newItem: CommentModel): Boolean {
        return oldItem == newItem
    }
}
