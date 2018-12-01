package com.imagegallery.library

import android.content.Context
import android.content.Intent
import android.os.Bundle
import java.util.*

/**
 * Created by Josh on 01,Dec,2018
 */
const val INTENT_EXTRA_TITLE = "title"
const val INTENT_EXTRA_SUB_TITLE = "sub_title"
const val INTENT_EXTRA_LIST = "list"
const val INTENT_EXTRA_LOAD_POSITION = "position"

class GalleryBuilder private constructor(private val context: Context) {

    companion object {
        @JvmStatic
        fun build(context: Context): GalleryBuilder {
            return GalleryBuilder(context)
        }
    }

    private val mExtras: Bundle = Bundle()

    fun withTitle(title: String): GalleryBuilder {
        mExtras.putString(INTENT_EXTRA_TITLE, title)
        return this
    }

    fun withSubTitle(subTitle: String): GalleryBuilder {
        mExtras.putString(INTENT_EXTRA_SUB_TITLE, subTitle)
        return this
    }

    fun from(list: ArrayList<String>): GalleryBuilder {
        mExtras.putStringArrayList(INTENT_EXTRA_LIST, list)
        return this
    }

    fun position(pos: Int): GalleryBuilder {
        mExtras.putInt(INTENT_EXTRA_LOAD_POSITION, pos)
        return this
    }

    fun show() {
        val i = Intent(context, GalleryActivity::class.java).apply { putExtras(mExtras) }
        context.startActivity(i)
    }

}


