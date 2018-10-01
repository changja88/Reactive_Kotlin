package com.rivuchk.packtpub.reactivekotlin.chapter7

import io.reactivex.rxkotlin.toObservable
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.experimental.delay
import kotlinx.coroutines.experimental.runBlocking

fun main(args: Array<String>) {
    listOf("1","2","3","4","5","6","7","8","9","10")
            .toObservable()
            .observeOn(Schedulers.computation())//(1)
            .map {
                item->
                println("Mapping $item - ${Thread.currentThread().name}")
                return@map item.toInt()
            }
            .observeOn(Schedulers.io())//(2)
            .subscribe {
                item -> println("Received $item - ${Thread.currentThread().name}")
            }

    runBlocking { delay(1000) }
}

// * 정리
// - observeOn
//      - 원하는 쓰레드로 갈아 태울때 사용 된다
//      - observeOn 선언 이후로는 observeOn에서 선언한 쓰레드를 타게 된다