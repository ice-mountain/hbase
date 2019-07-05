package cn.tedu.scala.day04

/**
  * 函数和方法可以当做是函数和方法的返回值
  */
object Method2_Function {
  def main(args: Array[String]): Unit = {
    //方法转为函数
    val f2=m1 _
    //函数转为方法
    def m2=f1
    println(f2(5,6))
    println(m2(5,6))
    println("------------------")
    //f3是一个方法
    def  f3=m3(4)
    println(f3)
    val result1=f3(5)
    println(result1)
    val result2=m3(4)(5)
    println(result2)
  }
  //方法
  def m1(x:Int,y:Int):Int=x+y
  //函数
  val  f1=(x:Int,y:Int)=>x+y
  def m3(x:Int)=(y:Int)=>x+y
}
