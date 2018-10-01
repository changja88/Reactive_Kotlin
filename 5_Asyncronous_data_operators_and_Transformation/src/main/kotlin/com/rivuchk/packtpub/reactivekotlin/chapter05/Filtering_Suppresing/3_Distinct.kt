package com.rivuchk.packtpub.reactivekotlin.chapter05.Filtering_Suppresing

import io.reactivex.rxkotlin.toObservable

fun main(args: Array<String>) {
    listOf(1,2,2,3,4,5,5,5,6,7,8,9,3,10,2)//(1)
            .toObservable()//(2)
            .distinct()//(3)
            .subscribe { println("Received $it") }//(4)
}

// * 정리
// - The distinct operators - distinct, distinctUntilChanged
//      - distinct -> 똑같은건 emit해도 처리하지 않는다