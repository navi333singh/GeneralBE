package com.bynav.generalbe.document.repository;

import com.bynav.generalbe.document.dto.Folder;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FolderRepository extends CrudRepository<Folder, Integer> {

    @Query(value = "SELECT folder_id, name, type, parent_id FROM folder WHERE parent_id=0", nativeQuery=true)
    List<Folder> getFolderParentZero();

    @Query(value = "SELECT folder_id, name, type, parent_id FROM folder WHERE parent_id=?1", nativeQuery=true)
    List<Folder> getFolder(Integer id);
}
