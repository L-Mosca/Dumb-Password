package br.com.moscatech.dumbpassword.ui.screens.new_platform

import android.view.LayoutInflater
import androidx.fragment.app.viewModels
import br.com.moscatech.dumbpassword.base.BaseDialogFragment
import br.com.moscatech.dumbpassword.databinding.DialogNewPlatformBinding
import br.com.moscatech.dumbpassword.utils.showKeyboard
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewPlatformDialog : BaseDialogFragment<DialogNewPlatformBinding>() {

    companion object {
        const val NEW_PLATFORM_ARG = "NewPlatformDialog.NewPlatformArg"
    }

    override val bindingInflater: (LayoutInflater) -> DialogNewPlatformBinding
        get() = DialogNewPlatformBinding::inflate

    override val viewModel: NewPlatformViewModel by viewModels()

    override fun initViews() {
        binding.etNewPlatformName.showKeyboard(requireActivity().window)

        with(binding.etNewPlatformName) {
            setOnEditorActionListener { textView, actionId, _ ->
                viewModel.onEditorActionListener(textView, actionId)
            }
        }
    }

    override fun initObservers() {
        // return to 'GroupFragment'
        viewModel.platformSaved.observe(this) {
            setNavigationResult(NEW_PLATFORM_ARG, it)
            dismiss()
        }

        viewModel.platformName.observe(this) {
            binding.etNewPlatformName.setText(it)
            binding.etNewPlatformName.setSelection(it.length)
        }
    }
}