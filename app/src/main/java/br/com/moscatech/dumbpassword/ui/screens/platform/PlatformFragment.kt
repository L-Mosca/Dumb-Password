package br.com.moscatech.dumbpassword.ui.screens.platform

import android.view.Gravity
import android.view.LayoutInflater
import androidx.appcompat.widget.PopupMenu
import androidx.fragment.app.viewModels
import br.com.moscatech.dumbpassword.R
import br.com.moscatech.dumbpassword.base.BaseFragment
import br.com.moscatech.dumbpassword.databinding.FragmentPlatformBinding
import br.com.moscatech.dumbpassword.ui.adapters.GroupAdapter
import br.com.moscatech.dumbpassword.ui.screens.new_platform.NewPlatformDialog
import br.com.moscatech.dumbpassword.utils.navigate
import br.com.moscatech.dumbpassword.utils.popBackStack
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PlatformFragment : BaseFragment<FragmentPlatformBinding>() {

    companion object {
        const val SELECTED_PLATFORM_ARG = "GroupFragment.SelectedPlatformArg"
    }

    override val bindingInflater: (LayoutInflater) -> FragmentPlatformBinding
        get() = FragmentPlatformBinding::inflate

    override val viewModel: PlatformViewModel by viewModels()

    private val adapter by lazy { GroupAdapter() }

    override fun initViews() {
        binding.apply {
            platformAppBar.setOnBackClickListener { popBackStack() }

            btNewPlatform.setOnClickListener {
                val direction = PlatformFragmentDirections.actionPlatformFragmentToNewPlatformDialog()
                navigate(direction)
            }
        }
    }

    override fun initObservers() {
        viewModel.platformList.observe(this) { platformList ->
            setupAdapter(platformList)
        }

        viewModel.showEditDialog.observe(viewLifecycleOwner) { (_, platform) ->
            val direction = PlatformFragmentDirections.actionPlatformFragmentToNewPlatformDialog(platform)
            navigate(direction)
        }

        // Called when user press 'send' on keyboard in 'NewPlatformDialog'
        getNavigationResult<String>(NewPlatformDialog.NEW_PLATFORM_ARG)?.observe(viewLifecycleOwner) {
            viewModel.getPlatforms()
        }
    }

    private fun setupAdapter(platformList: List<String>) {
        binding.rvPlatforms.adapter = adapter
        adapter.submitList(platformList)

        adapter.onItemClicked = { platform, _ ->
            setNavigationResult(SELECTED_PLATFORM_ARG, platform)
            popBackStack()
        }
        adapter.onItemLongClicked = { platform, view, position ->
            PopupMenu(requireContext(), view, Gravity.END).apply {
                menuInflater.inflate(R.menu.group_menu, this.menu)
                setOnMenuItemClickListener {
                    when (it.itemId) {
                        R.id.menuGroupEdit -> viewModel.onEditClicked(platform, position)
                        R.id.menuGroupDelete -> viewModel.onDeleteClicked(platform)
                    }
                    true
                }
                show()
            }
        }
    }
}