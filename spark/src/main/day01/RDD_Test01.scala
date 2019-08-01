package day01

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
  * union,join,coGroup,subtract,cartesian
  */
object RDD_Test01 {
  def main(args: Array[String]): Unit = {
    val conf: SparkConf = new SparkConf()
    conf.setAppName("MyPartition")
    conf.setMaster("local")
    val context:SparkContext = new SparkContext(conf)
    val array1=Array(1,2,3,4,5,6)
    val rdd1: RDD[Int] = context.parallelize(array1)
    val array2=Array(6,7,8,9,10)
    val rdd2: RDD[Int] = context.parallelize(array2)
    //笛卡尔积
    println("----------cartesian-----------")
    val result1: RDD[(Int, Int)] = rdd1.cartesian(rdd2)
    result1.foreach(x=>println(x))
    //两个变一个
    println("----------union-----------")
    val result2: RDD[Int] = rdd1.union(rdd2)
    result2.foreach(x=>println(x))
    val list1: List[(String, Int)] =List(("a",1),("b",2),("c",3))
    val list2:List[(String,String)]=List(("a","haungbo"),("b","xuzheng"),("c","wuzun"))
    val rdd3: RDD[(String,Int)] = context.parallelize(list1)
    val rdd4: RDD[(String,String)] = context.parallelize(list2)
    //求并集
    println("----------join-----------")
    val result3: RDD[(String, (Int, String))] = rdd3.join(rdd4)
    result3.foreach(x=>println(x))
    //求差集
    println("----------subtract-----------")
    val result4: RDD[Int] = rdd2.subtract(rdd1)
    result4.foreach(x=>println(x))
    context.stop()
  }
}
