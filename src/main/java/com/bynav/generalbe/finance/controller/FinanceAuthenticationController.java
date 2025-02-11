package com.bynav.generalbe.finance.controller;


import com.bynav.generalbe.document.dto.DocumentRequest;
import com.bynav.generalbe.finance.dto.Account;
import com.bynav.generalbe.finance.orchestration.AccountOrchestration;
import com.bynav.generalbe.one_password.dto.OnePassword;
import com.bynav.generalbe.one_password.dto.OnePasswordResponse;
import com.bynav.generalbe.one_password.orchestration.OnePasswordOrchestration;
import com.bynav.generalbe.security.auth.AuthenticationRequest;
import com.bynav.generalbe.security.auth.AuthenticationResponse;
import com.bynav.generalbe.security.auth.AuthenticationService;
import com.bynav.generalbe.security.auth.RegisterRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/v1/finance")
public class FinanceAuthenticationController {

    private final AccountOrchestration accountOrchestration;


    @RequestMapping(
            value = "/account",
            produces = {"application/json"},
            consumes = { "application/json" },
            method = RequestMethod.POST)
    public ResponseEntity<Void> addAccount(@Valid @RequestBody Account account) {
        accountOrchestration.addAccount(account);
        return ResponseEntity.noContent().build();
    }



}
