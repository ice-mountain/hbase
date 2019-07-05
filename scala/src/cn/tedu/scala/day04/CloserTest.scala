package cn.tedu.scala

/**
  * 如何实现闭包？
  * 就是在这个bibao 函数的内部，在定义一个函数add_sum去访问这个sum函数
  *
  */
object CloserTest {
  def main(args: Array[String]): Unit = {
    val result = bibao
    result(2)
    result(2)
    result(2)
    result(2)
    val result1=bibao
    result(3)//11 函数都是单利的
  }
  val bibao = {
    var sum = 0
    var add_sum = (x: Int) => {
      sum += x
      println(sum)
    }
    add_sum
  }
}
