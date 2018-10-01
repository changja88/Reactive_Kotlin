package com.rivuchk.packtpub.reactivekotlin.chapter05.Filtering_Suppresing

import io.reactivex.Observable

fun main(args: Array<String>) {
    val observable = Observable.range(1,10)
    observable
            .ignoreElements()
            .subscribe { println("Completed") }//(1)
}

// * 정리
// - ignoreElements
//      - 아무것도 emit하지 않는다
//      - onComplete만 필요할때 사용한다