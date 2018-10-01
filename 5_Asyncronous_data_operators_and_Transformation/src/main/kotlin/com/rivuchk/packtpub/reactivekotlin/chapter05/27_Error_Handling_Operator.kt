package com.rivuchk.packtpub.reactivekotlin.chapter05

import io.reactivex.Observable
import io.reactivex.rxkotlin.subscribeBy

fun main(args: Array<String>) {
    Observable.just(1,2,3,5,6,7,"Errr",8,9,10)
            .map { it.toIntOrError() }
            .subscribeBy (
                    onNext = {
                        println("Next $it")
                    },
                    onError = {
                        println("Error $it")
                    }
            )
}

// * 정리
// - error handling operator
//      - emit하다가 onError에 걸리게 되면 subcription이 바로 끝나게 된다