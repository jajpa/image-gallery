package com.imagegallery.library

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.util.DisplayMetrics
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager.HORIZONTAL
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView

/**
 * Created by Josh on 01,Dec,2018
 */
private const val UI_ANIMATION_DELAY = 300
private const val UI_HIDE_ANIMATION_DELAY = 100

class GalleryActivity : AppCompatActivity(), GalleryAdapter.ImageTapInterface {

    private val mHideHandler = Handler()
    private var mVisible: Boolean = false
    private val mHideRunnable = Runnable { hide() }

    @SuppressLint("InlinedApi")
    private val mHidePart2Runnable = Runnable {
        window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_FULLSCREEN
                or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION)
        supportActionBar?.hide()
    }
    private val mShowPart2Runnable = Runnable {
        window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_LAYOUT_STABLE)
        supportActionBar?.show()
    }

    fun convertDpToPixel(context: Context, dp: Float): Int {
        val resources = context.resources
        val metrics = resources.displayMetrics
        return Math.round(dp * (metrics.densityDpi.toFloat() / DisplayMetrics.DENSITY_DEFAULT))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.statusBarColor = ContextCompat.getColor(this, R.color.transparent_black)
            window.navigationBarColor = ContextCompat.getColor(this, R.color.black_overlay)
        }

        val recyclerView = RecyclerView(this).apply {
            layoutParams = ViewGroup.LayoutParams(MATCH_PARENT, MATCH_PARENT)
            layoutManager =
                    LinearLayoutManager(this@GalleryActivity, HORIZONTAL, false)
        }
        PagerSnapHelper().attachToRecyclerView(recyclerView)
        setContentView(recyclerView)
        mVisible = false

        supportActionBar?.apply {
            setBackgroundDrawable(
                    ColorDrawable(
                            ContextCompat.getColor(this@GalleryActivity, R.color.black_overlay)
                    )
            )
            setHomeButtonEnabled(true)
            setDisplayHomeAsUpEnabled(true)
            title = intent.getStringExtra(INTENT_EXTRA_TITLE) ?: "Gallery"
            intent.getStringExtra(INTENT_EXTRA_SUB_TITLE)?.let { subtitle = it }
        }


        val imgUrls = intent.getStringArrayListExtra(INTENT_EXTRA_LIST)
        recyclerView.adapter = GalleryAdapter(this, imgUrls)
//        mHideHandler.postDelayed(mHideRunnable, UI_HIDE_ANIMATION_DELAY.toLong())
    }

    /*override fun onResume() {
        super.onResume()
        window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_LAYOUT_STABLE)
    }*/

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home)
            super.onBackPressed()
        return super.onOptionsItemSelected(item)
    }

    private fun toggle() {
        if (mVisible) {
            hide()
        } else {
            show()
        }
    }

    private fun hide() {
        mVisible = false
        mHideHandler.removeCallbacks(mShowPart2Runnable)
        mHideHandler.postDelayed(mHidePart2Runnable, UI_ANIMATION_DELAY.toLong())
    }

    @SuppressLint("InlinedApi")
    private fun show() {
        mVisible = true
        mHideHandler.removeCallbacks(mHidePart2Runnable)
        mHideHandler.postDelayed(mShowPart2Runnable, UI_ANIMATION_DELAY.toLong())
    }

    override fun onTap() {
        toggle()
    }

}