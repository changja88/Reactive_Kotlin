package com.rivuchk.packtpub.reactivekotlin.chapter02

import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.runBlocking

/**
 * Created by rivuc on 13-07-2017.
 */

fun main(args: Array<String>) {
    val time = async(CommonPool) { longRunningTsk() }//(1)
    println("Print after async ")
    runBlocking { println("printing time ${time.await()}") }//(2)
    println("abccc")
}


// * 정리
// - async -> 블럭 안에 suspend를 넣을 수 있다
// - await -> runBlokcing 한테 time 결과값 나오면 실행하라고 말해준다
// - 코루틴 Context의 종류
//      - Unconfined -> run on main thread
//      - CommonPool -> run on common thread pool
//      - Custom -> 쓸일 없을 듯?