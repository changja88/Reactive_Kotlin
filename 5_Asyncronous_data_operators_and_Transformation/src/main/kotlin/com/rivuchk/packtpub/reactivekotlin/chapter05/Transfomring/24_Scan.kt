package com.rivuchk.packtpub.reactivekotlin.chapter05.Transfomring

import io.reactivex.Observable
import io.reactivex.rxkotlin.toObservable

fun main(args: Array<String>) {
    Observable.range(1, 10)
            .scan { previousAccumulation, newEmission -> previousAccumulation + newEmission }//(1)
            .subscribe { println("Received $it") }

    listOf("String 1", "String 2", "String 3", "String 4")
            .toObservable()
            .scan { previousAccumulation, newEmission -> previousAccumulation + " " + newEmission }//(2)
            .subscribe { println("Received $it") }

    Observable.range(1, 5)
            .scan { previousAccumulation, newEmission -> previousAccumulation * 10 + newEmission }//(3)
            .subscribe { println("Received $it") }

}


// * 정리
// - Scan
//      - rolling aggregator
//      - 이전에 배출 했던 것들과 상관을 지어 emit 하게된다
