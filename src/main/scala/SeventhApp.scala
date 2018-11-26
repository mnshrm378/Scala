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

val newDS = sample1.zipWithIndex().map{case(line,i)=>i.toString + "," + line}

val n = newDS.filter(line => line.contains("countryCode")).take(1).flatMap(line => line.split(","))
val f = sc.parallelize(n).first().toInt

newDS.take(f).slice(3,12).foreach(println)
sample1.take(f).slice(3,f).foreach(println)
val pairD = newDS.take(f).map(line => (line.split(","))).map(fields => (fields(1),fields(2)))

println("to convert to Json this file contains data for: ")
print("[")
val test = pairD.map(x => x._1).mkString(" , ").foreach(print)
print("]\n")

val n1 = Source.fromFile("C:\\Users\\alay.singhal\\Desktop\\Codes\\After_markings_SMPC522A1DC0040_SE-PGA454_20171012_082108_FileInfo.txt").getLines.slice(0,12)
n1.slice(3,12).foreach(println)
//print("to convert to Json")

//.map(fields => (fields(0),fields(1)))
//val getJsonFor = newDS.filter(line => line.startsWith("3,")).map(line => line.split(",")).map(fields => (fields(2),fields(3)))
//val k = getJsonFor.keys.take(1).foreach(println)
//val v = getJsonFor.values.take(1).foreach(println)

sc.stop()
}