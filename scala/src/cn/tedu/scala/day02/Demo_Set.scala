package cn.tedu.scala.day02

import scala.collection.immutable.HashSet
import scala.collection.mutable

object Demo_Set {
  def main(args: Array[String]): Unit = {
    val set0=new HashSet[Int]()
    println(set0)
    val set1=set0+4
    println(set0,set1)
    val set3=set1++Set(5,6,7)
    println(set3)
    val set4=Set(1,2,3,4)++set3
    println(set4)
    val set5=Set(1,2,3,4,5)
    val set6=Set(3,4,5,6,7)
    //交集
    println(set5.intersect(set6))
    //并集
    println(set5.union(set6))
    //差集只在set5中
    println(set5.diff(set6))
    val set7=new mutable.HashSet[Int]()
    val set8=set7+1
    println(set7,set8)
    set7+=1
    println(set7)

  }
}
