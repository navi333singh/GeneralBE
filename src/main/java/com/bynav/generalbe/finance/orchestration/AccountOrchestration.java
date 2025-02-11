package com.bynav.generalbe.finance.orchestration;

import com.bynav.generalbe.exception.error.GeneralErrorEnum;
import com.bynav.generalbe.finance.repository.AccountRepository;
import com.bynav.generalbe.finance.dto.Account;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Getter
@RequiredArgsConstructor
@Slf4j
public class AccountOrchestration {

    final AccountRepository repositoryRes;
    final AuditorAware<String> auditorAware;

    public Account addAccount(Account account) {
        try {
            return repositoryRes.save(account);
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

    public Account getAccount(String id) {
        try {
            Optional<Account> account = repositoryRes.findByAccountId(id);
            if(account.isPresent()) {
                return account.get();
            } else {
                throw GeneralErrorEnum.ENTRY_NOT_FOUND.buildGeneralException();
            }
        }catch(Exception e) {
            log.error("saving error: %",e);
            throw GeneralErrorEnum.SERVICE_UNAVAILABLE.buildGeneralException();
        }
    }

    public List<Account> getAccounts() {
        try {
            String userId = auditorAware.getCurrentAuditor().orElse(null);
            if(userId == null) {
                throw GeneralErrorEnum.ENTRY_NOT_FOUND.buildGeneralException();
            }
            Optional<List<Account>> accounts = repositoryRes.findByUserId(userId);
            if(accounts.isPresent()) {
                return accounts.get();
            } else {
                throw GeneralErrorEnum.ENTRY_NOT_FOUND.buildGeneralException();
            }
        }catch(Exception e) {
            log.error("saving error: %",e);
            throw GeneralErrorEnum.SERVICE_UNAVAILABLE.buildGeneralException();
        }
    }

    public Account editAccount(Account account) {
        try {
            Optional<Account> response = repositoryRes.findByAccountId(account.getAccountId());
            if(response.isPresent()) {
                Account oldAccount = response.get();
                oldAccount.setName(account.getName());
                oldAccount.setDescription(account.getDescription());
                oldAccount.setBank_name(account.getBank_name());
                oldAccount.setType(account.getType());
                return repositoryRes.save(oldAccount);
            } else {
                throw GeneralErrorEnum.ENTRY_NOT_FOUND.buildGeneralException();
            }
        } catch(Exception e) {
            log.error("saving error: %",e);
            throw GeneralErrorEnum.SERVICE_UNAVAILABLE.buildGeneralException();
        }
    }
}
