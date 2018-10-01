package com.rivuchk.packtpub.reactivekotlin.chapter04

import io.reactivex.Flowable
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.experimental.delay
import kotlinx.coroutines.experimental.runBlocking
import org.reactivestreams.Subscriber
import org.reactivestreams.Subscription


fun main(args: Array<String>) {
    Flowable.range(1, 1000)//(1)
            .map { MyItem5(it) }//(2)
            .observeOn(Schedulers.io())
            .subscribe(object : Subscriber<MyItem5> {//(3)
                override fun onSubscribe(subscription: Subscription) {
                    subscription.request(Long.MAX_VALUE)//(4) request for the number of emissions
                }

                override fun onNext(s: MyItem5?) {
                    runBlocking { delay(50) }
                    println("Subscriber received " + s!!)
                }

                override fun onError(e: Throwable) {
                    e.printStackTrace()
                }

                override fun onComplete() {
                    println("Done!")
                }
            })
    runBlocking { delay(60000) }
}

data class MyItem5 (val id:Int) {
    init {
        println("MyItem Created $id")
    }
}

// * 정리
// - Flowable and Subscriber
//      - Flowable은 observer 대신에 subscriber(backpressure compatible)를 사용한다
//      - Lamda를 사용하면 oberserver나 subscribe나 사용하는데 코드상 크게 달라지는 점은 없다
//      - Subscriber를 사용하면 must specify how many items you want to receive from upstream
//

