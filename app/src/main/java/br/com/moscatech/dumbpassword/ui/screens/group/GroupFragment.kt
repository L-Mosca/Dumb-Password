package br.com.moscatech.dumbpassword.ui.screens.group

import android.view.LayoutInflater
import androidx.fragment.app.viewModels
import br.com.moscatech.dumbpassword.base.BaseFragment
import br.com.moscatech.dumbpassword.databinding.FragmentGroupBinding
import br.com.moscatech.dumbpassword.utils.navigate
import br.com.moscatech.dumbpassword.utils.popBackStack
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GroupFragment : BaseFragment<FragmentGroupBinding>() {

    override val bindingInflater: (LayoutInflater) -> FragmentGroupBinding
        get() = FragmentGroupBinding::inflate

    override val viewModel: GroupViewModel by viewModels()

    override fun initViews() {
        binding.apply {
            groupAppBar.setOnBackClickListener { popBackStack() }

            btNewGroup.setOnClickListener {
                val direction = GroupFragmentDirections.actionGroupFragmentToFragmentNewGroup()
                navigate(direction)
            }
        }
    }

    override fun initObservers() {}
}