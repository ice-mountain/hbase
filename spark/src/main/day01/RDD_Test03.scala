package day01

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object RDD_Test03 {
  def main(args: Array[String]): Unit = {
    val conf: SparkConf = new SparkConf()
    conf.setAppName("RDD_Test03")
    conf.setMaster("local")
    val context: SparkContext = new SparkContext(conf)
    val array1 = Array(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
    val rdd1: RDD[Int] = context.parallelize(array1, 3)
    println("----------------mapPartitions-------------")
    val rdd3: RDD[Int] = rdd1.mapPartitions((x: Iterator[Int]) => {
      println("执行了一次分区处理")
      x.toList.map(x => x * x).toIterator
    })
    rdd3.foreach(x => println(x))
    println("------------------mapPartitionsWithIndex------------------------")
    // f: (Int, Iterator[T]) => Iterator[U]
    //Int 就是分区的编号 如果有4个分区那就是0，1,2,3
    val rdd2: RDD[Int] = rdd1.mapPartitionsWithIndex((index: Int, data: Iterator[Int]) => {
      println("执行了一次分区处理 当前的分区编号是：" + index)
      data.map(x => x * x).toIterator
    })
    rdd2.foreach(x => println(x))
    context.stop()
  }
}
