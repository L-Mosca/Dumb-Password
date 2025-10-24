package br.com.moscatech.dumbpassword.ui.screens.home

import android.view.LayoutInflater
import androidx.fragment.app.viewModels
import br.com.moscatech.dumbpassword.base.BaseFragment
import br.com.moscatech.dumbpassword.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    override val bindingInflater: (LayoutInflater) -> FragmentHomeBinding
        get() = FragmentHomeBinding::inflate

    override val viewModel: HomeViewModel by viewModels()

    override fun initViews() {}

    override fun initObservers() {}
}