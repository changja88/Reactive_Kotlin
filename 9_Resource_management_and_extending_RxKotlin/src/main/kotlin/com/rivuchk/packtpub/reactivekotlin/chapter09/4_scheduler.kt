package com.rivuchk.packtpub.reactivekotlin.chapter09

import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.experimental.delay
import kotlinx.coroutines.experimental.runBlocking

fun main(args: Array<String>) {
    Observable.range(1,10)
            .map {
                println("map - ${Thread.currentThread().name} $it")
                it
            }
            .subscribeOn(Schedulers.computation())
            .observeOn(Schedulers.io())
            .subscribe {
                println("onNext - ${Thread.currentThread().name} $it")
            }

    runBlocking { delay(100) }
}

// * 정리
// - Composing operators with transformer
//      - create new operators by combining multiple operators
//      -> subscribeOn, observeOn을 같이 사용 하고 싶다
//      -> 계산의 subscribeOn에서 시키고, 그 결과 같음 main 에서 처리하고 싶다