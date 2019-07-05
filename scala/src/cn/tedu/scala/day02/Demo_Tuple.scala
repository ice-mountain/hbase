package cn.tedu.scala.day02

/**
  * 元组的相关练习
  */
object Demo_Tuple {
  def main(args: Array[String]): Unit = {
    val t,(a,b,c,d,e)=("aa",1,5.0,true,false)
    println(t)
    //取第一个元素
    println(a)
    println(t._1)
//    for (a<- 1 to 9){
//      for(b<- 1 to a){
//        print(a+"*"+b+"="+a*b+"\t")
//      }
//      println()
//    }
    println("---------------------------")
    for (a<- 1 to 9;b<- 1 to a){print(a+"*"+b+"="+a*b+"\t");if(a==b)println()}
  }
}
