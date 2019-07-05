package cn.tedu.scala.day01

object Demo_Method {
  def main(args: Array[String]): Unit = {
    val result:Int = sum(2)
    println(result)
    var result1=sum2(1,3)
    println(result1)
    //方法转换为函数
    var sum3=sum _
    println(sum3(1))
    var sum1=(x:Int)=>{x+1}
    print(sum1(4))
  }
  def sum(x:Int):Int=x+1
  def sum2(x:Int,y:Int):Int=x+y

}
