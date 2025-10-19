package br.com.moscatech.dumbpassword.ui.screens.splash

import android.view.LayoutInflater
import androidx.fragment.app.viewModels
import br.com.moscatech.dumbpassword.base.BaseFragment
import br.com.moscatech.dumbpassword.databinding.FragmentSplashBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashFragment : BaseFragment<FragmentSplashBinding>() {

    override val bindingInflater: (LayoutInflater) -> FragmentSplashBinding
        get() = FragmentSplashBinding::inflate

    override val viewModel: SplashViewModel by viewModels()

    override fun initViews() {
    }

    override fun initObservers() {
    }
}