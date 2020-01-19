var express = require("express");
var app = express();
app.listen(3000, () => {
  console.log("Server running on port 3000");
});

var responseFrom;

var http = require("https");

app.get("/:origin/:destination/:date", (req, res, next) => {
  var origin = [req.params.origin];
  var destination = [req.params.destination];
  var date = [req.params.date];
  var unirest = require("unirest");

  var req = unirest(
    "GET",
    "https://skyscanner-skyscanner-flight-search-v1.p.rapidapi.com/apiservices/browsequotes/v1.0/US/INR/en-US/" +
      origin +
      "/" +
      destination +
      "/" +
      date
  );

  req.headers({
    "x-rapidapi-host": "skyscanner-skyscanner-flight-search-v1.p.rapidapi.com",
    "x-rapidapi-key": "3ca473d7d8msh05da92b5f33d66bp14d5dejsn2cbb731181c0"
  });

  req.end(function(response) {
    if (response.error) throw new Error(response.error);
    console.log(response.body);
    if (response.body.Quotes.length == 0) {
      res.send("0");
    } else {
      res.send(response.body.Quotes[0].MinPrice.toString());
    }

    // res.json(responseFrom);

    // console.log(response.body.Quotes[0].MinPrice);
  });

  //   var options = {
  //     method: "GET",
  //     hostname: "skyscanner-skyscanner-flight-search-v1.p.rapidapi.com",
  //     port: null,
  //     path:
  //       "/apiservices/browsequotes/v1.0/US/INR/en-US/" +
  //       origin +
  //       "/" +
  //       destination +
  //       "/" +
  //       date,
  //     headers: {
  //       "x-rapidapi-host":
  //         "skyscanner-skyscanner-flight-search-v1.p.rapidapi.com",
  //       "x-rapidapi-key": "3ca473d7d8msh05da92b5f33d66bp14d5dejsn2cbb731181c0"
  //     }
  //   };
  //   var req = http.request(options, function(res) {
  //     var chunks = [];
  //     res.on("data", function(chunk) {
  //       chunks.push(chunk);
  //     });
  //     res.on("end", function() {
  //       var body = Buffer.concat(chunks);
  //       responseFrom = body.toString();
  //       console.log(JSON.stringify(body.toString()));
  //     });
  //   });
  //   req.end();
  //   res.json(responseFrom);
});
