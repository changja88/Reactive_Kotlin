package com.rivuchk.packtpub.reactivekotlin.chapter05.Filtering_Suppresing

import io.reactivex.Observable
import io.reactivex.rxkotlin.subscribeBy

fun main(args: Array<String>) {
    val observable = Observable.range(1,10)
    observable.first(2)//(1)
            .subscribeBy { item -> println("Received $item") }

    observable.last(2)//(2)
            .subscribeBy { item -> println("Received $item") }

    Observable.empty<Int>().first(2)//(3)
            .subscribeBy { item -> println("Received $item") }
}
// *정리
// - First and Last
//      - first, last, empty 는 defalut value를 받는다
//        first,last,empty조건을 만족하는 item이 없을 경우 defalut value가 나온다