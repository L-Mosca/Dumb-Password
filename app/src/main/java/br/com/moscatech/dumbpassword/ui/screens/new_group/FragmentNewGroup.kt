package br.com.moscatech.dumbpassword.ui.screens.new_group

import android.view.LayoutInflater
import androidx.fragment.app.viewModels
import br.com.moscatech.dumbpassword.base.BaseFragment
import br.com.moscatech.dumbpassword.databinding.FragmentNewGroupBinding
import br.com.moscatech.dumbpassword.utils.popBackStack
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FragmentNewGroup : BaseFragment<FragmentNewGroupBinding>() {

    override val bindingInflater: (LayoutInflater) -> FragmentNewGroupBinding
        get() = FragmentNewGroupBinding::inflate

    override val viewModel: NewGroupViewModel by viewModels()

    override fun initViews() {
        binding.apply {
            newGroupAppBar.setOnBackClickListener { popBackStack() }
        }
    }

    override fun initObservers() {}
}