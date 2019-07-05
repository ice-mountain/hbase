package cn.tedu.scala.day01

object Demo_For {
  def main(args: Array[String]): Unit = {
    //1到10的数包含10
    var a=1 to 10;
    for(i<-a){
      print(i)
    }
    println()
    //1到10的数不包含10
    var b=1 until 10;
   for(ii<-b){
     print(ii)
   }
    println()
    //数组
    var array:Array[Int]=Array(1,2,3,4,5,6,7,8,9);
    for(a <- array){
      print(a);
    }
    println()
    //每次跨两步
    for(c <- 0 until (array.length,2)){
        print(c,array(c))
    }
    println()
    for(array <- array.reverse)print(array)
    println()
    for(c <- 0 until array.length) yield println(array(c)*2)
  }
}
