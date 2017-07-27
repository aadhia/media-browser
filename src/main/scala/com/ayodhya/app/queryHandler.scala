package com.ayodhya.app

import scalikejdbc._
import org.json4s.JsonDSL._
import org.json4s._


object queryHandler {

    Class.forName("com.mysql.jdbc.Driver")
    ConnectionPool.singleton("jdbc:mysql://ayodhya.adhia.net:13306/MyMusic56", "xbmc", "xbmc")
    implicit val session = AutoSession

    def getArtists:List[String] = 
        sql"Select strArtists From songview Group by strArtists"
            .map(rs => rs.string("strArtists"))
            .list
            .apply()

    def getSongByArtist(artistQuery:String):List[JObject] = {
        val artist = s"%$artistQuery%"
        sql"Select strTitle, S.strArtists, strAlbum From song S, album A Where S.idAlbum = A.idAlbum and S.strArtists Like ${artist}"
            .map(rs => ("title"->rs.string("strTitle")) ~ ("artist"->rs.string("strArtists")) ~ ("album"->rs.string("strAlbum")) )
            .list
            .apply()
    }
}