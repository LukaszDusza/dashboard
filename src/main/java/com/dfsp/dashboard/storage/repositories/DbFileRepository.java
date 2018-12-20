package com.dfsp.dashboard.storage.repositories;


import com.dfsp.dashboard.storage.model.DbFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DbFileRepository extends JpaRepository<DbFile, String> {


}
