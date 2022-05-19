package br.com.grupo4.classoverflow.feature.question

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import br.com.grupo4.classoverflow.databinding.FragmentQuestionDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class QuestionDetailFragment : Fragment() {

    // region [ Properties ]

    private var _binding: FragmentQuestionDetailBinding? = null
    private val binding get() = _binding!!

    private val viewModel: QuestionDetailViewModel by viewModels()

    // endregion

    // region [ Actions ]

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

        setupObservers()
    }

    // endregion

    // region [ Private Functions ]

    private fun setupObservers() {

    }

    // endregion
}