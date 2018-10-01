package com.rivuchk.packtpub.reactivekotlin.chapter06.Combining_Producer

import io.reactivex.Observable
import io.reactivex.functions.BiFunction
import kotlinx.coroutines.experimental.delay
import kotlinx.coroutines.experimental.runBlocking
import java.util.concurrent.TimeUnit

fun main(args: Array<String>) {
    val observable1 = Observable.interval(100, TimeUnit.MILLISECONDS)
    val observable2 = Observable.interval(250, TimeUnit.MILLISECONDS)

    Observable.combineLatest(observable1,observable2,
            BiFunction { t1:Long, t2:Long -> "t1: $t1, t2: $t2" })
            .subscribe{
                println("Received $it")
            }

    runBlocking { delay(1100) }
}

// * 정리
// - CombineLatest
//      - 양쪽에서 가장 최근에 나온애들 둘이 합쳐서 결과물을 만들어 준다
//      - emit을 기다려 주지 않는 다는 점에서 zip과 다르다