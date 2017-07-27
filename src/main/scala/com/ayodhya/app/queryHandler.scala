package com.ayodhya.app

import mySQL._
import java.sql.Connection
import java.sql.ResultSet
import java.sql.SQLException
import scala.util.control.Exception._
import org.json4s.JsonDSL._


object queryHandler {

    def getArtists:List[String] = 
        callQuery[List[String]]("Select strArtists From songview Group by strArtists") {
            resultSet =>{
                new Iterator[String] {
                    def hasNext = resultSet.next
                    def next = resultSet.getString(1)
                }.toList 
            }               
        }{err => List(handleError(err))}

    def getSongByArtist(artist:String):List[(String,String)] = 
        callQuery[List[(String,String)]](s"Select strTitle, strArtists From song Where strArtists Like '%${artist}%'"){
            resultSet => {
                new Iterator[(String,String)] {
                    def hasNext = resultSet.next
                    def next = (resultSet.getString(1),resultSet.getString(2))
                }.toList      
            }
        }{err => List(("Error",handleError(err)))}

    def callQuery[A](queryString:String)(handler:ResultSet=>A)(errorHandler:Throwable=>A):A = 
        execQuery(connectionLendee(queryString)) match {
            case Left(ex) => errorHandler(ex)
            case Right(resultSet) => handler(resultSet)
        }
    

    def connectionLendee(queryString:String): Connection => Either[Throwable, ResultSet] = 
        conn => {
            val statement = conn.createStatement
            catching(classOf[SQLException]) either {
                statement executeQuery queryString
            }
        }

    def handleError(err:Throwable):String = 
        err match {
            case ex:SQLException => s"SQLException: ${ex.getMessage} \n SQLState: ${ex.getSQLState} \n VendorError: ${ex.getErrorCode}"
            case ex => s"Exception: ${ex.getMessage}"
        }
}