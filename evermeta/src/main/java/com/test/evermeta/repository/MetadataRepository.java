package com.test.evermeta.repository;

import com.test.evermeta.entity.MetadataColumn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface MetadataRepository extends JpaRepository<MetadataColumn, Long> {

}
