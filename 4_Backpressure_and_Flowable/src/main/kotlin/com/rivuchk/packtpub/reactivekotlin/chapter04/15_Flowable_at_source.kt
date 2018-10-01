package com.rivuchk.packtpub.reactivekotlin.chapter04

import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.experimental.delay
import kotlinx.coroutines.experimental.runBlocking

fun main(args: Array<String>) {
    val flowable = Flowable.generate<Int> {
        it.onNext(GenerateFlowableItem.item)
//        it.onComplete() -> 이걸 호출 해줘야 emiiting을 멈춘
    }//(1)

    flowable
            .map { MyItemFlowable(it) }
            .observeOn(Schedulers.io())
            .subscribe {
                runBlocking { delay(100) }
                println("Next $it")
            }//(2)

    runBlocking { delay(700000) }


    val abc: Flowable<Int> = Flowable.create<Int>({
        it.onNext(GenerateFlowableItem.item)
    }, BackpressureStrategy.BUFFER)


    abc
            .map { MyItemFlowable(it) }
            .observeOn(Schedulers.io())
            .subscribe {
                runBlocking { delay(100) }
                println("Next $it")
            }//(2)

    runBlocking { delay(700000) }


}

data class MyItemFlowable(val id: Int) {
    init {
        println("MyItemFlowable Created $id")
    }
}

object GenerateFlowableItem {
    var item: Int = 0//(3)
        get() {
            field += 1
            return field//(4)
        }
}
// * 정리
// - Generating Flowble with backpressure at source
//      - Flowable.create() 와 Flowable.generate()의 차이
//          - (1)부분이 create()와 다르다
//          - abc라고 만들어 놓은 것과의 차이점일 이해야된다 -> 각각 돌려 보면됨
//              - abc는 한번만 만들고 끝나지만(onNext가 한번만 호출, flowable은 계속 해서 만들어 낸다(onNext가 무한히 호출)
//              - flowable은 onComplete가 불려야지만 item emit하는 것을 멈춘다