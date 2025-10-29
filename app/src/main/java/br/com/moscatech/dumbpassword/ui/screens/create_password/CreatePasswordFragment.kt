package br.com.moscatech.dumbpassword.ui.screens.create_password

import android.view.LayoutInflater
import androidx.fragment.app.viewModels
import androidx.navigation.NavOptions
import br.com.moscatech.dumbpassword.R
import br.com.moscatech.dumbpassword.base.BaseFragment
import br.com.moscatech.dumbpassword.databinding.FragmentCreatePasswordBinding
import br.com.moscatech.dumbpassword.utils.navigate
import br.com.moscatech.dumbpassword.utils.popBackStack
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CreatePasswordFragment : BaseFragment<FragmentCreatePasswordBinding>() {

    override val bindingInflater: (LayoutInflater) -> FragmentCreatePasswordBinding
        get() = FragmentCreatePasswordBinding::inflate

    override val viewModel: CreatePasswordViewModel by viewModels()

    override fun initViews() {
        binding.apply {
            createPasswordAppBar.setOnBackClickListener { popBackStack() }

            dcvGroup.setOnClickListener {
                val navOptions = NavOptions.Builder()
                    .setEnterAnim(R.anim.slide_in_right)
                    .setExitAnim(R.anim.slide_out_left)
                    .setPopEnterAnim(R.anim.slide_in_left)
                    .setPopExitAnim(R.anim.slide_out_right)
                    .build()

                val direction =
                    CreatePasswordFragmentDirections.actionCreatePasswordFragmentToGroupNavGraph()
                navigate(direction, navOptions)
            }

            dcvPlatform.setOnClickListener { }
        }
    }

    override fun initObservers() {}
}