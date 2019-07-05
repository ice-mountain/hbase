package cn.tedu.scala.day03

import scala.util.Random

/**
  * 模式匹配
  */
object MatchTest {
  def main(args: Array[String]): Unit = {
    val array: Array[Any] = Array("1",2,5.5,true)
    val index: Int = new Random().nextInt(array.length)
    val value = array(index)
    println(value)
    //模式匹配
    value match {
      case "1" =>println("调用了第二个方法")
      case 2=>println("调用了第二个方法")
      case _=>println("调用了第三第四个方法")
    }
  }

}
