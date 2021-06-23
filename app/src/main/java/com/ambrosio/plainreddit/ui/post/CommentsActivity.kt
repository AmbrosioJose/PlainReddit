package com.ambrosio.plainreddit.ui.post

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.ambrosio.plainreddit.MainActivity
import com.ambrosio.plainreddit.R

class CommentsActivity : AppCompatActivity() {
    lateinit var postId: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.comments_activity)

        intent?.getStringExtra(MainActivity.POST_INTENT).run{
            postId = this?:""
            createFragment(CommentsFragment.newInstance(postId))
        }
    }

    private fun createFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, CommentsFragment.newInstance(postId))
            .commitNow()
    }
}