
import scala.concurrent.duration._

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._

class PostGetZone extends Simulation {

  val sentHeaders = Map ("v" -> "6.5.6", "Content-Type" -> "application/json;charset=utf-8", "os" -> "android", "os_version" -> "7.0")

	val httpProtocol = http
		.baseURL("https://mobileapi.snapdeal.com")
  
	val scn = scenario("scenario")
		.exec(http("request_0")
		.post("/service/getZone")
    .headers(sentHeaders)
    
   .body(StringBody("""
{
	"latitude": "0.0",
	"responseProtocol": "PROTOCOL_JSON",
	"longitude": "0.0",
	"pincode": "122022",
	"apiKey": "snapdeal",
	"requestProtocol": "PROTOCOL_JSON"
}
                      """)).asJSON
    
    .check(status.is(200))
)

	setUp(scn.inject(atOnceUsers(1))).protocols(httpProtocol)
}