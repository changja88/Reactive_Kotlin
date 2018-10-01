package com.rivuchk.packtpub.reactivekotlin.chapter05.Filtering_Suppresing

import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.toObservable

fun main(args: Array<String>) {
    val observable = Observable.range(1, 20)
    val observable2 = listOf(1, 2, 3, 4, 5, 1, 2, 3, 4, 5).toObservable()

    observable
            .takeWhile { item -> item < 10 }//(1)
            .subscribe(object : Observer<Int> {
                override fun onError(e: Throwable) {
                    println("Error $e")
                }

                override fun onComplete() {
                    println("Complete")
                }

                override fun onNext(t: Int) {
                    println("Received $t")
                }

                override fun onSubscribe(d: Disposable) {
                    println("starting skipWhile")
                }

            })
}

// * 정리
// - takeWhile
//      - 파라미터로 받은 조건이 true은 경우에만 emission을 처리하다가 false가 되는 순간 다 끝내고 onComplete를 호출한다