package com.pisey.cleanarchitecturewithgraphql.presentation.components

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver
import android.widget.FrameLayout
import androidx.annotation.LayoutRes
import androidx.core.view.isVisible
import com.pisey.cleanarchitecturewithgraphql.R
import com.pisey.cleanarchitecturewithgraphql.databinding.LayoutEmptyStateBinding
import com.pisey.cleanarchitecturewithgraphql.databinding.LayoutFailedStateBinding
import com.pisey.cleanarchitecturewithgraphql.databinding.LayoutLoadingStateBinding
import com.pisey.cleanarchitecturewithgraphql.databinding.LayoutNoInternetStateBinding
import com.pisey.cleanarchitecturewithgraphql.databinding.LayoutSomethingWentWrongStateBinding
import com.pisey.cleanarchitecturewithgraphql.databinding.LayoutTimeoutStateBinding

class CustomStateView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : FrameLayout(context, attrs, defStyleAttr) {
    private var state: State = State.LOADING
    private lateinit var contentLayout: View
    private lateinit var currentTag: String
    private lateinit var currentLayout: View

    private val loadingStateBinding = LayoutLoadingStateBinding.inflate(LayoutInflater.from(context), this, false)
    private val emptyStateBinding = LayoutEmptyStateBinding.inflate(LayoutInflater.from(context), this, false)
    private val noInternetStateBinding = LayoutNoInternetStateBinding.inflate(LayoutInflater.from(context), this, false)
    private val failedStateBinding = LayoutFailedStateBinding.inflate(LayoutInflater.from(context), this, false)
    private val timeoutStateBinding = LayoutTimeoutStateBinding.inflate(LayoutInflater.from(context), this, false)
    private val somethingWentWrongStateBinding = LayoutSomethingWentWrongStateBinding.inflate(LayoutInflater.from(context), this, false)

    @LayoutRes
    private var loadingLayoutRes: Int = -1
    private lateinit var loadingView: View

    @LayoutRes
    private var emptyLayoutRes: Int = -1
    private lateinit var emptyView: View

    private val duration = 50L

    var onLoad: (() -> Unit)? = null

    init {
        context.theme.obtainStyledAttributes(attrs, R.styleable.CustomStateView, 0, 0)
            .apply {
                try {
                    state = State.values()[getInteger(R.styleable.CustomStateView_state, State.LOADING.ordinal)]
                    loadingLayoutRes = getResourceId(R.styleable.CustomStateView_loadingLayout, -1)
                    emptyLayoutRes = getResourceId(R.styleable.CustomStateView_emptyLayout, -1)
                } finally {
                    recycle()
                }
            }
        viewTreeObserver.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                onLoad?.invoke()
                viewTreeObserver.removeOnGlobalLayoutListener(this)
            }
        })
        if (isInEditMode) {
            state = State.SUCCESS
        }
    }

    private fun updateStateToLoading() {
        state = State.LOADING
        if (loadingLayoutRes.isHaveLayout()) {
            val view = inflate(loadingLayoutRes, this)
            showLayout(view) {
                onLoad?.invoke()
            }
        } else {
            showLayout(loadingStateBinding.root) {
                onLoad?.invoke()
            }
        }
    }

    private fun hideAll() {
        loadingView.isVisible = false
        contentLayout.isVisible = false
        failedStateBinding.root.isVisible = false
        emptyView.isVisible = false
        noInternetStateBinding.root.isVisible = false
        timeoutStateBinding.root.isVisible = false
        somethingWentWrongStateBinding.root.isVisible = false
    }


    override fun onFinishInflate() {
        super.onFinishInflate()
        contentLayout = getChildAt(0)
        contentLayout.tag = "Success"
        loadingView = if (loadingLayoutRes.isHaveLayout()) inflate(loadingLayoutRes, this) else loadingStateBinding.root
        loadingView.tag = "loading"
        emptyView = if (emptyLayoutRes.isHaveLayout()) inflate(emptyLayoutRes, this) else emptyStateBinding.root
        emptyView.tag = "empty"
        failedStateBinding.root.tag = "failed"
        noInternetStateBinding.root.tag = "no internet"
        somethingWentWrongStateBinding.root.tag = "something"

        hideAll()
        addView(loadingView)
        addView(emptyView)
        addView(failedStateBinding.root)
        addView(noInternetStateBinding.root)
        addView(somethingWentWrongStateBinding.root)
        when (state) {
            State.LOADING -> {
                loadingView.isVisible = true
                currentLayout = loadingView
            }

            State.SUCCESS -> {
                contentLayout.isVisible = true
                currentLayout = contentLayout
            }

            State.FAILED -> {
                failedStateBinding.root.isVisible = true
                currentLayout = failedStateBinding.root
            }

            State.EMPTY -> {
                emptyView.isVisible = true
                currentLayout = emptyView
            }

            State.NO_INTERNET -> {
                noInternetStateBinding.root.isVisible = true
                currentLayout = noInternetStateBinding.root
            }

            State.TIMEOUT -> {
                timeoutStateBinding.root.isVisible = true
                currentLayout = timeoutStateBinding.root
            }

            State.SOMETHING_WENT_WRONG -> {
                somethingWentWrongStateBinding.root.isVisible = true
                currentLayout = somethingWentWrongStateBinding.root
            }
        }
        currentTag = currentLayout.tag as String
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        setupEventClick()
    }

    private fun showLayout(view: View, commitCallback: Runnable? = null) {
        if (currentTag == view.tag) {
            return
        }
        currentTag = view.tag as String
        val animatorSetOut = AnimatorSet()
        animatorSetOut.playTogether(
            ObjectAnimator.ofFloat(currentLayout, "scaleX", 1f, 0.5f),
            ObjectAnimator.ofFloat(currentLayout, "scaleY", 1f, 0.5f),
            ObjectAnimator.ofFloat(currentLayout, "alpha", 1f, 0f),
        )
        animatorSetOut.duration = duration
        animatorSetOut.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator) {
                super.onAnimationEnd(animation)
                hideAll()
                view.alpha = 0f
                view.isVisible = true
                currentLayout = view
                val animatorSetIn = AnimatorSet()
                animatorSetIn.playTogether(
                    ObjectAnimator.ofFloat(view, "scaleX", 0.5f, 1f),
                    ObjectAnimator.ofFloat(view, "scaleY", 0.5f, 1f),
                    ObjectAnimator.ofFloat(view, "alpha", 0f, 1f)
                )
                animatorSetIn.duration = duration
                animatorSetIn.addListener(object : AnimatorListenerAdapter() {
                    override fun onAnimationEnd(animation: Animator) {
                        super.onAnimationEnd(animation)
                        commitCallback?.run()
                    }
                })
                animatorSetIn.start()
            }
        })
        animatorSetOut.start()
    }

    private fun setupEventClick() {
        emptyStateBinding.btnRetry.setOnClickListener {
            updateStateToLoading()
        }
        noInternetStateBinding.btnRetry.setOnClickListener {
            updateStateToLoading()
        }
        failedStateBinding.btnRetry.setOnClickListener {
            updateStateToLoading()
        }
        timeoutStateBinding.btnRetry.setOnClickListener {
            updateStateToLoading()
        }
        somethingWentWrongStateBinding.btnRetry.setOnClickListener {
            updateStateToLoading()
        }

    }

    fun showSuccess() {
        showLayout(contentLayout)
    }

    fun showLoading() {
        showLayout(loadingView)
    }

    fun showTimeout() {
        showLayout(timeoutStateBinding.root)
    }

    fun showSomethingWentWrong() {
        showLayout(somethingWentWrongStateBinding.root)
    }

    fun showFailed() {
        showLayout(failedStateBinding.root)
    }

    fun showNoInternet() {
        showLayout(noInternetStateBinding.root)
    }

    fun showEmpty() {
        showLayout(emptyView)
    }


    fun View.inflate(@LayoutRes layoutId: Int, viewGroup: ViewGroup): View {
        return LayoutInflater.from(this.context).inflate(layoutId, viewGroup, false)
    }

    private fun Int.isHaveLayout(): Boolean {
        return this != -1
    }

    enum class State {
        LOADING, SUCCESS, EMPTY, FAILED, NO_INTERNET, TIMEOUT, SOMETHING_WENT_WRONG
    }

}

sealed class LayoutState<T> {
    class Loading<T> : LayoutState<T>()
    class Success<T>(val data: T) : LayoutState<T>()
    class Failed<T> : LayoutState<T>()
    class Empty<T> : LayoutState<T>()
    class NoInternet<T> : LayoutState<T>()
    class Timeout<T> : LayoutState<T>()
    class SomethingWentWrong<T> : LayoutState<T>()
}