package cn.tedu.scala.day03

object TypeCheck {
  def main(args: Array[String]): Unit = {
    val people: People = new People()
    val student: Student = new Student()
    println(people.isInstanceOf[People])//true
    println(classOf[People])//People.class
    println(student.asInstanceOf[People])//是否可以强转
  }
}
class People{

}
class Student extends  People{

}
