package com.rivuchk.packtpub.reactivekotlin.chapter06.Error_Handling

import io.reactivex.Observable

fun main(args: Array<String>) {
    Observable.just(1, 2, 3, 4, 5)
            .map { it / (3 - it) }
            .onErrorResumeNext(Observable.range(10, 5))//(1)
            .subscribe {
                println("Received $it")
            }
}

// * 정리
// - onErrorResumeNext
//      - error가 발생하면 파라미터로 받은 것을 시작한다