package cn.tedu.scala.day03

object AbstractTest {
  def main(args: Array[String]): Unit = {
    val dog=new Dog
    println(dog.id)
    println(dog.name)
    println(dog.m1(1))
    println(dog.m2(10))
  }
}

/**
  * 抽象类不能实例化对象
  */
abstract class Animal{
  //抽象字段
  val id:Int
  //具体字段
  val name:String="aa"
  //定义抽象方法
  def m1(x:Int)
  //定义具体方法
  def m2(x:Int)=x+2
}
class  Dog extends Animal{
  //这两个是Animal中为实现的如果是未实现的方法可以不加override关键字
  override val id: Int = 11
  override def m1(x: Int): Unit= println("Hello World")
  //这个是Animal中已经实现的重写已经实现的方法必须要加override关键字
  override def m2(x: Int): Int = x+3
}