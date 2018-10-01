package com.rivuchk.packtpub.reactivekotlin.chapter05.Transfomring

import io.reactivex.Observable

fun main(args: Array<String>) {
    Observable.range(0,10)//(1)
            .filter{it>15}//(2)
            .switchIfEmpty(Observable.range(11,10))//(3)
            .subscribe({
                println("Received $it")
            })
}
// * 정리
// - switchIfEmpty
//      - 이건 Observable 이 비어 있다면 새로운 Observable을 줄테니깐 이걸 사용 하라는 뜻