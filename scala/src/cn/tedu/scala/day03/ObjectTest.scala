package cn.tedu.scala.day03

/**
  * 定义类一般把伴生类和伴生对象放在一个文件中
  * 如果属相和方法定义在object的组件中则可以通过类名来访问
  * 伴生类和伴生对象可以相互访问对方的私有属性
  */
object ObjectTest {
  def main(args: Array[String]): Unit = {
    //实例对象
    val ot22: ObjectTest22 = new ObjectTest22()
    println(ot22.id)
    println(ot22.m1(3))
    //静态的属性和方法
    println(ObjectTest11.id1)
    println(ObjectTest11.m11(4))
    //object修饰的类是单利对象
    val ot11: ObjectTest11.type = ObjectTest11
    val ot12: ObjectTest11.type = ObjectTest11
    //在scala中== 默认比较的是内容 但前提是重写equals,就是按照equals比较的，eq和ne是比较对象的引用
    println(ot11==ot12)
    println(ot11 eq ot12)
    println(ot11 ne ot12)
    println(ot11.equals(ot12))
  }
}
object ObjectTest11{
  val id1:Int=11
  def m11(x:Int)=x+1
}
class  ObjectTest22{
  val id:Int=11
  def m1(x:Int)=x+1
}