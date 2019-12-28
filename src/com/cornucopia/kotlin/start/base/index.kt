package com.cornucopia.kotlin.start.base

/**
 * kotlin-基本语法
 * @author cornucopia
 * @version 1.0
 * @since 2019/12/28
 */
//1.程序入口
fun main() {
    testCollections()
}

//2.函数

//2.1 带有两个Int参数,返回Int的函数
fun sum(a: Int, b: Int): Int {
    return a + b
}
//2.2 表达式作为函数体，返回类型自动推断
fun sum1(a: Int, b: Int) = a + b

//2.3 无返回值
fun printSum(a:Int,b:Int):Unit{
    println("无返回值函数")
}

//2.4 返回值可以省略
fun printSum1(a:Int,b:Int){
    println("无返回值")
}

//3.变量

//3.1 只读变量。定义变量：1.指定类型 2.类型推断
fun printVal(){
    val a=1 //类型推断
    val a1:Int=2 //指定类型
    println(a)
}

//3.2 非只读变量
fun printVar(){
    var x=1
    var x1:Int = 5
    var x2=5
    x2+=5
    println(x2)
}
//3.3 顶层变量
val PI=3.14
var x=0
fun incrementX(){
    x+=1
}

//4.注释
/*
4.2 多行注释
 */
fun printExplanatory(a:Int){
    val a=5  //4.1 单行注释
}

//5.字符串模板
//5.1 $变量名
//5.2 ${表达式}
fun printString(){
    var a=1
    val s1="a is $a"
    a=2
    val s2="${s1.replace("is","was")},but now is $a"
}

//6.条件表达式
//6.1 方法一
fun maxOf(a:Int,b:Int):Int{
    if(a>b){
        return a
    }else {
        return b
    }
}
//6.2 if作为表达式
fun maxOf1(a:Int,b:Int)=if(a>b) a else b

//7.空值与null检测
//7.1 在声明处的类型后添加?来标识该引用可为空。
fun parseInt(str:String):Int?{
    if(true){
        return null
    }
    return null
}

//8.类型检测与自动类型转换
//8.1 is运算符检测一个表达式是否是某类行的一个实例
//8.2 is运算符确定类型后，不定类型的变量将会被自动转换为确定的类型的变量。
fun getStringLength(obj:Any):Int?{
    if(obj is String){
        //自动进行类型转换, Any -> String
        return obj.length
    }
    return null
}
//9.使用区间
//in运算符可以检测某个数字是否在指定区间内。如果不在，可以使用`!in`
fun testRange(){
    val x=10
    val y=9
    if(x in 1..y+1){
        println("fits in range")
    }
    for (x in 1..10 step 2){} // 1到10区间，步长2

    for( x in 9 downTo 0 step 3){ //9递减到0，步长3
    }
}

//10.Collections
fun testCollections(){
    var fruits= listOf<String>("apple","banana")
    //10.1 in运算符判断是否包含某个实例
    when{
        "orange" in fruits -> println("juicy")
        "apple" in fruits -> println("apple is fine too")
    }
    //10.2 使用lambda表达式来过滤与映射集合
    fruits.filter { it.startsWith("a") }
        .sortedBy { it }
        .map { it.toUpperCase() }
        .forEach { println(it) }
}









