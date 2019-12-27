package com.cornucopia.kotlin.start.base

/**
 * kotlin-基本语法
 * @author cornucopia
 * @version 1.0
 * @since 2019/12/28
 */
fun main() {
    var sum = sum(1, 2)
    print(sum)
}

//1.函数
fun sum(a: Int, b: Int): Int {
    return a + b
}