package com.rivuchk.packtpub.reactivekotlin.chapter04

import io.reactivex.BackpressureStrategy
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.experimental.delay
import kotlinx.coroutines.experimental.runBlocking


fun main(args: Array<String>) {
    val source = Observable.range(1, 1000)
    source.toFlowable(BackpressureStrategy.MISSING)//(1)
            .onBackpressureBuffer(20)//(2)
            .map { MyItem11(it) }
            .observeOn(Schedulers.io())
            .subscribe{
                println(it)
                runBlocking { delay(100) }
            }
    runBlocking { delay(600000) }
}

data class MyItem11 (val id:Int) {
    init {
        println("MyItem Created $id")
    }
}

// * 정리
// - Operator onBackpressureBuffer()
//      - BackpressureStrategy.BUFFER 를 위해서 만들어 졌음
//      - 파라미터를 채워주지 않으면 디폴트 behavior를 사용 하게(파라미터로 버퍼 사이즈를 정할 수있다)
//      - 버퍼 사이즐르 20으로 해놔서 터지게 되는데 onError에를 처리해주면 안터진다