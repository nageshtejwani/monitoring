# Sample monitoring app(Spring Boot)

#### Application contains dependnecy of Promethehus and Grafana.
#### It registers sample metrics with Spring boot application 
#### and we can change the value of metrics using  REST API endpoints
#### and same can be visible in Grafana/Prometheus


## Build
####  docker compose build

## Run 

#### docker compose run 



#### Sample Metrics 
 
############ TYPE requests_total counter
requests_total{application="monitoring-demo",} 5.0

############ TYPE requests_active gauge
requests_active{application="monitoring-demo",} 3.0

#### Rest Endpoints 

GET http://localhost:8089/myappmetrics/add  //adding the counter

GET http://localhost:8089/myappmetrics/remove //subtracting the active request count

#### Actuator Endpoint 
http://localhost:8089/actuator/prometheus

#### Public Docker Image
docker pull nageshtejwani/monitoring 

