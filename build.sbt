name := "SimpleProject"
 
version := "1.1.0"
 
scalaVersion := "2.11.8"
 
val sparkVersion = "2.2.1"
 
resolvers ++= Seq(
    "apache-snapshots" at "http://repository.apache.org/snapshots/"
)
 
libraryDependencies ++= Seq(
    "org.apache.spark" %% "spark-core" % sparkVersion,
    "org.apache.spark" %% "spark-sql" % sparkVersion,
    "org.apache.spark" %% "spark-mllib" % sparkVersion,
    "org.apache.spark" %% "spark-streaming" % sparkVersion,
    "org.apache.spark" %% "spark-hive" % sparkVersion
)