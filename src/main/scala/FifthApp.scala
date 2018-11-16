package main.scala

import org.apache.spark.SparkContext 
import org.apache.spark.SparkContext._ 
import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql
import org.apache.spark.sql.types._
import org.apache.spark.sql.Row

object FifthApp extends App {
val conf = new SparkConf().setAppName("HelloSpark").setMaster("local").set("spark.debug.maxToStringFields","true")
//.set("spark.sql.warehouse.dir","C:\\Users\\alay.singhal\\eclipse-workspace\\ScalaApps\\spark-warehouse")
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
//pairz.saveAsTextFile("C:\\Users\\alay.singhal\\Desktop\\Codes\\tmp2")
//val spark = SparkSession.builder.master("local").appName("Spark_SQL_basic_example").getOrCreate()
// you can add this too --> .config("spark.some.config.option", "some-value")

//val df = spark.read.format("csv").option("inferSchema","true").load("C:\\Users\\alay.singhal\\Desktop\\Codes\\tmp2\\part-00000")
//df.write.format("json").save("C:\\Users\\alay.singhal\\Desktop\\Codes\\JsonConvertedFI3")
//pairz.sortByKey().take(10).foreach(println)
pairz.sortByKey(true).saveAsTextFile("C:\\Users\\alay.singhal\\Desktop\\Codes\\tmp2")

// you can add this too --> .config("spark.some.config.option", "some-value")
val x1 = sc.textFile("C:\\Users\\alay.singhal\\Desktop\\Codes\\tmp2\\part-00000").filter(line => line.contains("pedestrian"))
x1.saveAsTextFile("C:\\Users\\alay.singhal\\Desktop\\Codes\\tmp3")
val spark = SparkSession.builder.master("local").appName("Spark_SQL_basic_example").getOrCreate()

//val df = spark.read.format("csv").option("inferSchema","true").load("C:\\Users\\alay.singhal\\Desktop\\Codes\\tmp2\\part-00000")
//df.show(10)
//val test1 = pairz.values.filter(line => line.startsWith("22,"))
//val y = test1.flatMap(line => (line.split(",")))
//test1.flatMap(line => (line.split(","))).take(54).mkString(", ").foreach(print)
//val temp = test1.flatMap(line => (line.split(","))).take(54).mkString(", ")
val schemaString = "frameNumber,streamName,refId,objectType,height,direction,movement,occlusion,headOccluded,feetOccluded,overlapped,unsharp,strangePose,crossing,accessory,topLeftX,topLeftY,topRightX,topRightY,bottomRightX,bottomRightY,bottomLeftX,bottomLeftY,box3DGroundLength,box3DGroundWidth,box3DGroundCenterX,box3DGroundCenterXSigma,box3DGroundCenterY,box3DGroundCenterYSigma,box3DClosestPointX,box3DClosestPointY,box3DOrientationAngle,box3DOrientationAngleSigma,box3DHeight,box3DRelVelocityX,box3DRelVelocityXSigma,box3DRelVelocityY,box3DRelVelocityYSigma,box3DDataSource,box3DLidarInterpolationAge,box3DClassificationQuality,lidarDistanceX,lidarDistanceY,lidarVelocityX,lidarVelocityY,isInvalid,isStatic,ObjectId,Ibeo2MarkingsVersion,IdcOdExtractorVersion,clusterID,faceVisible,leftBorderVisibility,rightBorderVisibility"
val fields = schemaString.split(",").map(fieldName => StructField(fieldName, StringType, nullable = false))
val schema = StructType(fields)

// Convert records of the RDD (people) to Rows
val rowRDD = x1.map(_.split(",")).map(attributes => Row(attributes(0), attributes(1),attributes(2), attributes(3),attributes(4), attributes(5),attributes(6), attributes(7),attributes(8), attributes(9),attributes(10), attributes(11),attributes(12), attributes(13),attributes(14), attributes(15),attributes(16), attributes(17),attributes(18), attributes(19),attributes(20), attributes(21),
    attributes(22), attributes(23),attributes(24), attributes(25),attributes(26), attributes(27),attributes(28), attributes(29),attributes(30), 
    attributes(31),attributes(32), attributes(33),attributes(34), attributes(35),attributes(36), attributes(37),attributes(38), attributes(39),
    attributes(40), attributes(41),attributes(42), attributes(43),attributes(44), attributes(45),attributes(46), attributes(47),attributes(48),
    attributes(49),attributes(50), attributes(51),attributes(52), attributes(53)))
val df = spark.createDataFrame(rowRDD, schema)
df.write.format("json").save("C:\\Users\\alay.singhal\\Desktop\\Codes\\JsonConvertedFI3")

//df.na.replace("colName", Map("" -> "UNKNOWN")).show()
//df.createOrReplaceTempView("markeddata")
//spark.sql("select * from markeddata where _c5 = 'pedestrian' order by _c0 asc").show(10)
//spark.sql("select count(*) from markeddata where _c5 = 'pedestrian' ").show()
//spark.sql("select * from markeddata ").write.format("json").save("C:\\Users\\alay.singhal\\Desktop\\Codes\\JsonConvertedFI3")
//pairz.sortByKey().take(10).foreach(println)
sc.stop()
}