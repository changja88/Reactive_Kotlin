package com.rivuchk.packtpub.reactivekotlin.chapter04

import io.reactivex.BackpressureStrategy
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.experimental.delay
import kotlinx.coroutines.experimental.runBlocking


fun main(args: Array<String>) {
    val source = Observable.range(1, 1000)
    source.toFlowable(BackpressureStrategy.BUFFER)
            .map { MyItem7(it) }
            .observeOn(Schedulers.computation())
            .subscribe{
                print("Rec. $it;\n")
                runBlocking { delay(600) }
            }
    runBlocking { delay(700000) }
}

data class MyItem7 (val id:Int) {
    init {
        print("MyItem7 init $id;\n")
    }
}

// * 정리
// - Createing Flowable from observable
//      - Observable.toFlowable() -> 어떤 observable이라도 flowable로 바꿔 준다
