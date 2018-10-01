package com.rivuchk.packtpub.reactivekotlin.chapter05.Transfomring

import io.reactivex.rxkotlin.toObservable

fun main(args: Array<String>) {

    val observable = listOf(10, 9, 8, 7, 6, 5, 4, 3, 2, 1).toObservable()
    observable.map { number ->
        "Transforming Int to String $number"
    }.subscribe { item ->
        println("Received $item")
    }
}

// * 정리
// - Map
//      - Map transform an emitted item of type T into an emission of type R
//        by applying the provided lambda of Function<T,R> to it.