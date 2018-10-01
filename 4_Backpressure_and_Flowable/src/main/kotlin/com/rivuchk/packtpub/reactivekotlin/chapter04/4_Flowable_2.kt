package com.rivuchk.packtpub.reactivekotlin.chapter04

import io.reactivex.Flowable
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.experimental.delay
import kotlinx.coroutines.experimental.runBlocking

fun main(args: Array<String>) {
   Flowable.range(1,1000)//(1)
            .map { MyItem4(it) }//(2)
            .observeOn(Schedulers.computation())
            .subscribe({//(3)
                print("Received $it;\n")
                runBlocking { delay(50) }//(4)
            },{it.printStackTrace()})
    runBlocking { delay(70000) }//(5)
}


data class MyItem4 (val id:Int) {
    init {
        print("MyItem Created $id;\n")
    }
}

// * 정리
// - Flowable 적용 버젼
// - Flowable
//      - Flowable은 observable의 backpressure 버젼이라고 생각하면 된다
//      - Observable과의 유일한 차이점도 backpressure 기능 여부 이다
//      - RxKotlin 2.X 버전에 추가 되었다

// - When to use Flowables and Observables
//      - Flowable이 적합한 상황
//          - Need to deal with larger amounts of data(10,000+ items)
//          - Need to read/parse from a file or databse
//          - Want to emit from network IO operations/Streaming API

//      - Observable이 적합한 상황
//          - Need to deal with smaller amount of data
//          - Performing strictly synchronous operations
//          - When you are emitting UI events(Android)