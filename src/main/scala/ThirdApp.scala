package main.scala

import org.apache.spark.SparkContext 
import org.apache.spark.SparkContext._
import org.apache.spark.SparkConf 
 
object ThirdApp extends App{
    val logFile = "abc1.txt" // Should be some file on your system 
    val conf = new SparkConf().setAppName("Simple Application").setMaster("local") 
    //val conf = new SparkConf().setAppName(Simple Application)
    val sc = new SparkContext(conf) 
    val logData = sc.textFile(logFile, 3)
    val numAs = logData.filter(line => line.contains("a")).count() 
    val numBs = logData.filter(line => line.contains("b")).count() 
    println("Lines with a: %s, Lines with b: %s".format(numAs, numBs
))
sc.stop()
}