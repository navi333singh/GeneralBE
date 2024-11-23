package com.bynav.generalbe.document.controller;

import com.bynav.generalbe.document.dto.DocumentListResponse;
import com.bynav.generalbe.document.dto.DocumentRequest;
import com.bynav.generalbe.document.dto.DocumentResponse;
import com.bynav.generalbe.document.dto.Structure;
import com.bynav.generalbe.document.orchestration.DocumentOrchestration;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/generalbe/document")
public class DocumentController {

    private final DocumentOrchestration documentOrchestration;

    @RequestMapping(
            value = "/structure",
            produces = {"application/json"},
    method = RequestMethod.GET)
    public Mono<ResponseEntity<Structure>> allDocumentsName() {
        Structure resp = documentOrchestration.getAllDocumentsName();
        return Mono.just(ResponseEntity.ok().body(resp)).doOnSuccess(result -> log.info("successfully get column"));
    }

    @RequestMapping(
            value = "/{id}",
            produces = {"*/*"},
            method = RequestMethod.GET)
    public Mono<ResponseEntity<byte[]>> getDocument(@PathVariable int id) {
        DocumentResponse resp = documentOrchestration.getDocumentByID(id);
        return Mono.just(ResponseEntity.ok().contentType(MediaType.valueOf(resp.getContentType())).body(resp.getDocument())).doOnSuccess(result -> log.info("successfully get column"));
    }

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

    @RequestMapping(
            value = "/{id}",
            method = RequestMethod.DELETE)
    public Mono<ResponseEntity<Object>> deleteDocument(@PathVariable int id) {
        documentOrchestration.deleteDocument(id);
        return Mono.just(ResponseEntity.noContent().build());
    }
}
