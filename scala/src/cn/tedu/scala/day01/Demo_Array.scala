package cn.tedu.scala.day01

import scala.collection.mutable.ArrayBuffer

object Demo_Array {
  def main(args: Array[String]): Unit = {
    val array = Array(1, 2, 3, 4)
    println(array.toBuffer)
    val ab = array.toBuffer
    //追加一个元素
    ab += 1
    //追加多个元素
    ab += (1, 5, 4, 7)
    //追加一个定长数组
    ab ++= Array(4, 7, 8, 9)
    //追加一个可变数组
    ab ++= ArrayBuffer(4, 8, 9)
    println(ab)
    val a = ArrayBuffer[Int](2,3,5,6,7)
    a += 1
    a.insert(0, 5)
    println(a)
    a.remove(0, 1)
    println(a)
    println(a.min)
    println(a.max)
    println(a.sum)
    val str: String = a.mkString("-")
    print(str+"\n")
    val str1: String = a.mkString("<","-",">")
    print(str1+"\n")
    val result = a.map((x:Int)=>x+1).mkString(",")
    print(result+"\n")
    //通过reduce求这个数组的最大值
    val result1 = a.reduce((x:Int,y:Int)=>if(x>y) x else y)
    println(result1)
    //通过reduce来求和
    val  result2=a.reduce((x:Int,y:Int)=>x+y)
    print(result2)

  }

}
