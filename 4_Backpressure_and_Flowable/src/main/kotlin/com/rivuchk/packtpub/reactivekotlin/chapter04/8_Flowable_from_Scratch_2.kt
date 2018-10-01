package com.rivuchk.packtpub.reactivekotlin.chapter04

import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.experimental.delay
import kotlinx.coroutines.experimental.runBlocking
import org.reactivestreams.Subscriber
import org.reactivestreams.Subscription

fun main(args: Array<String>) {
    val subscriber: Subscriber<Int> = object : Subscriber<Int> {
        override fun onComplete() {
            println("All Completed")
        }

        override fun onNext(item: Int) {
            println("Next $item")
        }

        override fun onError(e: Throwable) {
            println("Error Occured ${e.message}")
        }

        override fun onSubscribe(subscription: Subscription) {
            println("New Subscription ")
            subscription.request(10)
        }
    }//(1)

    val flowable: Flowable<Int> = Flowable.create<Int> ({//1
        for(i in 1..10) {
            it.onNext(i)
        }
        it.onComplete()
    },BackpressureStrategy.BUFFER)//(2)

    flowable
            .observeOn(Schedulers.io())
            .subscribe(subscriber)//(3)

    runBlocking { delay(10000) }



}

// * 정리
// - Flowable 적용
// - Creating Flowable from scratch
//      - Flowable.create 의 첫번째 파라미터 -> source from where the emissions will generate
//      - 두번째 파라미터 -> BackpressureStrategy(enum, that helps supporting backpressure)
//          - BackpressureStrategy enum 종류
//              - BUFFER : buffers all the emissions until they are consumed by the downstream
//                         OOM를 발생 시킬수 있음
//              - MISSING : backpressure 지원 안함, downstream이 overflow처리해야함
//              - ERROR : backpressure 지원 안함, signals MissingbackpressureException the very moment
//                        the downstream cannot keep up with the source
//              - DROP : drop all the emissions while the downstream is busy and can't keep up
//                       계속 emitt되고 있다면 바쁜 동안에는 받지 못하고 안바빠지면 다시 받는다
//              - LATEST : 계속 드랍을 하는데 드랍되는 것들중 맨 마지막 꺼는 안바빠지면 다시 받는다
