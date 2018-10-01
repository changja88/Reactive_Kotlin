package com.rivuchk.packtpub.reactivekotlin.chapter04

import io.reactivex.BackpressureStrategy
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.experimental.delay
import kotlinx.coroutines.experimental.runBlocking


fun main(args: Array<String>) {
    val source = Observable.range(1, 1000)
    source.toFlowable(BackpressureStrategy.MISSING)//(1)
            .onBackpressureLatest()//(2)
            .map { MyItem13(it) }
            .observeOn(Schedulers.io())
            .subscribe{
                print("-> $it;\t")
                runBlocking { delay(100) }
            }
    runBlocking { delay(600000) }
}

data class MyItem13 (val id:Int) {
    init {
        print("init $id;\t")
    }
}

// * 정리
// - Operator onBackpressureLatest()
//      - .onBackpressureLatest()// -> 파라미터로 할수 있는게 없다