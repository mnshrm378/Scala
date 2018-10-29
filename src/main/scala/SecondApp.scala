package main.scala
import org.apache.spark.SparkContext 
import org.apache.spark.SparkContext._
import org.apache.spark.SparkConf 
 
object SecondApp extends App{
val x = "abc1.txt"
val conf = new SparkConf().setAppName("HelloSpark").setMaster("local")
val sc = new SparkContext(conf)
val y = sc.textFile(x, 2).cache()
val counts = y.flatMap(line => line.split(" "))
                 .map(word => (word, 1))
                 .reduceByKey(_ + _)
//counts.saveAsTextFile("hdfs://u1:9000/mydataabco/sparkoutpt" + java.util.UUID.randomUUID.toString)
counts.take(10).foreach(println)
sc.stop()
}