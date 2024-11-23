package com.bynav.generalbe.document.repository;

import com.bynav.generalbe.document.dto.Document;
import com.bynav.generalbe.document.dto.DocumentList;
import com.bynav.generalbe.document.dto.Folder;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DocumentRepository extends CrudRepository<Document, Integer> {

    @Query(value = "SELECT *  FROM documents WHERE folder_id=?1", nativeQuery=true)
    List<Document> getDocumentList(Integer id);
    @Query(value = "SELECT *  FROM documents WHERE folder_id=0", nativeQuery=true)
    List<Document> getDocumentListParentZero();
}
