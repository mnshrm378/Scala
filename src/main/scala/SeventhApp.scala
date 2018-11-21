package main.scala

import org.apache.spark.SparkContext 
import org.apache.spark.SparkContext._ 
import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql
import org.apache.spark.sql.types._
import org.apache.spark.sql.Row
import scala.collection.mutable.ArrayBuffer
import scala.io.Source

object SeventhApp extends App {
val conf = new SparkConf().setAppName("HelloSpark").setMaster("local")
//.set("spark.debug.maxToStringFields","100")

val sc = new SparkContext(conf)
val fileInfoFile_with_Marked_Data = args(0)
val x = sc.textFile(fileInfoFile_with_Marked_Data)
val sample1 = x
val newDS = sample1
.zipWithIndex()
.map{case(line,i)=>i.toString + "," + line}

val n = newDS.filter(line => line.contains("countryCode")).take(1).flatMap(line => line.split(","))
val f = sc.parallelize(n).first().toInt
for(a <- 1 to f){
 newDS.take(a).foreach(println)}

println("To generate json")

sc.stop()
}