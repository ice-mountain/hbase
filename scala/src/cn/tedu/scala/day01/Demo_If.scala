package cn.tedu.scala.day01

object Demo_If {
  def main(args: Array[String]): Unit = {
    val aa=2;
    if(aa>0){
      println(">")
    }else{
      println("<")
    }
    if (aa>0)println(1)else println(-1);
    println("-----------------------------------");
    var x=2;
    var y=3;
    var result:Int={if(x>y)println(x)else println(y);x-y};
    println(result);
    println("------------------------------------");
    var result1={if(x>y) println(x) else println(y)}
    println(result1);
    println("------------------------------------")
    var abc=if(aa<0)true;
    println(abc);

  }

}
