package com.ayodhya.app
import org.scalatra._
import org.json4s.{DefaultFormats, Formats}
import org.scalatra.json._
import queryHandler._

class MyScalatraServlet extends ServerMysqlApiStack with JacksonJsonSupport {

  protected implicit val jsonFormats: Formats = DefaultFormats

  before() {
    contentType = formats("json")
  }

  get("/") {
    val headers = Map("Access-Control-Allow-Origin" -> "*",
                      "Access-Control-Allow-Methods" -> "POST, GET, OPTIONS, DELETE",
                      "Access-Control-Max-Age" -> "3600",
                      "Access-Control-Allow-Headers" -> "x-requested-with, content-type")
    Ok(getArtists,headers)
  }

  get("/song/:artist") {
    getSongByArtist(params("artist"))
  }

}
