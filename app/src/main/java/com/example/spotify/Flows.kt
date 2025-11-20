package com.example.spotify

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.buffer
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.reduce
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.flow.zip
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.system.measureTimeMillis

fun simple(): Flow<Int> = flow {
    for (i in 1..4) {
        delay(100) // pretend we are asynchronously waiting 100 ms
        emit(i) // emit next value
    }
}
val newRange = 1..3
fun numbers(): Flow<Int> = newRange.asFlow().onEach { delay(300) }
fun letters(): Flow<String> = flowOf("a", "b", "c").onEach { delay(400) }




fun requestFlow(i: Int): Flow<String> = flow {
    emit("$i: First")
    delay(500) // wait 500 ms
    emit("$i: Second")
}

fun main() = runBlocking<Unit> {


    (1..3).asFlow().map { requestFlow(it) }.collect{
        println(it)
    }



//    val startTime = System.currentTimeMillis()
//    numbers().combine(letters(), { i, string ->
//        string+i.toString()
//    }).collect {
//
//        println("value at ${System.currentTimeMillis()-startTime} is $it")
//    }
}

val numberStream: Flow<Int> = flow {
    println("Flow: Started") // This will only print when collected
    delay(1000) // Pretend we're doing some work
    println("Flow: Emitting 1")
    emit(1) // Emitting the first value

    delay(1000)
    println("Flow: Emitting 2")
    emit(2)

    delay(1000)
    println("Flow: Emitting 3")
    emit(3)
}