package com.bynav.generalbe.finance.controller;


import com.bynav.generalbe.finance.dto.Account;
import com.bynav.generalbe.finance.orchestration.AccountOrchestration;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/v1/finance")
public class FinanceAccountController {

    private final AccountOrchestration accountOrchestration;

    @RequestMapping(
            value = "/account",
            produces = {"application/json"},
            consumes = { "application/json" },
            method = RequestMethod.POST)
    public ResponseEntity<Account> addAccount(@Valid @RequestBody Account account) {
        return ResponseEntity.ok(accountOrchestration.addAccount(account));
    }

    @RequestMapping(
            value = "/account/{id}",
            produces = {"application/json"},
            consumes = { "application/json" },
            method = RequestMethod.DELETE)
    public ResponseEntity<Void> removeAccount(@PathVariable String id) {
        accountOrchestration.deleteAccount(id);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(
            value = "/account/{id}",
            produces = {"application/json"},
            consumes = { "application/json" },
            method = RequestMethod.GET)
    public ResponseEntity<Account> getAccount(@PathVariable String id) {
        return ResponseEntity.ok(accountOrchestration.getAccount(id));
    }

    @RequestMapping(
            value = "/accounts",
            produces = {"application/json"},
            consumes = { "application/json" },
            method = RequestMethod.GET)
    public ResponseEntity<List<Account>> getAccounts() {
        return ResponseEntity.ok(accountOrchestration.getAccounts());
    }

    @RequestMapping(
            value = "/account",
            produces = {"application/json"},
            consumes = { "application/json" },
            method = RequestMethod.PUT)
    public ResponseEntity<Account> editAccount(@Valid @RequestBody Account account) {
        return ResponseEntity.ok(accountOrchestration.editAccount(account));
    }


}
