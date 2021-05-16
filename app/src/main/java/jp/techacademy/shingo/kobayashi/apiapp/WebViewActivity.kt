package jp.techacademy.shingo.kobayashi.apiapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_web_view.*

class WebViewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view)

        val locate = intent.getStringExtra("key_url").toString()
        webView.loadUrl(locate)

        if(FavoriteShop.findByURL(
                locate
            ) == null) {
            toggleButton.text = "お気に入りに追加する☆"
        }else {
            toggleButton.text = "お気に入りを解除する★"
        }

        toggleButton.setOnClickListener {
            if (FavoriteShop.findByURL(
                    locate
                ) == null) {

                var historyId = historyOfShop.findByURL(
                    locate
                )!!.id
                var historyName = historyOfShop.findByURL(
                    locate
                )!!.name
                var historyImageUrl = historyOfShop.findByURL(
                    locate
                )!!.imageUrl
                var historyUrl = historyOfShop.findByURL(
                    locate
                )!!.url

                toggleButton.text = "お気に入りを解除する★"

                FavoriteShop.insert(
                    FavoriteShop().apply {
                        id = historyId
                        name = historyName
                        imageUrl = historyImageUrl
                        url = historyUrl
                    })
            } else {
                var historyId = historyOfShop.findByURL(
                    locate
                )!!.id
                FavoriteShop.delete(
                    historyId
                )
                toggleButton.text = "お気に入りに追加する☆"
            }
        }
    }
}