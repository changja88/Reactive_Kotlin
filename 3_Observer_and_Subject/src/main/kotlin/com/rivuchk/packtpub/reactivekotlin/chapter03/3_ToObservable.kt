package com.rivuchk.packtpub.reactivekotlin.chapter03

import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.toObservable


/**
 * Created by rivuc on 22-07-2017.
 */

fun main(args: Array<String>) {

    val observer: Observer<String> = object : Observer<String> {
        override fun onComplete() {
            println("All Completed")
        }

        override fun onNext(item: String) {
            println("Next $item")
        }

        override fun onError(e: Throwable) {
            println("Error Occured ${e.message}")
        }

        override fun onSubscribe(d: Disposable) {
            println("New Subscription ")
        }
    }//Create Observer

    val list:List<String> = listOf("String 1","String 2","String 3","String 4")

    val observable:Observable<String> = list.toObservable()

    observable.subscribe(observer)
}

// * 정리
// - Understanding the toObservable extension function
//      - toObservable 메소드를 통해서 observable로 변환이 쉽게 가능하다