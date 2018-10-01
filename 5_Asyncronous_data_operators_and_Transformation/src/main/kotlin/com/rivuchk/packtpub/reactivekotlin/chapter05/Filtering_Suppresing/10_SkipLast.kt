package com.rivuchk.packtpub.reactivekotlin.chapter05.Filtering_Suppresing

import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.disposables.Disposable

fun main(args: Array<String>) {
    val observable = Observable.range(1,20)
    observable
            .skipLast(5)//(1)
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
// - SkipLast
//      - skip은 처음부터 걸러네는거고 skipLast는 뒤에서 부터 걸러낸다
//      -