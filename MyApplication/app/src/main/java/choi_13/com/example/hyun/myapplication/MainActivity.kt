package choi_13.com.example.hyun.myapplication

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.activity_main.*
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        start()

    }

    fun start() {
        val observable = Observable.interval(1, TimeUnit.SECONDS)
        val observer = object : Observer<Long> {

            lateinit var disposable: Disposable

            override fun onComplete() {
            }

            override fun onSubscribe(d: Disposable) {
                disposable = d
            }

            override fun onNext(t: Long) {
                if (t == 2L) {
                    changeScreen1()
                }
                if (t == 4L) {
                    disposable.dispose()
                    changeScreen2()
                }
            }

            override fun onError(e: Throwable) {
            }
        }

        observable
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer)
    }

    fun changeScreen1() {
        img11.visibility = View.GONE
        img22.visibility = View.VISIBLE
    }

    fun changeScreen2() {
        img22.visibility = View.GONE
        img33.visibility = View.VISIBLE

        call.setOnClickListener {
            val number = "tel:01032381832"
            startActivity(Intent(Intent.ACTION_DIAL).apply {
                setData(Uri.parse(number))
            })
        }
        kakao.setOnClickListener {
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("http://qr.kakao.com/talk/K0Vv0xqq4jJOMtWJohvwX72M8I8-")))
        }

    }


}

