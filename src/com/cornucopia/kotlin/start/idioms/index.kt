package com.cornucopia.kotlin.start.idioms

import java.io.File

/**
 * kotlin-习惯用法
 * @author cornucopia
 * @version 1.0
 * @since 2019/12/28
 */
fun main() {
}

//1.创建DTOs(POJOs/POCOs)
data class Customer(val name: String, val email: String)

//1.1 data class 自动提供`equals()`,`hashCode()`,`toString()`,`copy()`,所有属性的
//`component1()`
fun printCustomer() {
    var customer = Customer("刘伟", "13040839537@163.com")
    println(customer.toString())
}

//2.函数的默认参数
fun foo(a: Int = 0, b: String = "") {
    print("a = $a   b= $b ")
}

//3.过滤list
fun testFilter() {
    var list = listOf<String>("hello", "world")
    val item = list.filter { x -> x.contains("d") }
    val item1 = list.filter { it.contains("o") }
    println(item)
    println(item1)
}

//4.检查元素是否在集合中
fun containOne() {
    var list = listOf<String>("hello", "world")
    if ("hello" in list) {
        println("true")
    }
}

//5.字符串内插或者拼接
fun stringJoin() {
    val name = "lw"
    println("a is $name")
}

//6.类型判断
fun judgeType() {
    var x = "hello"
    when (x) {
        is String -> println("String")
    }
}

//7.遍历map/pair型list
fun testFor() {
    var map = mapOf("a" to 1, "b" to 2)
    for ((k, v) in map) {
        println("$k -> $v")
    }
}

//8.使用区间
//for (i in 1..100) { …… } // 闭区间：包含 100
//for (i in 1 until 100) { …… } // 半开区间：不包含 100
//for (x in 2..10 step 2) { …… }
//for (x in 10 downTo 1) { …… }
//if (x in 1..10) { …… }

//9.只读list以及map，访问map
fun testMapAndList() {
    val list = listOf<String>("a", "b")
    val map = mapOf<String, Int>("a" to 1, "b" to 2)
    println(map["a"])
}

//10.延迟属性
fun testLazy() {

}

//11.扩展函数
fun String.getLength() = this.length

fun testExtend() {
    println("test".getLength())
}

//12.创建单例
//12.1 object修饰类，在声明类的同时创建一个实例
//12.2 声明时候，使用类名即可
object Resource {
    val name = "Name"
}

fun testSingle() {
    val resource = Resource
    println(resource.name)
}

//13. if null
// 13.1  ?. 如果不为空(则执行)
// 13.2  ?: 如果不为空(则执行)，如果为空，则取值:后的值
// 13.3  ?.let 如果不为空，则执行代码。与前面不一样的是:?.,?:都是接的变量
fun testNull() {
    val files = File("test").listFiles()
    println(files?.size)
    println(files?.size ?: "empty")
    files?.let {
        //如果不为空，则执行代码
    }
}

//14. 映射可空值(如果非空的话)
//14.1 ?.let{ 带返回值的函数表达式 }
fun transformValue(a: Any): Int? {
    if (a is String) {
        return Integer.parseInt(a)
    }
    return null
}

fun testNotNull() {
    val value = "12"
    println(transformValue(value))
    val mapped = value?.let { transformValue(it) } ?: 0
    println(mapped)
}

//15.返回when表达式
fun whenCore(color: String): Int {
    return when (color) {
        "Red" -> 0
        "Green" -> 1
        "Blue" -> 2
        else -> throw IllegalArgumentException("Invalid color param value")
    }
}

fun testWhen() {
    val result = whenCore("Red")
    println(result)
}

//16.try/catch表达式
fun testTryCatch() {
    val result = try {
        whenCore("Red")
    } catch (e: Exception) {
        throw IllegalArgumentException(e)
    }
    println(result)
}

//17.if表达式
fun testIf() {
    val param = 1
    val result = if (param == 1) {
        "One"
    } else if (param == 2) {
        "Two"
    } else {
        "Three"
    }
    println(result)
}





