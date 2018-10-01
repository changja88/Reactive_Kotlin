package com.rivuchk.packtpub.reactivekotlin.chapter05.Filtering_Suppresing

import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.disposables.Disposable

fun main(args: Array<String>) {
    val observable = Observable.range(1,20)
    observable
            .takeLast(5)//(1)
            .subscribe(object: Observer<Int> {
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
                    println("starting skipLast(count)")
                }

            })
}

// * 정리
// - takeLast
//      - 뒤에서 부터 몇개를 받을 건지 파라미터로 받는다
//      - skipLast의 경우는 뒤에서 부터 몇개를 건너 뛸건지를 받느다
