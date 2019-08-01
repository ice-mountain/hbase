package day01

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object RDD_Test {
  def main(args: Array[String]): Unit = {
    val conf: SparkConf = new SparkConf()
    conf.setAppName("MyPartition")
    conf.setMaster("local")
    val context: SparkContext = new SparkContext(conf)
    val data: List[(String, Int)] = List(("math", 89), ("english", 84), ("hadoop", 56), ("math", 87),
      ("english", 122), ("hadoop", 89), ("hadoop", 100))
    val rdd: RDD[(String, Int)] = context.makeRDD(data)
    //RDD[(String, Int)]------------->RDD[(String, Int)]
    val result1: RDD[(String, Int)] = rdd.reduceByKey((x, y) => x + y)
    //RDD[(String, Int)]------------->collection.Map[String, Int]
    val result2: collection.Map[String, Int] = rdd.reduceByKeyLocally((x, y) => x + y)
    context.stop()
  }
}
