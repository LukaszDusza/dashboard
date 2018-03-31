package com.dfsp.dashboard.repositories;


import com.dfsp.dashboard.entities.RaportDas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public interface RaportDasRepository extends JpaRepository<RaportDas, Long> {
}
