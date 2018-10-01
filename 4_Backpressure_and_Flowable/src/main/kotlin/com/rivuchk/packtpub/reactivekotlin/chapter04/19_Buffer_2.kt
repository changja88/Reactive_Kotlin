package com.rivuchk.packtpub.reactivekotlin.chapter04

import io.reactivex.Flowable

fun main(args: Array<String>) {
    val flowable = Flowable.range(1,111)
    flowable.buffer(10,15)//(1)
            .subscribe { println("Subscription 1 $it") }

    flowable.buffer(15,7)//(2)
            .subscribe { println("Subscription 2 $it") }
}


// * 정리
// - Learning Buffer, Throttle, and Window operators
//      - backpressure 를 해결 하는 방법을 배웠지만 (Flowable) downstream에서 해결하는 방법은 좋지 않다
//      - Buffer, Throttle, Window 를 사용해서 해결 하는 편이 좋

// - Buffer
//      - onBackPressureBuffer()
//        -> emit 된 건을 다 처리 하기 전까지 emission을 buffer 한다
//        -> 이건 진짜 처리 못하는 동안 emit 된것을 기다렸다가 하나씩 다시 전달 하는 것
//      - Buffer()
//        -> emit 된 것들을 bastch 로 모아서 list나 다른 collection 타입으로 전달한다
//        -> 이거는 batch라고 생각하면 된다 모아서 한방에 전달 하는 방식
//        -> 첫번째 파라미터는 count값 -> 몇개를 1개의 배치로 만들 것인지
//        -> 두번째 파라미터는 skip count 이다
//              -> skip count가 count값보다 크면 그 차이 만큼을 건너 뛴다
//              -> skip count가 count값보다 작으면 그 차이만큼을 반복 하게 된다
//              -> 따라서 둘이 값이 같으면 아무것도 안한다
//              -> (돌려 보면암)