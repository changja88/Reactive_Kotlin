package com.rivuchk.packtpub.reactivekotlin.chapter04

import io.reactivex.Flowable

fun main(args: Array<String>) {
    val flowable = Flowable.range(1, 111)//(1)
    flowable.window(10)
            .subscribe { flo ->
                flo.subscribe {
                    print("$it, ")// ->
                }
                println()
            }
}
// * 정리
// - Window
//      - Buffer와 거의 유사하게 동작한다
//      - 차이점은 버퍼는 Collection으로 전달하지만 window는 다른 producer로 전달한
//      -> 돌려보면 10개씩 모아서 하나의 producer를 만들어서 전달한다
//          -> 10개씩 모은 producer를 어떻게 할건지가 필요 하기때문에 flo->flo.subscribe {} 이부분이 존재한다
