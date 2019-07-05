package cn.tedu.scala.day02

import scala.collection.mutable

/**
  * map的相关练习
  */
object Demo_Map {
  def main(args: Array[String]): Unit = {
    val map0=mutable.HashMap("a"->1,"b"->2,"c"->3)
    println(map0("a"))
    //避免抛出异常
    println(map0.get("a"))
    //避免抛出异常做了if else判断当key不存在返回默认值
    println(map0.getOrElse("c",2))
    map0+=(("c",3),("d",4))
    println(map0)
    map0-="a"
    println(map0)
  }
}
