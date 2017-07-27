package com.ayodhya.app

import org.scalatra._
import org.json4s.{DefaultFormats, Formats}
import org.scalatra.json._
import org.json4s.JsonDSL._
import queryHandler._

class MyScalatraServlet extends ServerMysqlApiStack  with JacksonJsonSupport{

  protected implicit val jsonFormats: Formats = DefaultFormats
  
  before() {
    contentType = formats("json")
  }

  get("/") {
    getArtists
  }

  get("/song/:artist") {
    getSongByArtist(params("artist"))
  }

}
