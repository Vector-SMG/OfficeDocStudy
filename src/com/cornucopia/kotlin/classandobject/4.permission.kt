package com.cornucopia.kotlin.classandobject

/**
 * 可见性修饰符
 * @author cornucopia
 * @version 1.0
 * @since 2020/1/2
 */
fun main() {

}
//1.包
//1.1 public，在随处可见。
//1.2 private,它只会声明它的文件内可见。
//1.3 internal,它会在相同模块随处可见。


//2.类和接口
//2.1 private类内部可见
//2.2 protected，在子类中可见。
//2.3 internal，模块内
//2.4 public,随处可见
open class Outer{
    private val a=1
    protected open val b=2
    internal  val c=3
    val d=4

    protected class Nested{
        public val e:Int=5
    }
}
class SubClass:Outer(){
    //a不可见
    fun test(){
        //b,c,d可见
        //Nested和e可见
    }
    override val b: Int=3
}
class Unrelated(o:Outer){

    init {
        //o.a,o.b不可见
        //o.c,o.d 本模块
        //Outer.Nested不可见,Nested:e不可见
    }
}

//3.构造函数
//3.1 默认构造函数都是public，如果要私有必须显式的指定

//4.局部声明
//4.1 局部声明不可有可见性修饰符

//5.模块
//5.1 模块是编译在一起的一套kotlin文件:
//5.2 一个Intellij IDEA模块,一个Maven项目,一个Gradle源集，一次Ant任务执行 所编译的一套文件。




