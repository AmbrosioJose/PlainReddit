package com.ambrosio.plainreddit

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.ambrosio.plainreddit.model.Post
import com.ambrosio.plainreddit.ui.main.MainFragment
import com.ambrosio.plainreddit.ui.post.CommentsActivity

class MainActivity : AppCompatActivity(), MainFragment.TouchActionDelegate{

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.container, MainFragment.newInstance())
                    .commitNow()
        }
    }

    private fun launchPostDetailActivity(post: Post){
        val intent = Intent(this, CommentsActivity::class.java)
        var postId: String = ""
        intent.apply {
            if(post.name!= null && post.name.contains("_")){
                postId = post.name.split("_")[1]
                this.putExtra(POST_INTENT, postId)
            }
        }
        if(!postId.isEmpty())
            startActivity(intent)
    }

    private fun replaceFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction()
                .replace(R.id.container, fragment)
                .commit()
    }

    override fun onPostTap(post: Post) {
        launchPostDetailActivity(post)
    }

    companion object{
        const val POST_INTENT = "post_id"
    }

}