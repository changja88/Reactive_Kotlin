package com.rivuchk.packtpub.reactivekotlin.chapter02

import kotlinx.coroutines.experimental.delay
import kotlinx.coroutines.experimental.runBlocking
import java.util.concurrent.TimeUnit
import kotlin.system.measureTimeMillis

/**
 * Created by rivuc on 13-07-2017.
 */

suspend fun longRunningTsk():Long {//(1)
    val time = measureTimeMillis {//(2)
        println("Please wait")
        delay(2,TimeUnit.SECONDS)//(3)
        println("Delay Over")
    }
    return time
}

fun main(args: Array<String>) {
    runBlocking {//(4)
        val exeTime = longRunningTsk()//(5)
        println("Execution Time is $exeTime")
    }
}

// * 정리
// - suspend -> suspend는 다른 suspend블럭 이나 코루틴 블럭 안에서만 실행 가능하다
// - runBlocking -> Main Thread 도 막아 버리고 안에 블럭을 실행한다
