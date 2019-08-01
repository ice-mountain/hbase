package day01

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
  * map,foreach,filter
  */
object RDD_Test04 {
  def main(args: Array[String]): Unit = {
    val sparkConf: SparkConf = new SparkConf()
    sparkConf.setMaster("local")
    sparkConf.setAppName("RDD_Test04")
    val sparkContext: SparkContext = new SparkContext(sparkConf)
    val array1 = Array(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
    val array3 = new Array[Int](100)
    for (array <- 0 until array3.length) {
      array3(array) += array
    }
    val rdd1: RDD[Int] = sparkContext.parallelize(array1)
    println("-------------map-------------")
    rdd1.map(x => x * 2).foreach(x => println(x))
    rdd1.map(x => (x, x)).foreach(x => println(x))
    println("-------------filter-------------")
    //保留奇数
    rdd1.filter((x: Int) => if (x % 2 == 1) true else false).foreach(x => println(x))
    println("-------------flatMap-------------")
    val array2 = Array("hello huangbo", "hello xuzheng", "hello wangboqing")
    val rdd2: RDD[String] = sparkContext.makeRDD(array2)
    val rdd3 = rdd2.flatMap((line: String) => line.split(" "))
    rdd3.foreach(x => println(x))
    println("-------------sample-------------")
    //采样算法
    /**
      * withReplacement: Boolean,采取样的样本是否放回
      * fraction: Double,采取的概率
      * seed: Long = Utils.random.nextLong)
      */
    val rdd5: RDD[Int] = sparkContext.makeRDD(array3)
    val str:String = rdd5.sample(true,0.05,0l).collect().mkString(",")
    println(str)
    val rdd4: Array[Int] = rdd5.takeSample(true,10, 0l)
    val str2:String =rdd4.mkString(",")
    println(str2)
    sparkContext.stop()
  }
}
