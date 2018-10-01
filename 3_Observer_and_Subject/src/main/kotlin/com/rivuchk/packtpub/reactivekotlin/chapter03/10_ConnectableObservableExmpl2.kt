package com.rivuchk.packtpub.reactivekotlin.chapter03

import io.reactivex.Observable
import kotlinx.coroutines.experimental.delay
import kotlinx.coroutines.experimental.runBlocking
import java.util.concurrent.TimeUnit

/**
 * Created by rivuc on 22-07-2017.
 */

fun main(args: Array<String>) {
    val connectableObservable = Observable.interval(100, TimeUnit.MILLISECONDS).publish()//1

    connectableObservable.subscribe({ println("Subscription 1: $it") })//2

    connectableObservable.subscribe({ println("Subscription 2 $it") })//3

    connectableObservable.connect()//4
    runBlocking { delay(500) }//5

    connectableObservable.subscribe({ println("Subscription 3: $it") })//6
    runBlocking { delay(500) }//7
}

// * 정리
// - delay를 걸어놔서 connect 이후에 subcribe를 해도 connect 한게 다 안끝나서 살짝 걸린다