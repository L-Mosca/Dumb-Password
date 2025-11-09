package br.com.moscatech.dumbpassword.base

import android.graphics.Color
import android.graphics.drawable.InsetDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.MutableLiveData
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding
import androidx.core.graphics.drawable.toDrawable

abstract class BaseDialogFragment<VB: ViewBinding>: DialogFragment() {

    abstract val bindingInflater: (LayoutInflater) -> VB
    private var viewBinding: VB? = null

    abstract val viewModel: BaseViewModel

    open val windowHeight: Int = WindowManager.LayoutParams.WRAP_CONTENT

    val binding: VB
        get() = viewBinding as VB

    abstract fun initViews()

    abstract fun initObservers()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewBinding = bindingInflater.invoke(layoutInflater)
        return binding.root
    }

    override fun onStart() {
        super.onStart()

        dialog?.window?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            windowHeight
        )

        val back = Color.TRANSPARENT.toDrawable()
        val inset = InsetDrawable(back, 50)
        dialog?.window?.setBackgroundDrawable(inset)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObservers()
        initViews()
    }

    protected fun <T> setNavigationResult(key: String, result: T) {
        findNavController().previousBackStackEntry?.savedStateHandle?.set(
            key,
            result
        )
    }

    protected fun <T> getNavigationResult(key: String): MutableLiveData<T>? {
        return findNavController().currentBackStackEntry?.savedStateHandle?.getLiveData(key)
    }
}