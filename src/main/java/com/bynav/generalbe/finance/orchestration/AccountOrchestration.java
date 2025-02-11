package com.bynav.generalbe.finance.orchestration;

import com.bynav.generalbe.exception.error.GeneralErrorEnum;
import com.bynav.generalbe.finance.repository.AccountRepository;
import com.bynav.generalbe.finance.dto.Account;
import com.bynav.generalbe.security.auditing.ApplicationAuditAware;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Getter
@RequiredArgsConstructor
@Slf4j
public class AccountOrchestration {

    final AccountRepository repositoryRes;

    public void addAccount(Account account) {
        try {
            repositoryRes.save(account);
        }catch(Exception e) {
            log.error("saving error: %",e);
            throw GeneralErrorEnum.SERVICE_UNAVAILABLE.buildGeneralException();
        }
    }

    public void deleteAccount(String id) {
        try {
            repositoryRes.deleteById(id);
        }catch(Exception e) {
            log.error("saving error: %",e);
            throw GeneralErrorEnum.SERVICE_UNAVAILABLE.buildGeneralException();
        }
    }
}
