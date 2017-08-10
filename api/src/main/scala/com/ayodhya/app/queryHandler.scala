package com.ayodhya.app

import scalikejdbc._
import org.json4s.JsonDSL._
import org.json4s._


object queryHandler {

    Class.forName("org.postgresql.Driver")
    ConnectionPool.singleton("jdbc:postgresql:pagila", "arjun", "")
    implicit val session = AutoSession

    def getArtists:List[JObject] =
        sql"Select distinct * From actor"
            .map(rs => ("id" -> rs.string("actor_id")) ~ ("first" -> rs.string("first_name")) ~ ("last" -> rs.string("last_name")))
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