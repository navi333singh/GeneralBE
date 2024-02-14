package com.bynav.generalbe.one_password.repository;

import com.bynav.generalbe.one_password.dto.OnePassword;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OnePasswordRepository extends CrudRepository<OnePassword, Integer> {

//    @Query(value = "SELECT * FROM one_password WHERE password_id=?1", nativeQuery=true)
//    Collection<OnePassword> getPasswordByID(int id);


}
