package com.rivuchk.packtpub.reactivekotlin.chapter05.Filtering_Suppresing

import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.toObservable

fun main(args: Array<String>) {
    val observable = Observable.range(1, 20)
    val observable2 = listOf(1, 2, 3, 4, 5, 1, 2, 3, 4, 5).toObservable()
    observable
            .skipWhile { item -> item < 2 }//(1)
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
// - SkipWhile
//      - it will keep skipping emissions while the predicate evaluates to true
//      - it will start passing al lemissions downstream as soon as the predicate returns false
//      - Filter와 다른점
//          - Filter는 매번 조건을 확인하고 조건을 만족하면 걸러내고 아니면 emit하지만
//          - skipWhile은 파라미터로 받은 조건이 한번 false가 되면 그다음부터는 그냥 무조건 emit하게 된다
//          - observable2 넣고 돌려보면 안다