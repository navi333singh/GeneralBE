package com.bynav.generalbe.one_password.controller;

import com.bynav.generalbe.one_password.dto.OnePassword;
import com.bynav.generalbe.one_password.dto.OnePasswordResponse;
import com.bynav.generalbe.one_password.orchestration.OnePasswordOrchestration;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/generalbe/OnePassword")
public class OnePasswordController {

    private final OnePasswordOrchestration listOrchestration;

    @RequestMapping(
            value = "/all",
            produces = {"application/json"},
    method = RequestMethod.GET)
    public Mono<ResponseEntity<OnePasswordResponse>> allPasswords() {
        OnePasswordResponse resp = listOrchestration.getAllPassword();
        return Mono.just(ResponseEntity.ok().body(resp)).doOnSuccess(result -> log.info("successfully get column"));
    }

    @RequestMapping(
            value = "/{id}",
            produces = {"application/json"},
            method = RequestMethod.GET)
    public Mono<ResponseEntity<OnePasswordResponse>> getPassword(@PathVariable int id) {
        OnePasswordResponse resp = listOrchestration.getPasswordByID(id);
        return Mono.just(ResponseEntity.ok().body(resp)).doOnSuccess(result -> log.info("successfully get column"));
    }

    @RequestMapping(
            value = "/add",
            produces = {"application/json"},
            consumes = { "application/json" },
            method = RequestMethod.POST)
    public Mono<ResponseEntity<Object>> addPassword(@RequestBody @Validated OnePassword onePassword) {
        listOrchestration.addPassword(onePassword);
        return Mono.just(ResponseEntity.noContent().build());
    }

    @RequestMapping(
            value = "/delete/{id}",
            produces = {"application/json"},
            consumes = { "application/json" },
            method = RequestMethod.DELETE)
    public Mono<ResponseEntity<Object>> deletePassword(@PathVariable int id) {
        listOrchestration.deletePassword(id);
        return Mono.just(ResponseEntity.noContent().build());
    }
}
