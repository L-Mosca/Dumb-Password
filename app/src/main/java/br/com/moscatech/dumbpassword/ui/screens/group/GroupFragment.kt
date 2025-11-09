package br.com.moscatech.dumbpassword.ui.screens.group

import android.view.LayoutInflater
import androidx.fragment.app.viewModels
import br.com.moscatech.dumbpassword.base.BaseFragment
import br.com.moscatech.dumbpassword.databinding.FragmentGroupBinding
import br.com.moscatech.dumbpassword.ui.adapters.GroupAdapter
import br.com.moscatech.dumbpassword.ui.screens.new_group.NewGroupDialog
import br.com.moscatech.dumbpassword.utils.navigate
import br.com.moscatech.dumbpassword.utils.popBackStack
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GroupFragment : BaseFragment<FragmentGroupBinding>() {

    override val bindingInflater: (LayoutInflater) -> FragmentGroupBinding
        get() = FragmentGroupBinding::inflate

    override val viewModel: GroupViewModel by viewModels()

    private val adapter by lazy { GroupAdapter() }

    override fun initViews() {
        binding.apply {
            groupAppBar.setOnBackClickListener { popBackStack() }

            btNewGroup.setOnClickListener {
                val direction = GroupFragmentDirections.actionGroupFragmentToNewGroupDialog()
                navigate(direction)
            }


        }
    }

    override fun initObservers() {
        viewModel.groupList.observe(this) { groupList ->
            setupAdapter(groupList)
        }

        getNavigationResult<String>(NewGroupDialog.NEW_GROUP_ARG)?.observe(viewLifecycleOwner) {
            viewModel.getGroups()
        }
    }

    private fun setupAdapter(groupList: List<String>) {
        binding.rvGroups.adapter = adapter
        adapter.submitList(groupList)
        adapter.onItemClicked = {
            showShortToast(it)
        }
    }
}