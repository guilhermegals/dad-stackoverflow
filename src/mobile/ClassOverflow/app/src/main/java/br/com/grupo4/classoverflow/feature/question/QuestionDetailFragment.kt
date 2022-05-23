package br.com.grupo4.classoverflow.feature.question

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import br.com.grupo4.classoverflow.R
import br.com.grupo4.classoverflow.core.EventObserver
import br.com.grupo4.classoverflow.core.Utils
import br.com.grupo4.classoverflow.databinding.FragmentQuestionDetailBinding
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class QuestionDetailFragment : Fragment() {

    companion object {
        const val ARG_QUESTION_ID = "ARG_QUESTION_ID"
    }

    // region [ Properties ]

    private var _binding: FragmentQuestionDetailBinding? = null
    private val binding get() = _binding!!

    private val viewModel: QuestionDetailViewModel by viewModels()

    private lateinit var adapter: CommentAdapter

    // endregion

    // region [ Actions ]

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupExtras()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentQuestionDetailBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupAdapter()
        setupObservers()
    }

    // endregion

    // region [ Private Functions ]

    private fun setupExtras() {
        arguments?.let { bundle ->
            viewModel.getQuestion(bundle.getString(ARG_QUESTION_ID, ""))
        }
    }

    private fun setupAdapter() {
        adapter = CommentAdapter(requireContext(), viewModel.getCurrentUserEmail(), ::addLike)

        binding.questionDetailCommentsList.adapter = adapter
    }

    private fun setupObservers() {
        viewModel.backEvent.observe(viewLifecycleOwner, EventObserver {
            Utils.vibrate(requireContext())
            if (it) openHome()
        })

        viewModel.errorEvent.observe(viewLifecycleOwner, EventObserver {
            if (it) showErrorSnackbar()
        })

        viewModel.likeEvent.observe(viewLifecycleOwner, EventObserver {
            showLikeSnackbar(it)
        })

        viewModel.likeErrorEvent.observe(viewLifecycleOwner, EventObserver {
            if (it) showLikeErrorSnackbar()
        })

        viewModel.question.observe(viewLifecycleOwner) { question ->
            binding.model = question
            adapter.submitList(question.comments)
        }
    }

    private fun addLike(userEmail: String, liked: Boolean) {
        Utils.vibrate(requireContext())
        viewModel.like(userEmail, liked)
    }

    private fun showErrorSnackbar() {
        Snackbar.make(
            requireContext(),
            binding.root,
            getString(R.string.question_detail_cant_load),
            Snackbar.LENGTH_LONG
        ).setTextColor(ContextCompat.getColor(requireContext(), R.color.color_on_primary))
            .setBackgroundTint(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.color_on_background
                )
            )
            .addCallback(object : Snackbar.Callback() {
                override fun onDismissed(transientBottomBar: Snackbar?, event: Int) {
                    super.onDismissed(transientBottomBar, event)
                    openHome()
                }
            }).show()
    }

    private fun showLikeSnackbar(liked: Boolean) {
        Snackbar.make(
            requireContext(),
            binding.root,
            if (liked) getString(R.string.like_add) else getString(R.string.like_removed),
            Snackbar.LENGTH_LONG
        ).setTextColor(
            ContextCompat.getColor(requireContext(), R.color.color_on_primary)
        ).setBackgroundTint(
            if (liked) ContextCompat.getColor(requireContext(), R.color.color_primary)
            else ContextCompat.getColor(requireContext(), R.color.color_error)
        ).show()
    }

    private fun showLikeErrorSnackbar() {
        Snackbar.make(
            requireContext(),
            binding.root,
            getString(R.string.like_error),
            Snackbar.LENGTH_LONG
        ).setTextColor(ContextCompat.getColor(requireContext(), R.color.color_on_primary))
            .setBackgroundTint(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.color_on_background
                )
            )
            .show()
    }

    private fun openHome() {
        findNavController().navigate(R.id.action_navigation_question_detail_to_navigation_home)
    }

    // endregion
}