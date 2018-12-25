package com.imagegallery.sample

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.imagegallery.library.GalleryBuilder
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun goTo(view: View) {
        val array = ArrayList<String>().apply {
            addAll(Arrays.asList(
                    "https://images.pexels.com/photos/248797/pexels-photo-248797.jpeg",
                    "https://images.pexels.com/photos/460823/pexels-photo-460823.jpeg"
            ))
        }
        GalleryBuilder.build(this)
                .from(array)
                .show()
    }
}
