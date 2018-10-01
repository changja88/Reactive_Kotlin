package com.rivuchk.packtpub.reactivekotlin.chapter09

import io.reactivex.Observable
import java.io.Closeable


fun main(args: Array<String>) {
    Observable.using({
        Resource()
    }, { resource: Resource ->
        Observable.just(resource)
    }, { resource: Resource ->
        resource.close()
    }).subscribe {
        println("Resource Data ${it.data}")
    }
}

class Resource() : Closeable {

    init {
        println("Resource Created")
    }

    val data: String = "Hello World"

    override fun close() {
        println("Resource Closed")
    }
}

// * Resource Management
// - JVM 에서는 class 들이 'Closable'을 구현하고 있어서 JVM이 알아서 사용을 다하면 치워준다
// -> Imperative programming에서는 쉬운데 reactive programming에서는 어떻게 해야 할까
// -> disposable resources 를 쉽게 다루기 위해서 RxKotlin은 'using'이라는 operator를 제공한다
// - 'using'은 let you create a resource that'll exist only during the life span of the Observable
//   and it will be closed as soon as the Observable completes

// - 'Using' 작동 과정 설명
//      - using 은 resource를 만들고 반환할 'Callable' instance를 받는다
//      - call first lambda before creating the 'Resource' instance
//      - Pass 'Resource' instance to the second lambda to create Observable so that can be subscribed
//      - When Observable call its onComplete event, it will call the thrid lambda to clase the 'Resource'
//