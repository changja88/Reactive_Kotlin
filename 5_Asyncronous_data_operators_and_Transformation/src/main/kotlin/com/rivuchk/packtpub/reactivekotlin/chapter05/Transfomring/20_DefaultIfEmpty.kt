package com.rivuchk.packtpub.reactivekotlin.chapter05.Transfomring

import io.reactivex.Observable

fun main(args: Array<String>) {

    Observable.range(0,10)//(1)
            .filter{it>15}//(2)
            .subscribe({
                println("Received $it")
            })

    Observable.range(0,10)//(1)
            .filter{it>15}//(2)
            .defaultIfEmpty(15)//(3)
            .subscribe({
                println("Received $it")
            })
}

// * 정리
// - DefaultIfEmpty
//      - 첫번째 observable처럼 produccer가 empty일 경우 사용한다