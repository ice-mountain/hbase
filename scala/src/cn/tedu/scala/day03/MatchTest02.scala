package cn.tedu.scala.day03

import scala.util.Random

/**
  * 模式匹配
  */
object MatchTest02 {
  def main(args: Array[String]): Unit = {
    val array: Array[Any] = Array("1",2,5.5,7.7,true)
    val index: Int = new Random().nextInt(array.length)
    val value = array(index)
    println(value)
    value match {
      case x:String=>println("调用了String的方法")
      case x:Int=>println("调用用了Int的方法")
      case x:Double if x>6 =>println(x,"调用用了Double的方法")
      case x:Boolean=>println("调用用了Boolean方法")
      case _=>println("Any else")
    }

  }
}
