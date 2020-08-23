package main

import (
	"log"
	"net/http"
	"encoding/json"
	"math/rand"
	"math"

	"github.com/gorilla/mux"
)

var router = mux.NewRouter().StrictSlash(true)
var minCelciusValue = 0.0
var maxCelciusValue = 38.0

type WeatherResponse struct {
    City string
    CelciusValue float64
    When string
}

func CreateResponse(city string, when string) WeatherResponse {
    random := minCelciusValue + rand.Float64() * (maxCelciusValue - minCelciusValue)
    weather := WeatherResponse{city, math.Floor(random*100)/100, when}
    log.Printf("action=%#v, city=%#v, temperature=\"%.2f °C\", when=%#v", CreateResponse, city, random, when)
    return weather
}

func weather(w http.ResponseWriter, r *http.Request) {
    w.Header().Set("Content-Type", "application/json")
    w.WriteHeader(http.StatusOK)
    var city = mux.Vars(r)["city"]
    var when = mux.Vars(r)["when"]

    json.NewEncoder(w).Encode(CreateResponse(city, when))
}

func main() {
	router.HandleFunc("/weather/{city}/{when}", weather)
	log.Print("Weather Mock Service API Started")
	log.Fatal(http.ListenAndServe(":8080", router))
}