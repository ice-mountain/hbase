package cn.tedu.scala.day03

/**
  * 1.scala定义属性的时候一定要进行初始化
  * 2.使用val修饰的属性具备get方法不具备set方法
  * 3.var修饰的具备get方法也具备set方法
  * 4.表示私有除了自己以外别人都不能方法但是和这个类同名的object可以访问
  * 5.在一个类中可以定义多个类而且这些类默认都属于public 如果出现了相同名称的类和object
  * 则说明这个类是半生类这个对象时伴生对象
  * 6.private[this] 只有自己可以访问伴生对象都不能访问
  * 7.一个类中除了属性之外还可以定义方法，方法的修饰和属性一样
  */
class Student {
  val id = 9527
  var name = "唐伯虎"
  //表示私有除了自己以外别人都不能方法但是和这个类同名的object可以访问
  private var salary = 40000
  private[this] var age = 40
  println("hello world")
  def sum(x:Int,y:Int):Int ={
    println(x,y)
    x+y
  }
  sum(3,4)
}
/**
  * 如果要进行代码测试必须要有main方法而且必须在object中
  */
object Student{
  def main(args: Array[String]): Unit = {
    //如果通过这个方法new对象则这个类中的所有代码都会执行
    val student = new Student()
    //student.id=788 不能更改
    student.name="唐嫣"
    println(student.name)
    println(student.salary)
  }
}