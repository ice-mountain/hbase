package cn.tedu.scala.day02

object Demo_List {
  def main(args: Array[String]): Unit = {
    //头元素和尾列表
    var list=3::Nil
    println(list)
    list=4::3::Nil
    println(list)
    //把元素放前面
    val list2=1::list
    println(list2)
    //所加的元素放在后面
    val list3=list2:+4
    println(list3)
    val empty = list3.isEmpty
    println(empty)
    //取集合的除了最后一个元素的其他元素
    println(list3.init)
    //取出集合的头元素
    println(list3.head)
    //取出集合的尾队列
    println(list3.tail)
    //取出集合的最后一个元素
    println(list3.last)
    //丢弃一个元素
    println(list3.drop(1))
    //取前面两个元素
    println(list3.take(2))
    val list4=List("a","b","c")
    //生成元组Tuple
    val list5: List[(Int, String)] = list3 zip(list4)
    println(list5)
    //生成map
    val map = list5.toMap
    println(map)
    println(map(1))
    //集合转换成数组
    val array = list3.toArray
    println(array)
  }
}
