package br.com.moscatech.dumbpassword.ui.screens.splash

import android.view.LayoutInflater
import androidx.fragment.app.viewModels
import androidx.navigation.NavOptions
import br.com.moscatech.dumbpassword.R
import br.com.moscatech.dumbpassword.base.BaseFragment
import br.com.moscatech.dumbpassword.databinding.FragmentSplashBinding
import br.com.moscatech.dumbpassword.utils.navigate
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashFragment : BaseFragment<FragmentSplashBinding>() {

    override val bindingInflater: (LayoutInflater) -> FragmentSplashBinding
        get() = FragmentSplashBinding::inflate

    override val viewModel: SplashViewModel by viewModels()

    override fun initViews() {}

    override fun initObservers() {
        viewModel.showHome.observe(this) {
            val navOptions = NavOptions.Builder()
                .setPopUpTo(R.id.splashFragment, inclusive = true)
                .setEnterAnim(R.anim.slide_in_right)
                .setExitAnim(R.anim.slide_out_left)
                .setPopEnterAnim(R.anim.slide_in_left)
                .setPopExitAnim(R.anim.slide_out_right)
                .build()

            val direction = SplashFragmentDirections.actionSplashFragmentToHomeFragment()

            navigate(direction, navOptions)
        }
    }
}