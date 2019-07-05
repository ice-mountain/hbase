package cn.tedu.scala

/**
  * 偏函数
  */
object PartialFunctionTest {
  def main(args: Array[String]): Unit = {
    val result:String=ff(1)
    println(result)
  }
  //偏函数
  val ff:PartialFunction[Int,String]={
  case 1=>"aa"
  case _=>"bb"
  }
}
