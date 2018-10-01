package com.rivuchk.packtpub.reactivekotlin.chapter03

import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import kotlinx.coroutines.experimental.delay
import kotlinx.coroutines.experimental.runBlocking
import java.util.concurrent.TimeUnit

/**
 * Created by rivuc on 22-07-2017.
 */

fun main(args: Array<String>) {
    runBlocking {
        val observale:Observable<Long> = Observable.interval(100,TimeUnit.MILLISECONDS)//1
        val observer:Observer<Long> = object : Observer<Long> {

            lateinit var disposable:Disposable//2

            override fun onSubscribe(d: Disposable) {
                disposable = d//3
            }

            override fun onNext(item: Long) {
                println("Received $item")
                if(item>=10 && !disposable.isDisposed) {//4
                    disposable.dispose()//5
                    println("Disposed")
                }
            }

            override fun onError(e: Throwable) {
                println("Error ${e.message}")
            }

            override fun onComplete() {
                println("Complete")
            }

        }

        observale.subscribe(observer)
        delay(1500)//6
    }
}

// * 정리
// - Subscribing and disposing
//      - Disposable 객체를 얻는 방법
//      - 6_SubscribeExpl에 있는 방법1로 observable을 만들면 subscribe operator가 Disposable을 리턴한다 (방법1)
//      - Observer에게 Observable 객체를 전달해서 만들었다면(6_SubscribeExpl에 있는 방법1), onSubscribe 에서
//        Disposable 객체를 얻을 수 있따(방법2)