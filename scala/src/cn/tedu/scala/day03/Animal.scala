package cn.tedu.scala.day03

/**
  * 特质：最基本的功能可以作为java的接口来使用
  */
trait Animal1 {
  //抽象字段
  val id:Int
  //具体字段
  val name:String="aa"
  //定义抽象方法
  def m1(x:Int)
  //定义具体方法
  def m2(x:Int)=x+2
}
//混入
class AnimalImpl extends Animal1 with Entable {
  override val id: Int =11
  override def m1(x: Int): Unit = ()
  override def eat1(what: String): Unit = println("Hello World")
}
trait  Entable{
  def eat1(what:String):Unit
}