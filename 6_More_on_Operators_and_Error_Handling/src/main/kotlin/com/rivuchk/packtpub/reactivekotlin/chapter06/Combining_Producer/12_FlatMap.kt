package com.rivuchk.packtpub.reactivekotlin.chapter06.Combining_Producer

import io.reactivex.Observable
import java.util.*
import java.util.concurrent.TimeUnit

fun main(args: Array<String>) {
    Observable.range(1, 10)
            .flatMap {
                val randDelay = Random().nextInt(10)
                return@flatMap Observable
                        .just(it)
                        .delay(randDelay.toLong(), TimeUnit.MILLISECONDS)//(1)
            }
            .blockingSubscribe {
                println("Received $it")
            }
}
// * 정리
// - FlatMap
//      - 내부적으로 merge를 사용 한다
//      - prescribed order of emission을 무시하기 때문에 랜덤으로 나온다
//      - flatMap 에서 필요한 작업을 하는데 걸리는 시간이 랜덤으로 설정 되어있다
//        -> 때문에 1부터 10까지 순서대로 faltMap에 들어가지만 나오는 순서가 다른다 (내부적으로 merge이어서)

// - When to user flatMap operator
//      - when working with a list of data within page, activity, or fragement and want to
//        send some data to a server or db per item of the list. ConcatMap operator will also do here
//        however as the flatMap operator works asynchronously, it will be faster
//      - list item을 비동기방식으로 빠른 시간에 처리 하고 싶은 경우
