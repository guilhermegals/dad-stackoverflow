package br.com.grupo4.classoverflow.feature.questions

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import br.com.grupo4.classoverflow.R
import br.com.grupo4.classoverflow.core.EventObserver
import br.com.grupo4.classoverflow.databinding.FragmentQuestionsBinding
import br.com.grupo4.classoverflow.feature.question.QuestionDetailFragment
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class QuestionsFragment : Fragment() {

    // region [ Properties ]

    private var _binding: FragmentQuestionsBinding? = null
    private val binding get() = _binding!!

    private val viewModel: QuestionsViewModel by viewModels()

    private lateinit var adapter: QuestionAdapter

    // endregion

    // region [ Actions ]

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentQuestionsBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupAdapter()
        setupObservers()

        viewModel.getQuestions()
    }

    // endregion

    // region [ Private Functions ]

    private fun setupAdapter() {
        adapter = QuestionAdapter(::openQuestion)

        binding.questionsList.adapter = adapter
    }

    private fun setupObservers() {
        viewModel.questions.observe(viewLifecycleOwner) { questions ->
            adapter.submitList(questions)
        }

        viewModel.errorEvent.observe(viewLifecycleOwner, EventObserver {
            if (it) showGetQuestionsErrorSnackbar()
        })
    }

    private fun showGetQuestionsErrorSnackbar() {
        Snackbar.make(
            requireContext(),
            binding.root,
            getString(R.string.questions_cant_load_questions),
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

    private fun openQuestion(id: String) {
        val bundle = Bundle().apply {
            putString(QuestionDetailFragment.ARG_QUESTION_ID, id)
        }

        findNavController().navigate(
            R.id.action_navigation_home_to_navigation_question_detail,
            bundle
        )
    }

    // endregion
}