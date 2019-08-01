package day01

import org.apache.spark.{SparkConf, SparkContext}

object RDD_scala {
  def main(args: Array[String]): Unit = {
    val conf: SparkConf = new SparkConf()
    conf.setAppName("MyPartition")
    conf.setMaster("local")
    val context:SparkContext = new SparkContext(conf)
    context.textFile(args(0)).flatMap(line=>line.split(","))
      .map((_,1)).reduceByKey(_+_).saveAsTextFile(args(1))
    context.stop()

  }
}
