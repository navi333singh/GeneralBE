package com.bynav.generalbe.finance.controller;


import com.bynav.generalbe.document.dto.DocumentRequest;
import com.bynav.generalbe.security.auth.AuthenticationRequest;
import com.bynav.generalbe.security.auth.AuthenticationResponse;
import com.bynav.generalbe.security.auth.AuthenticationService;
import com.bynav.generalbe.security.auth.RegisterRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/v1/finance/")
public class FinanceAuthenticationController {


    @RequestMapping(
            value = "/add",
            produces = {"*/*"},
            consumes = { "multipart/form-data" },
            method = RequestMethod.POST)
    public Mono<ResponseEntity<Object>> addDocument(@RequestParam("name") String newName, @RequestParam("desc") String desc, @RequestParam("file") MultipartFile file ) {
        documentOrchestration.addDocument(DocumentRequest.builder()
                .newName(newName)
                .desc(desc)
                .file(file)
                .build());
        return Mono.just(ResponseEntity.noContent().build());
    }


}
