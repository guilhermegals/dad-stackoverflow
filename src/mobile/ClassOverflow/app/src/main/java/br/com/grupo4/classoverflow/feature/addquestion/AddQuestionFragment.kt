package br.com.grupo4.classoverflow.feature.addquestion

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import br.com.grupo4.classoverflow.R
import br.com.grupo4.classoverflow.core.EventObserver
import br.com.grupo4.classoverflow.databinding.FragmentAddQuestionBinding
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddQuestionFragment : Fragment() {

    companion object {
        const val ARG_QUESTION_ID = "ARG_QUESTION_ID"
    }

    // region [ Properties ]

    private var _binding: FragmentAddQuestionBinding? = null
    private val binding get() = _binding!!

    private val viewModel: AddQuestionViewModel by viewModels()

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
        _binding = FragmentAddQuestionBinding.inflate(inflater, container, false)
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

    private fun setupExtras() {
        arguments?.let { bundle ->
            viewModel.setId(bundle.getString(ARG_QUESTION_ID))
        }
    }

    private fun setupObservers() {
        viewModel.backEvent.observe(viewLifecycleOwner, EventObserver {
            if (it) back()
        })

        viewModel.errorEvent.observe(viewLifecycleOwner, EventObserver {
            if (it) showErrorSnackbar()
        })

        viewModel.invalidFieldEvent.observe(viewLifecycleOwner, EventObserver {
            if (it) showInvalidFieldSnackbar()
        })

        viewModel.isEditMode.observe(viewLifecycleOwner) {
            if (it) {
                binding.addQuestionTitle.text = getString(R.string.edit_question_title)
            } else {
                binding.addQuestionTitle.text = getString(R.string.add_question_title)

            }
        }
    }

    private fun showErrorSnackbar() {
        Snackbar.make(
            requireContext(),
            binding.root,
            getString(R.string.cant_execut_action),
            Snackbar.LENGTH_LONG
        ).setTextColor(ContextCompat.getColor(requireContext(), R.color.color_on_primary))
            .setBackgroundTint(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.color_on_background
                )
            ).show()
    }

    private fun showInvalidFieldSnackbar(){
        Snackbar.make(
            requireContext(),
            binding.root,
            getString(R.string.all_fields_required),
            Snackbar.LENGTH_LONG
        ).setTextColor(
            ContextCompat.getColor(requireContext(), R.color.color_on_primary)
        ).setBackgroundTint(
            ContextCompat.getColor(requireContext(), R.color.color_error)
        ).show()
    }

    private fun back() {
        requireActivity().onBackPressed()
    }

    // endregion
}