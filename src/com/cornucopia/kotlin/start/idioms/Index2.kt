package com.cornucopia.kotlin.start.idioms

import java.math.BigDecimal
import java.nio.file.Files
import java.nio.file.Paths

/**
 * kotlin-习惯用法(三)
 * @author cornucopia
 * @version 1.0
 * @since 2019/12/31
 */
fun main() {
    testTodo()
}

//1.java7的try with  resources
//1.1 经典代码见`com.cornucopia.kotlin.start.idioms.javaresource.CloseIOOfJava`
//1.2 kotlin方式的try with resources
fun testTryWithResources() {
    val stream = Files.newInputStream(Paths.get("test"))
    stream.buffered().reader().use { reader ->
        println(reader.readText())
    }
}

//2.需要泛型信息的泛型函数的适宜形式 TODO("完善对范型的理解")

//3.使用可空布尔

fun testCanNullBool() {
    val b: Boolean? = null
    if (b == true) {
    } else {
        println(b)
    }
}

//4.`todo()`将代码标记为不完整
//4.1 构造代码轮廓，但是不写完整。
//4.2 Intellij IDEA的kotlin插件可以理解，并添加到TODO工具窗口。
fun testTodo(): BigDecimal = TODO("waiting")