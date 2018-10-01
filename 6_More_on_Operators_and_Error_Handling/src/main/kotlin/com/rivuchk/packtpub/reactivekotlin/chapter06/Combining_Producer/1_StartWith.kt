package com.rivuchk.packtpub.reactivekotlin.chapter06.Combining_Producer

import io.reactivex.Observable

fun main(args: Array<String>) {
    println("startWith Iterator")
    Observable.range(5,10)
            .startWith(listOf(1,2,3,4))//(1)
            .subscribe {
                println("Received $it")
            }
    println("startWith another source Producer")
    Observable.range(5,10)
            .startWith(Observable.just(1,2,3,4))//(2)
            .subscribe {
                println("Received $it")
            }
}


// * 정리
// - Combining producer(Observable/Flowable)
//      - startWith
//      - merge, mergeDelayError
//      - concat
//      - zip
//      - combineLatest
//
//      - Combine producer mechanism
//          - Merging producers
//          - Concatenating producers
//          - Ambiguous combination of producers
//          - Zipping
//          - Combine latest

// - startWith
//      - startWith 의 파라미터 observable부터 시작한다
//      - 파라미터로 들어온 것을 자동으로 observable로 바꿔준다 (Flowable을 사용하고 있다면 flowable로 바꿔준다)
//      -