package com.rivuchk.packtpub.reactivekotlin.chapter06.Combining_Producer

import io.reactivex.Observable
import java.util.*
import java.util.concurrent.TimeUnit

fun main(args: Array<String>) {
    Observable.range(1,10)
            .concatMap {
                val randDelay = Random().nextInt(10)
                return@concatMap Observable.just(it)
                        .delay(randDelay.toLong(), TimeUnit.MILLISECONDS)//(1)
            }
            .blockingSubscribe {
                println("Received $it")
            }
}
// * 정리
// - ConcatMap
//      - 내부적으로 concat을 사용한다
//      - prescribed order of emission을 유지하기 때문에 순서대으로 나온다
//      - concatMap 에서 필요한 작업을 하는데 걸리는 시간이 랜덤으로 설정 되어있다
//        -> 때문에 1부터 10까지 순서대로 concatMap 들어가고 1부터 10까지 순서대로 나온다(내부적으로 cocnat이어서)


// - When to user concatMap operator
//      - When you are downloading the list of data to display to the user
//        The order really matters here, you will surely not want to load and idsplay the second item of
//        the list afthe thrid and fourth one are already displayed
//      - Sorted list에 뭔가 작업을 하고 싶고 순서를 유지하고 싶을 때
//