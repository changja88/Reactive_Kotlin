package com.rivuchk.packtpub.reactivekotlin.chapter04

import io.reactivex.Flowable
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.experimental.delay
import kotlinx.coroutines.experimental.runBlocking
import org.reactivestreams.Subscriber
import org.reactivestreams.Subscription


fun main(args: Array<String>) {
    Flowable.range(1, 15)
            .map { MyItem6(it) }
            .observeOn(Schedulers.io())
            .subscribe(object : Subscriber<MyItem6> {
                lateinit var subscription: Subscription//(1)
                override fun onSubscribe(subscription: Subscription) {
                    this.subscription = subscription
                    subscription.request(5)//(2)
                }

                override fun onNext(s: MyItem6?) {
                    runBlocking { delay(50) }
                    println("Subscriber received " + s!!)
                    if(s.id == 5) {//(3)
                        println("Requesting two more")
                        subscription.request(2)//(4)
                    }
                }

                override fun onError(e: Throwable) {
                    e.printStackTrace()
                }

                override fun onComplete() {
                    println("Done!")
                }
            })
    runBlocking { delay(10000) }
}

data class MyItem6 (val id:Int) {
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

// - 포인트
//      - Observable, Flowable 은 emit을 어떻게 할건지에 관한 것
//      - Observer, Subscriber 는 emit되는 item을 어떻게 받을지에 관한 것
//      - 따라서 이예제에서 subscriber가 5개 받는다고 해도 emit은 15개를 한다