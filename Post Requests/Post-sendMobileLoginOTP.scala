
import scala.concurrent.duration._

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._

class PostSendMobileLoginOTP extends Simulation {

  val sentHeaders = Map ("v" -> "6.5.6", "Content-Type" -> "application/json;charset=utf-8", "os" -> "android", "os_version" -> "7.0")

	val httpProtocol = http
		.baseURL("https://mobileapi.snapdeal.com")
  
	val scn = scenario("scenario")
		.exec(http("request_0")
		.post("/service/user/otp/v1/sendMobileLoginOTP")
    .headers(sentHeaders)
    
   .body(StringBody("""
{
	"responseProtocol": "PROTOCOL_JSON",
	"apiKey": "snapdeal",
	"mobileNumber": "9958571972",
	"requestProtocol": "PROTOCOL_JSON"
}
                      """)).asJSON
    
    .check(status.is(200))
)

	setUp(scn.inject(atOnceUsers(1))).protocols(httpProtocol)
}