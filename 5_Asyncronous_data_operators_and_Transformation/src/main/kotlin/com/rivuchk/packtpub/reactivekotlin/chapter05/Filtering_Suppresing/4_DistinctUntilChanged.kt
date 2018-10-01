package com.rivuchk.packtpub.reactivekotlin.chapter05.Filtering_Suppresing

import io.reactivex.rxkotlin.toObservable

fun main(args: Array<String>) {
    listOf(1,2,2,3,4,5,5,5,6,7,8,9,3,10)//(1)
            .toObservable()//(2)
            .distinctUntilChanged()//(3)
            .subscribe { println("Received $it") }//(4)
}
// * 정리
// - distinctUntilChanged
//      - 이전값이 다른 값이 emit 되면 처리 해준다