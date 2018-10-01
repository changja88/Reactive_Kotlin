package com.rivuchk.packtpub.reactivekotlin.chapter04

import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.disposables.Disposable

fun main(args: Array<String>) {
    val observer: Observer<Int> = object : Observer<Int> {
        override fun onComplete() {
            println("All Completed")
        }

        override fun onNext(item: Int) {
            println("Next $item")
        }

        override fun onError(e: Throwable) {
            println("Error Occured ${e.message}")
        }

        override fun onSubscribe(d: Disposable) {
            println("New Subscription ")
        }
    }//Create Observer

    val observable: Observable<Int> = Observable.create<Int> {//1
        // 이렇게 만들면 자유도가 높지만 backpressure를 지원하지 못한다
        for(i in 1..10) {
            it.onNext(i)
        }
        it.onComplete()
    }

    observable.subscribe(observer)

}

// * 정리
// - Flowable 미적용
// - Creating Flowable from scratch
//