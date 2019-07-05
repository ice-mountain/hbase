package cn.tedu.scala.day01

object HelloWorld {
  def main(args: Array[String]): Unit = {
    //第一种变量的声明可以指定类型
    var a:Int=1;
    //改变值可以改变为同一类型不能改变为不同的类型
    a=2;
    println(a);
    //可以自动推断出你所定义的数据类型
    var b="hello"
    println(b);
    //val修饰的为常量不能随意改变
    val aa="helloWorld";
    println(aa)
    //lazy修饰的变量为延迟加载定义的时候并没有创建只有在使用的时候才会加载
    lazy val bb=1;
    println(bb);

  }
}
