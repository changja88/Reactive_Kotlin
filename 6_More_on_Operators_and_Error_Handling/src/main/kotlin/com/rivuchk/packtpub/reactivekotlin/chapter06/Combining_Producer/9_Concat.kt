package com.rivuchk.packtpub.reactivekotlin.chapter06.Combining_Producer

import io.reactivex.Observable
import kotlinx.coroutines.experimental.delay
import kotlinx.coroutines.experimental.runBlocking
import java.util.concurrent.TimeUnit

fun main(args: Array<String>) {
    val observable1 = Observable.interval(500, TimeUnit.MILLISECONDS)
            .take(2)//(1)
            .map { "Observable 1 $it" }//(2)
    val observable2 = Observable.interval(100, TimeUnit.MILLISECONDS)
            .map { "Observable 2 $it" }//(3)

    Observable
            .concat(observable1, observable2)
            .subscribe {
                println("Received $it")
            }

    runBlocking { delay(1500) }
}

// * 정리
// - Concatenating producers
//      - merge와 거의 같지만 respect the prescribed ordering.
//      - 하나의 Observable 구독이 끝나면 다음 Observable을 구독한