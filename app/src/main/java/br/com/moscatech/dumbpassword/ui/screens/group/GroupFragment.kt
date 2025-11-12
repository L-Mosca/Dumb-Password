package br.com.moscatech.dumbpassword.ui.screens.group

import android.view.Gravity
import android.view.LayoutInflater
import androidx.appcompat.widget.PopupMenu
import androidx.fragment.app.viewModels
import br.com.moscatech.dumbpassword.R
import br.com.moscatech.dumbpassword.base.BaseFragment
import br.com.moscatech.dumbpassword.databinding.FragmentGroupBinding
import br.com.moscatech.dumbpassword.ui.adapters.GroupAdapter
import br.com.moscatech.dumbpassword.ui.screens.new_group.NewGroupDialog
import br.com.moscatech.dumbpassword.utils.navigate
import br.com.moscatech.dumbpassword.utils.popBackStack
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GroupFragment : BaseFragment<FragmentGroupBinding>() {

    companion object {
        const val SELECTED_GROUP_ARG = "GroupFragment.SelectedGroupArg"
    }

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

        viewModel.showEditDialog.observe(viewLifecycleOwner) { (_, group) ->
            val direction = GroupFragmentDirections.actionGroupFragmentToNewGroupDialog(group)
            navigate(direction)
        }

        // Called when user press 'send' on keyboard in 'NewGroupDialog'
        getNavigationResult<String>(NewGroupDialog.NEW_GROUP_ARG)?.observe(viewLifecycleOwner) {
            viewModel.getGroups()
        }
    }

    private fun setupAdapter(groupList: List<String>) {
        binding.rvGroups.adapter = adapter
        adapter.submitList(groupList)

        adapter.onItemClicked = { group, _ ->
            setNavigationResult(SELECTED_GROUP_ARG, group)
            popBackStack()
        }
        adapter.onItemLongClicked = { group, view, position ->
            PopupMenu(requireContext(), view, Gravity.END).apply {
                menuInflater.inflate(R.menu.group_menu, this.menu)
                setOnMenuItemClickListener {
                    when (it.itemId) {
                        R.id.menuGroupEdit -> viewModel.onEditClicked(group, position)
                        R.id.menuGroupDelete -> viewModel.onDeleteClicked(group)
                    }
                    true
                }
                show()
            }
        }
    }
}