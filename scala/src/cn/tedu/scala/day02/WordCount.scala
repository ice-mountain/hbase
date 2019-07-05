package cn.tedu.scala.day02

import scala.collection.MapView

object WordCount {
  def main(args: Array[String]): Unit = {
    val array = Array("hello huangbo", "hello xuzheng", "hello wangboqaing")
    val result1 = array.map((x:String)=>x.split(" ")).flatten
    val result2: Map[String, Array[(String, Int)]] = result1.map((x:String)=>(x,1)).groupBy((x:(String,Int))=>x._1)
    //mapvalues中参数函数接收的输入数据是map中的key-value中的value的值map中有多少个key-value那么这个函数就会执行多少次
    val result3: MapView[String, Int] = result2.mapValues(x=>x.foldLeft(0)((x:Int,y:(String,Int))=>x+y._2))
    val result4: List[(String, Int)] = result3.toList.sorted
    println(result4)
  }
}
