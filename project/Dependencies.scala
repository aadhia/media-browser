import sbt._

object Dependencies {
    lazy val mysqlJDBC      = "mysql" % "mysql-connector-java" % "5.1.16"
    lazy val json           = "org.scalatra" %% "scalatra-json" % "2.5.1"
    lazy val jackson        = "org.json4s" %% "json4s-jackson" % "3.5.2"
    lazy val scalike        = "org.scalikejdbc" %% "scalikejdbc" % "2.5.2"
    lazy val h2             = "com.h2database"  %  "h2" % "1.4.196"

}