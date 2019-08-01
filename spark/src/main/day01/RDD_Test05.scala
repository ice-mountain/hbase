package day01

import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.rdd.RDD

/**
  * distinct去重
  */
object RDD_Test05 {
  def main(args: Array[String]): Unit = {
    val conf: SparkConf = new SparkConf()
    conf.setAppName("MyPartition")
    conf.setMaster("local")
    val context: SparkContext = new SparkContext(conf)
    val array1 = Array(1, 1, 2, 4, 2, 3, 4, 5, 6)
    val rdd1: RDD[Int] = context.parallelize(array1,2)
    val array2 = Array("hello huangbo", "hello xuzheng", "hello wangboqing")
    val rdd2: RDD[String] = context.makeRDD(array2)
    val str1: String = rdd1.distinct().collect().mkString(",")
    val str2:String = rdd1.distinct(1).collect().mkString(",")
    val str3: String= rdd2.distinct().collect().mkString(",")
    println(str1)
    println(str2)
    println(str3)
  }
}