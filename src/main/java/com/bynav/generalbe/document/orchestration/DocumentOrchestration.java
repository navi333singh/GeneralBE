package com.bynav.generalbe.document.orchestration;

import com.bynav.generalbe.document.dto.*;
import com.bynav.generalbe.document.file.ImageUtils;
import com.bynav.generalbe.document.repository.DocumentRepository;
import com.bynav.generalbe.document.repository.FolderRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Getter
@AllArgsConstructor
@Slf4j
public class DocumentOrchestration {

    DocumentRepository repositoryRes;
    FolderRepository folderRepository;

    public Structure getAllDocumentsName() {
        try {
            Structure structure = new Structure();
            List<Folder> folders = folderRepository.getFolderParentZero();
            List<DocumentsStructure> DocStructure = new ArrayList<>();
            repositoryRes.getDocumentListParentZero().forEach(doc -> DocStructure.add(DocumentsStructure.builder().name(doc.getTitle()).id(doc.getId()).type("file").build()));
            folders.forEach( value -> {
                DocumentsStructure documentsStructures =  DocumentsStructure.builder().name(value.getName()).id(value.getId()).type("folder").build();
                documentsStructures.setFileStructure(getData(value.getId()));
                DocStructure.add(documentsStructures);
            });
            structure.setStructure(DocStructure);
            return structure;
        } catch (Exception e) {
            log.error("error findAll: %",e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private  List<DocumentsStructure> getData(int folder_id)
    {
        List<DocumentsStructure> documentsStructures = new ArrayList<>();
        repositoryRes.getDocumentList(folder_id).forEach(doc -> documentsStructures.add(DocumentsStructure.builder().name(doc.getTitle()).id(doc.getId()).type("file").build()));
        List<Folder> folders = folderRepository.getFolder(folder_id);
        if(!folders.isEmpty()) {
            folders.forEach(value -> {
                DocumentsStructure doc = DocumentsStructure.builder().name(value.getName()).id(value.getId()).type("folder").build();
                doc.setFileStructure(getData(value.getId()));
                documentsStructures.add(doc);
            });
        }
        return documentsStructures;
    }
    public DocumentResponse getDocumentByID(int id) {
        try {
            Optional<Document> response = repositoryRes.findById(id);
            if (response.isEmpty()) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND);
            }
            byte[] images = ImageUtils.decompressImage(response.get().getData());
            return DocumentResponse.builder().document(images).contentType(response.get().getType()).build();
        } catch (Exception e) {
            log.error("error findById: %",e);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

    }

    public void addDocument(DocumentRequest document) {
        try {
            MultipartFile file =  document.getFile();
            repositoryRes.save(Document.builder()
                    .title(file.getOriginalFilename())
                    .data(ImageUtils.compressImage(file.getBytes()))
                    .desc(document.getDesc())
                    .type(file.getContentType())
                    .date_last_modi(LocalDateTime.now().toString())
                    .build());
        }catch(Exception e) {
            log.error("saving error: %",e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public void deleteDocument(int id) {
        try {
            repositoryRes.deleteById(id);
        }catch(Exception e) {
            log.error("saving error: %",e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
