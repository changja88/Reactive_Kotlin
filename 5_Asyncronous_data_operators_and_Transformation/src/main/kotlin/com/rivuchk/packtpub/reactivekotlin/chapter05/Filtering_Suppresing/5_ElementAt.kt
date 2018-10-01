package com.rivuchk.packtpub.reactivekotlin.chapter05.Filtering_Suppresing

import io.reactivex.rxkotlin.toObservable

fun main(args: Array<String>) {
    val observable = listOf(10,1,2,5,8,6,9)
            .toObservable()

    observable.elementAt(5)//(1)
            .subscribe { println("Received $it") }

    observable.elementAt(50)//(2)
            .subscribe { println("Received $it") }
}

// * 정리
// - ElementAt
//      - n번째 아이템만 emit한다
//      - n번째가 없으면 emit하지 않는다