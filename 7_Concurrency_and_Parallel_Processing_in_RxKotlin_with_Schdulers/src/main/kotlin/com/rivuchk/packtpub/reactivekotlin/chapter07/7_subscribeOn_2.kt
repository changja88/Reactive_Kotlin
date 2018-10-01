package com.rivuchk.packtpub.reactivekotlin.chapter7

import io.reactivex.rxkotlin.toObservable
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.experimental.delay
import kotlinx.coroutines.experimental.runBlocking

fun main(args: Array<String>) {
    listOf("1", "2", "3", "4", "5", "6", "7", "8", "9", "10")
            .toObservable()
            .map { item ->
                println("Mapping $item - ${Thread.currentThread().name}")
                return@map item.toInt()
            }
            .subscribeOn(Schedulers.computation())//(1)
            .subscribe { item ->
                println("Received $item - ${Thread.currentThread().name}")
            }

    runBlocking { delay(1000) }
}
// * 정리
// - subscribeOn 적용
//      - subscribeOn이 적용이 되면 그 이후로는 정해준 쓰레드 방식으로 쓰레드를 갈아타서 작업을 진행하게 된