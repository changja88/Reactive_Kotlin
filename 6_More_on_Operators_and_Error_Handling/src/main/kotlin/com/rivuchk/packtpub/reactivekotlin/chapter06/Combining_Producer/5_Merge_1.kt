package com.rivuchk.packtpub.reactivekotlin.chapter06.Combining_Producer

import io.reactivex.Observable
import io.reactivex.rxkotlin.toObservable

fun main(args: Array<String>) {
    val observable1 = listOf("Kotlin", "Scala", "Groovy").toObservable()
    val observable2 = listOf("Python", "Java", "C++", "C").toObservable()

    Observable
            .merge(observable1, observable2)//(1)
            .subscribe {
                println("Received $it")
            }
}

// * 정리
// - Merge
//      - Merging all the emissions of all the source producers together
//        and subscribing to them as a whole is the solution
//      -