package com.rivuchk.packtpub.reactivekotlin.chapter03

import io.reactivex.rxkotlin.toObservable


fun main(args: Array<String>) {
    val connectableObservable =
            listOf("String 1", "String 2", "String 3", "String 4", "String 5")
                    .toObservable()
                    .publish()//1 -> publish 는 cold obervable을 connectableObservable로 바꿔준다

    connectableObservable.subscribe({ println("Subscription 1: $it") })//2

    connectableObservable
            .map(String::reversed)//3 -> map은 어떤 item을 emitt 할건지 정하는 기능을ㄹ 한다
            .subscribe({ println("Subscription 2 $it") })//4

    connectableObservable.connect()//5 -> emitting을 시작하게 된다

    connectableObservable.subscribe({ println("Subscription 3: $it") })//6 ->Will never get called
}

// * 정리
// - Introducing the ConnectableObservable object(Hot Observable)
//      - 뭐든 Hot observable로 만들수 있음(심지어 cold obervable도 hot으로 만들 수 있음)
//      - subscribe 을 호출해도 emitt하지 않고, 대신 connect를 호출하면 active 상태가 된다
//      - 따라서, connect를 호출 하기전에 subscribe를 호출 해야 된다
//      - 만들어진 목적
//          - 하나의 ConnectableObservable을 만들어서 복수개의 oberver에 연결을 시키기 위해서
//          - hot observable과 다르게 여러번 emitting을 시작 할 수 있다 -> subscribe를 한번 한다고 끝나버리는게 아니다
//