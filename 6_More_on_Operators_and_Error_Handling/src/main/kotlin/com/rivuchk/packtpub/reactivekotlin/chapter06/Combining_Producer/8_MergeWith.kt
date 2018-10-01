package com.rivuchk.packtpub.reactivekotlin.chapter06.Combining_Producer

import io.reactivex.rxkotlin.toObservable

fun main(args: Array<String>) {
    val observable1 = listOf("Kotlin", "Scala", "Groovy").toObservable()
    val observable2 = listOf("Python", "Java", "C++", "C").toObservable()

    observable1
            .mergeWith(observable2)
            .subscribe {
                println("Received $it")
            }
}

// * 정리
// - mergeWith
//      - Observable 객체에 다른 Observable 객체를 추가 하고 싶을 때 사용한다
