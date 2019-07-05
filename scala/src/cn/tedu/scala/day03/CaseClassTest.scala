package cn.tedu.scala.day03

/**
  *  样例类重写了Apply方法和toString方法equals和copy
  */
case class CaseClassTest(val id:Int) {

}
//case object 样例对象单利模式
object CaseClassTest{
  def main(args: Array[String]): Unit = {
    val test1: CaseClassTest = new CaseClassTest(11)
    val test2 = CaseClassTest(11)
    println(test2.id)
  }
}