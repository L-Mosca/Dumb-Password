package br.com.moscatech.dumbpassword.ui.custom_view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import br.com.moscatech.dumbpassword.R
import br.com.moscatech.dumbpassword.databinding.BaseAppBarBinding

class BaseAppBar @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : FrameLayout(context, attrs, defStyleAttr) {

    private val binding: BaseAppBarBinding =
        BaseAppBarBinding.inflate(LayoutInflater.from(context), this, true)

    private var onBackClickListener: (() -> Unit)? = null

    init {
        context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.BaseAppBar,
            0,
            0,
        ).apply {
            try {
                val title = getString(R.styleable.BaseAppBar_title)
                setTitleText(title)
            } finally {
                recycle()
            }
        }
        setupListeners()
    }

    private fun setupListeners() {
        binding.vBack.setOnClickListener {
            onBackClickListener?.invoke()
        }
    }

    fun setOnBackClickListener(listener: () -> Unit) {
        onBackClickListener = listener
    }

    fun setTitleText(title: String?) {
        binding.tvAppBar.text = title ?: ""
    }

    var title: String?
        get() = binding.tvAppBar.text.toString()
        set(value) = setTitleText(value)
}