package day01

import org.apache.spark.rdd.RDD
import org.apache.spark.{Partitioner, SparkConf, SparkContext}

object MyPartition {
  def main(args: Array[String]): Unit = {
    val conf: SparkConf = new SparkConf()
    conf.setAppName("MyPartition")
    conf.setMaster("local")
    val context: SparkContext = new SparkContext(conf)
    val data: List[(String, Int)] = List(("math", 89), ("english", 84), ("hadoop", 56), ("math", 87),
      ("english", 122), ("hadoop", 89), ("hadoop", 100))
    val rdd: RDD[(String, Int)] = context.makeRDD(data, 4)
    val rdd_result1: RDD[(String, Int)] = rdd.partitionBy(new MyPartitioner(3))
    rdd_result1.foreach(x=>println(x))
  }
}
class MyPartitioner(val ptn: Int) extends Partitioner {
  //分个数
  override def numPartitions: Int = ptn

  //分区器按照某种分区逻辑
  override def getPartition(key: Any): Int = {
    val ptn_index: Int = (key.toString.hashCode()&Integer.MAX_VALUE) % ptn
    ptn_index
  }
}
