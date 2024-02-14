package com.bynav.generalbe.one_password.orchestration;

import com.bynav.generalbe.one_password.dto.OnePassword;
import com.bynav.generalbe.one_password.dto.OnePasswordResponse;
import com.bynav.generalbe.one_password.repository.OnePasswordRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Optional;

@Service
@Getter
@RequiredArgsConstructor
@Slf4j
public class OnePasswordOrchestration {

    @Autowired
    OnePasswordRepository repositoryRes;

    public OnePasswordResponse getAllPassword() {
        try {
            Iterable<OnePassword> response = repositoryRes.findAll();
            ArrayList<OnePassword> onePasswordList = new ArrayList<>();
            response.forEach(onePasswordList::add);
            return OnePasswordResponse.builder().OnePasswordList(onePasswordList).build();
        } catch (Exception e) {
            log.error("error findAll: %",e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public OnePasswordResponse getPasswordByID(int id) {
        try {
            Optional<OnePassword> response = repositoryRes.findById(id);
            ArrayList<OnePassword> onePasswordList = new ArrayList<>();
            response.ifPresent(onePasswordList::add);
            return OnePasswordResponse.builder().OnePasswordList(onePasswordList).build();
        } catch (Exception e)
        {
            log.error("error findById: %",e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    public void addPassword(OnePassword password) {
        try {
            repositoryRes.save(password);
        }catch(Exception e) {
            log.error("saving error: %",e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public void deletePassword(int id) {
        try {
            repositoryRes.deleteById(id);
        }catch(Exception e) {
            log.error("saving error: %",e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
