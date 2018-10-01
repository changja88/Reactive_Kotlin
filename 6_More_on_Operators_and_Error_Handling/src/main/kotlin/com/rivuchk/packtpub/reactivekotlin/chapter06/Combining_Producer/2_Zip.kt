package com.rivuchk.packtpub.reactivekotlin.chapter06.Combining_Producer

import io.reactivex.Observable
import io.reactivex.functions.BiFunction
import kotlinx.coroutines.experimental.delay
import kotlinx.coroutines.experimental.runBlocking
import java.util.concurrent.TimeUnit

fun main(args: Array<String>) {
    val observable1 = Observable.range(1, 10)
    val observable2 = Observable.range(11, 10)
    Observable.zip(observable1, observable2, BiFunction<Int, Int, Int> { emissionO1, emissionO2 ->
        emissionO1 + emissionO2
    }).subscribe {
        println("Received $it")
    }

    val observable3 = Observable.interval(100,TimeUnit.MILLISECONDS)//(1)
    val observable4 = Observable.interval(250,TimeUnit.MILLISECONDS)//(2)

    Observable.zip(observable3,observable4,
            BiFunction { t1:Long, t2:Long -> "t1: $t1, t2: $t2" })//(3)
            .subscribe{
                println("Received $it")
            }

    runBlocking { delay(1100) }
}

// * 정리
// - Zip
//      - Observable을 복수개(최대 9개) 사용 하고 있는데 각각에서 emit된 아이템 하나씩을 가지고 할 작업이 있을 경우 사용한다
//      - take functions to apply on the emissions as the scan or reduce operator,
//        but applies them to emissions from different producers
//      - Flowable로도 할 수 있다

//      - 중요 포인트
//          - 첫번째 observable 한테 n번째를 가져오면 다음 observable한테도 n번째를 가져온다 n+1번째 못가져온다
//          - 처리를 하기전에 먼져 emit이 될 때까지 기다린다
//            첫번째 observable한테 n번째를 가져왔다면 다음번 observable한테 n번째를 가져올 때까지 기다린다
//          - 어떤 observable이 onComplete 나 onError에 걸리면 처리 결과를 emit하지 않고 다 내다 버린다
//            첫번째가 8개 아이템이 있고 두번째가 15개 아이템이 있다면 8번째 이후 부터는 다내다 버린다
