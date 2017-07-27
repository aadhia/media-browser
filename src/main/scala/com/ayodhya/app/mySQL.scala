package com.ayodhya.app

import java.sql.Connection
import java.sql.DriverManager
import java.sql.SQLException
import java.sql.ResultSet
import scala.util.control.Exception._

object mySQL {

    val driver = "com.mysql.jdbc.Driver"
    val url = "jdbc:mysql://ayodhya.adhia.net:13306/MyMusic56"
    val user = "xbmc"
    val password = "xbmc"

    Class.forName(driver)

    def execQuery( f:Connection=>Either[Throwable,ResultSet]):Either[Throwable,ResultSet] = {
        
        val connection = catching(classOf[SQLException]) either {
            DriverManager.getConnection(url,user,password);
        }
        
        connection match {
            case Right(conn) => f(conn)            
            case Left(ex) => Left(ex)
        }
    }
}
