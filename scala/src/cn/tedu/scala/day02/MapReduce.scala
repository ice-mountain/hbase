package cn.tedu.scala.day02

import scala.collection.mutable.ArrayBuffer

object MapReduce {
  def main(args: Array[String]): Unit = {
    val a = ArrayBuffer[Int](2,3,5,6,7)
    val b=ArrayBuffer("aa","bb","cc")
    val result = a.map((x:Int)=>x+1).mkString(",")
    print(result+"\n")
    val result3 = b.map((s:String)=>"aa").mkString(",")
    println(result3)
    //通过reduce求这个数组的最大值
    val result1 = a.reduce((x:Int,y:Int)=>if(x>y) x else y)
    println(result1)
    //通过reduce来求和
    val  result2=a.reduce((x:Int,y:Int)=>x+y)
    println(result2)
    val result4 = b.reduce((x:String,y:String)=>x+y)
    println(result4)
    val result5 = a.filter((x:Int)=>if(x%2!=0)true else false).mkString(",")
    print(result5)
  }
}
