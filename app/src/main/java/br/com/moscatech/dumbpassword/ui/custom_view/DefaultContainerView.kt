package br.com.moscatech.dumbpassword.ui.custom_view

import android.animation.ArgbEvaluator
import android.animation.ValueAnimator
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.FrameLayout
import androidx.core.view.isVisible
import br.com.moscatech.dumbpassword.R
import br.com.moscatech.dumbpassword.databinding.DefaultContainerBinding
import com.google.android.material.color.MaterialColors

class DefaultContainerView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : FrameLayout(context, attrs, defStyleAttr) {

    private val binding: DefaultContainerBinding =
        DefaultContainerBinding.inflate(
            LayoutInflater.from(context), this, true,
        )

    private var previousShowError: Boolean = false

    private var isAnimating = false

    init {
        context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.DefaultContainerView,
            0,
            0,
        ).apply {
            try {
                val title = getString(R.styleable.DefaultContainerView_titleText)
                val content = getString(R.styleable.DefaultContainerView_contentText)
                val error = getString(R.styleable.DefaultContainerView_errorText)
                val showError = getBoolean(R.styleable.DefaultContainerView_showError, false)

                setTitle(title)
                setContent(content)
                setError(error, showError)
            } finally {
                recycle()
            }
        }
    }

    override fun setOnClickListener(l: OnClickListener?) {
        val pulse = AnimationUtils.loadAnimation(context, R.anim.anim_pulse)

        binding.cvDefaultContainer.setOnClickListener { view ->
            if (isAnimating) return@setOnClickListener
            isAnimating = true

            view.startAnimation(pulse.apply {
                setAnimationListener(object : Animation.AnimationListener {
                    override fun onAnimationEnd(p0: Animation?) {
                        view.post {
                            l?.onClick(view)
                            isAnimating = false
                        }
                    }

                    override fun onAnimationRepeat(p0: Animation?) {}
                    override fun onAnimationStart(p0: Animation?) {}
                })
            })
        }
    }

    var titleText: String?
        get() = binding.tvTitle.text.toString()
        set(value) = setTitle(value)

    var contentText: String?
        get() = binding.tvContent.text.toString()
        set(value) = setContent(value)

    var showError: Boolean
        get() = previousShowError
        set(value) = setError(binding.tvErrorMessage.text.toString(), value)

    fun setTitle(text: String?) {
        binding.tvTitle.text = text
    }

    fun setContent(text: String?) {
        binding.tvContent.text = text
    }

    fun setError(text: String?, showError: Boolean) {
        binding.cvDefaultContainer.strokeWidth = if (showError) 2 else 0

        val strokeColor = if (showError)
            MaterialColors.getColor(context, R.attr.errorColor, 0)
        else MaterialColors.getColor(context, R.attr.cardColor, 0)

        val currentColor = binding.cvDefaultContainer.strokeColorStateList?.defaultColor
        val colorAnimator = ValueAnimator.ofObject(ArgbEvaluator(), currentColor, strokeColor)
        colorAnimator.duration = 300
        colorAnimator.addUpdateListener { animator ->
            binding.cvDefaultContainer.strokeColor = animator.animatedValue as Int
        }
        colorAnimator.start()

        binding.tvErrorMessage.isVisible = showError
        binding.tvErrorMessage.text = text ?: ""

        previousShowError = showError
    }
}