package br.com.moscatech.dumbpassword.ui.screens.create_password

import android.view.LayoutInflater
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import br.com.moscatech.dumbpassword.R
import br.com.moscatech.dumbpassword.base.BaseFragment
import br.com.moscatech.dumbpassword.databinding.FragmentCreatePasswordBinding
import br.com.moscatech.dumbpassword.utils.setupAppBar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CreatePasswordFragment : BaseFragment<FragmentCreatePasswordBinding>() {

    override val bindingInflater: (LayoutInflater) -> FragmentCreatePasswordBinding
        get() = FragmentCreatePasswordBinding::inflate

    override val viewModel: CreatePasswordViewModel by viewModels()

    override fun initViews() {
        binding.includeAppBar.setupAppBar(
            getString(R.string.new_password),
            onBackClicked = { findNavController().popBackStack() },
        )
    }

    override fun initObservers() {}
}