package br.com.moscatech.dumbpassword.ui.screens.new_group

import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.viewModels
import br.com.moscatech.dumbpassword.base.BaseDialogFragment
import br.com.moscatech.dumbpassword.databinding.DialogNewGroupBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewGroupDialog : BaseDialogFragment<DialogNewGroupBinding>() {

    companion object {
        const val NEW_GROUP_ARG = "NewGroupDialog.NewGroupArg"
    }

    override val bindingInflater: (LayoutInflater) -> DialogNewGroupBinding
        get() = DialogNewGroupBinding::inflate

    override val viewModel: NewGroupViewModel by viewModels()

    override fun initViews() {
        binding.etNewGroupName.setOnEditorActionListener { textView, actionId, _ ->
            viewModel.onEditorActionListener(textView, actionId)
        }
    }

    override fun initObservers() {
        viewModel.groupSaved.observe(this) {
           setNavigationResult(NEW_GROUP_ARG, it)
            dismiss()
        }
    }
}