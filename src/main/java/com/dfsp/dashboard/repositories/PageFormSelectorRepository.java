package com.dfsp.dashboard.repositories;

import com.dfsp.dashboard.entities.PageFormSelector;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PageFormSelectorRepository extends JpaRepository<PageFormSelector,Long> {
}
