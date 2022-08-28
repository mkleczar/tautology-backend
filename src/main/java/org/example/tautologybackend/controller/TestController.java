package org.example.tautologybackend.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.tautology.openapi.api.TestApi;
import org.example.tautology.openapi.model.EchoResponse;
import org.example.tautology.openapi.model.TestResponse;
import org.example.tautology.openapi.model.TimeResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.time.OffsetDateTime;

@Slf4j
@RestController
public class TestController implements TestApi {

    @Override
    public ResponseEntity<TestResponse> test() {
        log.info("Call test controller method");
        TestResponse response = new TestResponse().message("to jest test");
        log.info("Call test controller response is: {}", response);
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<EchoResponse> echo(String text) {
        log.info("Call echo controller method with param: {}", text);
        EchoResponse response = new EchoResponse().echo(text);
        log.info("Call echo controller response is: {}", response);
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<TimeResponse> time() {
        log.info("Call time controller method");
        TimeResponse response = new TimeResponse().time(OffsetDateTime.now());
        log.info("Call time controller response is: {}", response);
        return ResponseEntity.ok(response);
    }
}
