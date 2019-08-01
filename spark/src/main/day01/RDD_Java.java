package day01;

import org.apache.spark.SparkConf;
import org.apache.spark.SparkContext;


/**
 * java的程序入口
 */
public class RDD_Java {
    public static void main(String[] args) {
        SparkConf sparkConf=new SparkConf();
        sparkConf.setAppName("MyPartition");
        sparkConf.setMaster("local");
        SparkContext sparkContext = new SparkContext(sparkConf);
    }
}
