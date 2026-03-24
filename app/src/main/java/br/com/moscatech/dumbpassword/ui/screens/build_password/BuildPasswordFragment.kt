package br.com.moscatech.dumbpassword.ui.screens.build_password

import android.view.LayoutInflater
import androidx.fragment.app.viewModels
import br.com.moscatech.dumbpassword.base.BaseFragment
import br.com.moscatech.dumbpassword.databinding.FragmentBuildPasswordBinding
import br.com.moscatech.dumbpassword.utils.popBackStack
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BuildPasswordFragment : BaseFragment<FragmentBuildPasswordBinding>() {

    override val bindingInflater: (LayoutInflater) -> FragmentBuildPasswordBinding
        get() = FragmentBuildPasswordBinding::inflate

    override val viewModel: BuildPasswordViewModel by viewModels()

    override fun initViews() {
        binding.buildPasswordAppBar.setOnBackClickListener {
            popBackStack()
        }


    }

    override fun initObservers() {}
}