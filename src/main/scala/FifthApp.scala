package main.scala

import org.apache.spark.SparkContext 
import org.apache.spark.SparkContext._ 
import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql
import org.apache.spark.sql.types._
import org.apache.spark.sql.Row

object FifthApp extends App {
val conf = new SparkConf().setAppName("HelloSpark").setMaster("local")
val sc = new SparkContext(conf)
val x = sc.textFile("C:\\Users\\alay.singhal\\Desktop\\Codes\\After_markings_SMPC522A1DC0040_SE-PGA454_20171012_082108_FileInfo.txt")
val sample1 = x
val newDS = sample1.zipWithIndex().map{case(line,i)=>i.toString + "," + line}
val pairx = newDS.map(x => (x.split(",")(0),x))
val y = sc.textFile("C:\\Users\\alay.singhal\\Desktop\\Codes\\Before_Markings_Sample_FileInfo.txt")
val sample2 = y
val newDS2 = sample2.zipWithIndex().map{case(line,i)=>i.toString + "," + line}
val pairy = newDS2.map(x => (x.split(",")(0),x))
val pairz = pairx.subtract(pairy)
pairz.saveAsTextFile("C:\\Users\\alay.singhal\\Desktop\\Codes\\tmp2")
val spark = SparkSession.builder.master("local").appName("Spark_SQL_basic_example").getOrCreate()
// you can add this too --> .config("spark.some.config.option", "some-value")

val df = spark.read.format("csv").option("inferSchema","true").load("C:\\Users\\alay.singhal\\Desktop\\Codes\\tmp2\\part-00000")
df.write.format("json").save("C:\\Users\\alay.singhal\\Desktop\\Codes\\JsonConvertedFI3")
}