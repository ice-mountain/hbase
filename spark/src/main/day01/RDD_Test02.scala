package day01

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
  * groupByKey,reduceByKey,aggregateByKey,sortByKey,combineByKey
  */
object RDD_Test02 {
  def main(args: Array[String]): Unit = {
    val conf: SparkConf = new SparkConf()
    conf.setAppName("MyPartition")
    conf.setMaster("local")
    val context:SparkContext = new SparkContext(conf)
    println("----------groupByKey-----------")
    val data: List[(String, Int)] = List(("math", 89), ("english", 84), ("hadoop", 56), ("math", 87),
      ("english", 122), ("hadoop", 89), ("hadoop", 100))
    val rdd1: RDD[(String, Int)] = context.makeRDD(data)
    val result1: RDD[(String, Iterable[Int])] = rdd1.groupByKey(3)
    result1.foreach(x=>{
      println(x._1+"\t"+x._2.mkString(","))
    })
    //求平均成绩
    result1.map(x=>{
      val sourceavg: Double = x._2.sum.toDouble/x._2.toList.size
      (x._1,sourceavg)
    }).foreach(x=>println(x))
    println("----------reduceByKey-----------")
    val rdd2: RDD[(String, Int)] = rdd1.reduceByKey((x:Int, y:Int)=>x+y)
    rdd2.foreach(x=>println(x))
    println("----------sortByKey-----------")
    //总成绩降序排列
    val rdd3 = rdd2.map(x=>(x._2,x._1)).sortByKey(false).map(x=>(x._2,x._1))
    rdd3.foreach(x=>println(x))
    println("----------sortBy-----------")
    //在key-value中使用
    rdd2.sortBy((x:(String,Int))=>x._2,true).foreach(x=>println(x))
    println("----------aggregate-----------")
    val array1=Array(1,2,3,4,5,6,7,8,9,10)
    val rdd4: RDD[Int] = context.parallelize(array1)
    //求array1的和
    val sum: Int = rdd4.aggregate(0)((x:Int, y:Int)=>x+y, (x:Int, y:Int)=>x+y)
    println(sum)
    //求array1的平均数
    val result2: (Int, Int) = rdd4.aggregate(0,0)((u:(Int,Int), x:Int)=>(u._1+x,u._2+1),
      (x:(Int,Int), y:(Int,Int))=>(x._1+y._1,x._2+y._2))
    println(result2._1.toDouble/result2._2)
    println("----------fold求平均值-----------")
    //求平均值
    val result3 = rdd4.map(x=>(x,1)).fold((0,0))((x,y)=>(x._1+y._1,x._2+y._2))
    println(result3._1.toDouble/result3._2)
    val result4: (Int, Int) = array1.foldLeft((0,0))((x, y)=>(x._1+y,x._2+1))
    println(result4._1.toDouble/result4._2)
    println("----------aggregateByKey-----------")
    //求平均分
    /**
      * 参数  zeroValue: U 初始值，
      * seqOp: (U, V) => U 迭代操作rdd中的每个元素和初始值进行合并,
      * combOp: (U, U) => U 分区聚合最终聚合
      * aggregateByKey =groupByKey+aggregate 只对每一组中的value进行聚合
      * rdd1.aggregateByKey()
      */
    val rdd5: RDD[(String, (Int, Int))] = rdd1.aggregateByKey((0,0))((x:(Int,Int), y:Int)=>(x._1+y,x._2+1),
      (x:(Int,Int), y:(Int,Int))=>(x._1+y._1,x._2+y._2))
    val result5: Unit = rdd5.foreach(x => {
      val sourceAvg = x._2._1.toDouble / x._2._2
      println(x._1,sourceAvg)
    })
    println("----------combineByKey-----------")

    /**
      *
      * https://www.jianshu.com/p/b77a6294f31c
      * createCombiner: V => C,
      * mergeValue: (C, V) => C,
      * mergeCombiners: (C, C) => C
      *
      * createCombiner: V => C ，这个函数把当前的值作为参数，此时我们可以对其做些附加操作(类型转换)并把它返回 (这一步类似于初始化操作)
      * mergeValue: (C, V) => C，该函数把元素V合并到之前的元素C(createCombiner)上 (这个操作在每个分区内进行)
      * mergeCombiners: (C, C) => C，该函数把2个元素C合并 (这个操作在不同分区间进行)
      *
      */
    val rdd6: RDD[(String, (Int, Int))] = rdd1.combineByKey(x=>(x,1), (x:(Int,Int), y:Int)=>(x._1+y,x._2+1),
      (x:(Int,Int), y:(Int,Int))=>(x._1+y._1,x._2+y._2))
    val result6: Array[(String, Double)] = rdd6.map(x => {
      (x._1, x._2._1.toDouble / x._2._2)
    }).collect
    result6.foreach(x=>println(x))
    context.stop()
  }
}
