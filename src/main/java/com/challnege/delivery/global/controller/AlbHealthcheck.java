package com.challnege.delivery.global.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AlbHealthcheck {

    @GetMapping("/healthcheck")
    public ResponseEntity<String> healthcheck() {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/health-check")
    public ResponseEntity<String> healthcheckForDeploy() {
        return ResponseEntity.ok("UP");
    }
}
