package com.sample.monitoring.monitoring.controller;


import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.MeterRegistry;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/myappmetrics")
public class SampleController {

    Logger LOG = LoggerFactory.getLogger(SampleController.class);
    private final Counter counter;
    private final Gauge gauge;
    private Double activeRequests = 0.0;


    public SampleController(MeterRegistry meterRegistry) {
        this.counter = Counter.builder("requests_total")
                .description("Total number of HTTP requests")
                .register(meterRegistry);
        this.gauge = Gauge.builder("requests_active", this, SampleController::getActiveRequests)
                .description("Number of active HTTP requests")
                .register(meterRegistry);
    }

    private double getActiveRequests() {
            return activeRequests;
    }

    @GetMapping("/add")
    public void  add() {
        LOG.info("Incrementing the counter");
        counter.increment();// increment the counter
        activeRequests = activeRequests + 1;
    }

    @GetMapping("/remove")
    public void  remove() {
        LOG.info("Decreasing the gauge");
        counter.increment();
        activeRequests =activeRequests -1;
    }
}