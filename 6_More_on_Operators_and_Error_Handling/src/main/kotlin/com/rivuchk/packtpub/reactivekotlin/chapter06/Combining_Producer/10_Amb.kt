package com.rivuchk.packtpub.reactivekotlin.chapter06.Combining_Producer

import io.reactivex.Observable
import kotlinx.coroutines.experimental.delay
import kotlinx.coroutines.experimental.runBlocking
import java.util.concurrent.TimeUnit

fun main(args: Array<String>) {
    val observable1 = Observable.interval(500, TimeUnit.MILLISECONDS).map { "Observable 1 $it" }//(1)
    val observable2 = Observable.interval(100, TimeUnit.MILLISECONDS).map { "Observable 2 $it" }//(2)

    Observable
            .amb(listOf(observable1,observable2))//(3)
            .subscribe {
                println("Received $it")
            }

    runBlocking { delay(1500) }
}

// * 정리
// - Ambiguously combining producers
//      - 통신을 해서 첫번째로 도착하는 것을 처리하고 싶을 때 사용한다 (돌려보면 observable2만 돌아간다)
//      - 다른 combining operator 처럼 ambArray, ambWith 가 있다