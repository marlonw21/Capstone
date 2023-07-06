package com.mwdevs.capstone.utils.animation

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import android.view.View
import com.mwdevs.capstone.R

inline fun View.clickListenerWithAnimation(
    defaultColor: Int,
    onClickColor: Int,
    crossinline cb: (View) -> Unit
) {
    this.setOnClickListener {
        val animator = ObjectAnimator.ofArgb(
            this,
            context.getString(R.string.background_color_property),
            context.getColor(defaultColor),
            context.getColor(onClickColor)
        )
        animator.duration = 200
        animator.repeatCount = 1
        animator.repeatMode = ObjectAnimator.REVERSE
        animator.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationStart(animation: Animator?) {
                this@clickListenerWithAnimation.isClickable = false
            }
            override fun onAnimationEnd(animation: Animator?, isReverse: Boolean) {
                this@clickListenerWithAnimation.isClickable = true
            }
        })
        animator.start()
        cb(this)
    }
}
