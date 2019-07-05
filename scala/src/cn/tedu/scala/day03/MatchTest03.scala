package cn.tedu.scala.day03

object MatchTest03 {
  def main(args: Array[String]): Unit = {
    val  list=List(3,1,5)
    list match {
      //case 3::1::5::Nil=>println("猜中了")
      case 3::3::Nil=>println("猜错了")
      case x::y::z::Nil=>println(s"x:$x,y:$y,z:$z")
      case x::tail=>println(tail)
      case _=>println("不猜了")
    }
  }
}
