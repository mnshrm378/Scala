package main.scala

import org.apache.spark.SparkContext 
import org.apache.spark.SparkContext._ 
import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql
import org.apache.spark.sql.types._
import org.apache.spark.sql.Row
import scala.collection.mutable.ArrayBuffer

object SeventhApp extends App {
val conf = new SparkConf().setAppName("HelloSpark").setMaster("local")
//.set("spark.debug.maxToStringFields","100")

val sc = new SparkContext(conf)
val fileInfoFile_with_Marked_Data = args(0)
val x = sc.textFile(fileInfoFile_with_Marked_Data)
val sample1 = x
val newDS = sample1.zipWithIndex().map{case(line,i)=>i.toString + "," + line}
val pairx = newDS.map(x => (x.split(",")(0),x))
val fileInfoFile_without_Marked_Data = args(1)

val y = sc.textFile(fileInfoFile_without_Marked_Data)
val sample2 = y
val newDS2 = sample2.zipWithIndex().map{case(line,i)=>i.toString + "," + line}
val pairy = newDS2.map(x => (x.split(",")(0),x))
val pairz = pairx.subtract(pairy)

println("extracting column headings as per metadata in y")
val colHeadingsRow = pairz.values.filter(line => line.startsWith("22,"))
val countCol = colHeadingsRow.flatMap(line => (line.split(",")))
val n = countCol.count().toInt
val colHeadings = colHeadingsRow.flatMap(line => (line.split(","))).take(n)
.mkString(", ")
//.foreach(print)
//val columns = new ArrayBuffer[String]()
val a = sc.parallelize(Array(colHeadings))
println("again")
a.map(_.toUpperCase()).foreach(arg => println(arg))

sc.stop()
}