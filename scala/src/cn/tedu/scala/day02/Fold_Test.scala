package cn.tedu.scala.day02

object Fold_Test {
  def main(args: Array[String]): Unit = {
    var array:Array[Int]=Array(1,2,3,4,5,6,7,8,9)
    //第一个括号z是初始值 第二个括号是聚合逻辑
    val result1: Int = array.fold(z = 0)(op=(x:Int, y:Int)=>x+y)
    val result2: Int = array.foldLeft(z = 0)(op=(x:Int, y:Int)=>{println(x,y);x+y})
    println("-----------------------")
    val result3: Int = array.foldRight(z = 0)(op=(x:Int, y:Int)=>{println(x,y);x+y})
    println(result1)
    println(result2)
    println(result3)
  }
}
