package cn.tedu.scala.day04

/**
  *函数和方法可以当做是函数和方法的参数
  */
object Method_Function {
  def main(args: Array[String]): Unit = {
    println(m2(1,2))
    println(f1(1))
    //参数传入函数
    println(mm1(5,6,f2))
    //参数传入方法如果这个参数是函数但是传入的是方法那么这个方法会被转换为函数
    println(mm1(5,6,m2 _))
    println("------------------")
    println(mm2(5,6,m2 _))
  }
  //方法
  def m2(x:Int,y:Int):Int=x+y
  //函数
  val  f1:Int=>Double=(value:Int)=>value.toDouble
  val  f2=(x:Int,y:Int)=>x+y
  val f3=m2 _
  def mm1(x:Int,y:Int,ff:(Int,Int)=>Int)=ff(x,y)
  val mm2=(x:Int,y:Int,ff:(Int,Int)=>Int)=>ff(x,y)
}
