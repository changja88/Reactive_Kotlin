package com.rivuchk.packtpub.reactivekotlin.chapter02

import io.reactivex.Maybe
import io.reactivex.rxkotlin.subscribeBy

/**
 * Created by rivuc on 15-07-2017.
 */

fun main(args: Array<String>) {
    val maybeValue: Maybe<Int> = Maybe.just(14)//1
    maybeValue.subscribeBy(//2
            onComplete = {println("Completed Empty")},
            onError = {println("Error $it")},
            onSuccess = { println("Completed with value $it")}
    )

    var maybeEmpty:Maybe<Int> = Maybe.empty()//3
    maybeEmpty.subscribeBy(
            onComplete = {println("Completed Empty")},//4
            onError = {println("Error $it")},//5
            onSuccess = { println("Completed with value $it")}//6
    )
}

// * 정리
// - Monad
//      - monad is a stsructure that creates a new type by encapsulating a value and adding
//        some extra functionalities to it
//      - value 를 encapsulating 하고 기능을 더해서 새로운 타입을 만들어 내는 구조
// - Maybe
//      - value를 포함 하고 있을 수도 있고, 하지 않을 수도 있다.
//      - onError -> error 가 발생하면 호출 된다
//      - onComplete -> noerror + no value 시 호출 된다
//      - onSuccess -> noeeror + contain value 시 호출 된다
