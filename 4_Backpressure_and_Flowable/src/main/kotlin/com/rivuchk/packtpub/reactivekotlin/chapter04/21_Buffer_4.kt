package com.rivuchk.packtpub.reactivekotlin.chapter04

import io.reactivex.Flowable
import kotlinx.coroutines.experimental.delay
import kotlinx.coroutines.experimental.runBlocking
import java.util.concurrent.TimeUnit

fun main(args: Array<String>) {
    val boundaryFlowable = Flowable.interval(350, TimeUnit.MILLISECONDS)

    val flowable = Flowable.interval(100, TimeUnit.MILLISECONDS)//(1)
    flowable.buffer(boundaryFlowable)//(2)
            .subscribe { println(it) }

    runBlocking { delay(5, TimeUnit.SECONDS) }//(3)

}

// * 정리
// - BoundaryFlowable
//      - BufferOperator can take producer as the boundary
//      - 100에 하나씩 내보내는데 350동안 모았다가 전달 하니깐 3개 4개 3개 반복으로 결과가 나오게된다