package main.scala

import org.apache.spark.streaming._
import org.apache.spark.sql.SparkSession
//import org.apache.spark.SparkContext
//import org.apache.spark.SparkContext._
//import org.apache.spark.SparkConf
 
object FourthApp extends App{
 
val spark =  SparkSession.builder()
            .appName("StreamingApp-1")
            .master("local[2]")
            .config("spark.executor.instances",2)
            .config("spark.driver.memory","2g")
            .getOrCreate()
val ssc = new StreamingContext(spark.sparkContext, Seconds(5))
//setting up receiver
val streamRDD = ssc.socketTextStream("localhost",2222)
val wordcounts = streamRDD.flatMap(line => line.toLowerCase().split(" ")).map(word => (word,1))
                  .reduceByKey((x,y) => x+y)
wordcounts.print()
ssc.start()
ssc.awaitTermination()
}