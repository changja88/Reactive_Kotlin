package com.rivuchk.packtpub.reactivekotlin.chapter04

import io.reactivex.BackpressureStrategy
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.experimental.delay
import kotlinx.coroutines.experimental.runBlocking


fun main(args: Array<String>) {
    val source = Observable.range(1, 1000)
    source.toFlowable(BackpressureStrategy.MISSING)//(1)
            .onBackpressureDrop{ print("Dropped $it;\t") }//(2) 놓친 아이템이 들어 온다
            .map { MyItem12(it) }
            .observeOn(Schedulers.io())
            .subscribe{
                print("Rec. $it;\t")
                runBlocking { delay(100) }
            }
    runBlocking { delay(600000) }
}

data class MyItem12 (val id:Int) {
    init {
        print("MyItem init $id\t")
    }
}

// * 정리
// - Operator onBackpressureDrop()
//      - BackpressureStrategy.DROP과 같이 사용하는 것이 좋다
//      - onBackpressureDrop{ print("Dropped $it;\t") } -> 드랍된 아이템이 들어 온다