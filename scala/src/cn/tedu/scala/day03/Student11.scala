package cn.tedu.scala.day03
/**
  * 在定义一个scala的类的构成中，如果这个类的后面没有跟（）我们认为这个类有一个主构造器但是（）也可以省略
  * 定义有参构造   class  student11(var id:Int,var name:String)这个都是主构造器
  * 如果在定义一个属性使用var 或者val修饰则是属于public 的
  * 如果没有var或者val修饰的则是属于private[this]的
  *
  */
class Student11 (var id:Int,var name:String,age:Int,private val scalary
: Int) {

  println("hello scala")
  //这句代码就是把这个属性使用了
  def myPrint=println(age)
}
/**
  * 禁止主构造器的使用 private (var id:Int,var name:String)主构造器也可以被private和priavte[this]修饰
  * @param id
  * @param name
  */
class Student22 private[this] (var id:Int,var name:String){
  /**
    * 定义辅助构造器
    * 1.方法的名称必须是this
    * 2.必须使用def修饰
    * 3.辅助构造器一般来说都包含主构造器的参数
    * 4.辅助构造器的第一句代码一般的都是以其他的构造器开头的
    */
  def this(id:Int,name:String,age:Int)={
    //第一句使用的主构造器
    this(id,name)
  }
  def this(id:Int,name:String,age:Int,scala:String)={
    //第一句使用了辅助构造器
    this(id,name,age)
  }


}
object Student11{
  def main(args: Array[String]): Unit = {
    ()
    val student11: Student11 = new  Student11(9527,"唐伯虎",18,5200)
    println(student11.id)
    println(student11.name)
    println(student11.scalary)
    val student2: Student22 = new Student22(9594,"爽",18)
  }
}
