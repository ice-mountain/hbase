package cn.tedu.scala.day03

object ApplyTest {
  def main(args: Array[String]): Unit = {
    //def apply(x : scala.Int, xs : scala.Int*)调用apply方法
    val array1: Array[Int] = Array(1,2,3,4,5,6,7,8,9)
    //调用构造函数
    val array2: Array[Int] =new Array[Int](10)
    println(array1.toBuffer)
    println(array2.toBuffer)
    val studentTest44: StudentTest44 = StudentTest44(11,"唐伯虎")

  }
}

/**
  *主构造器被private修饰之后只能在伴生对象中使用
  * @param id
  * @param name
  */
class StudentTest44 private (val id:Int,val name:String){

}
object StudentTest44{
  //该方法可以重载
  def apply(id:Int,name:String): StudentTest44 ={
    println("调用了Apply方法")
    new StudentTest44(id,name)
  }
}

