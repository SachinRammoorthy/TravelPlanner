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
});

app.get("monument/:coordinatex/:coordinatey/:coordinatez", (req, res, next) => {
  var coordinatex = [req.params.coordinatex];
  var coordinatey = [req.params.coordinatey];
  var coordinatez = [req.params.coordinatez];

  var unirest = require("unirest");

  var req = unirest(
    "GET",
    "https://reverse.geocoder.ls.hereapi.com/6.2/reversegeocode.json?apiKey=0Frq5aaFxs2HPzEYhJbmrH_6OsjFpkpReqG13U4IG5k&mode=retrieveLandmarks&prox=" +
      coordinatex +
      "," +
      coordinatey +
      "," +
      coordinatez
  );

  req.end(function(response) {
    if (response.error) throw new Error(response.error);
    console.log(response.body);

    // res.json(responseFrom);

    // console.log(response.body.Quotes[0].MinPrice);
  });
});
