package com.ayodhya.app

import scalikejdbc._

object queryHandler {

    Class.forName("com.mysql.jdbc.Driver")
    ConnectionPool.singleton("jdbc:mysql://ayodhya.adhia.net:13306/MyMusic56", "xbmc", "xbmc")
    implicit val session = AutoSession

    def getArtists:List[String] = 
        sql"Select strArtists From songview Group by strArtists".map(rs => rs.string("strArtists")).list.apply()

    def getSongByArtist(artist:String):List[(String,String)] = {
        sql"Select strTitle, strArtists From song Where strArtists Like ${'%' + artist + '%'}"
            .map(rs => (rs.string("strTitle"),rs.string("strArtists")) ).list.apply()
    }
}