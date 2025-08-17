package com.test.evermeta.repository;

import com.test.evermeta.entity.MetadataColumn;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

public interface MetadataColumnRepository extends JpaRepository<MetadataColumn, Long>, JpaSpecificationExecutor<MetadataColumn> {
  @Query("""
      SELECT m FROM MetadataColumn m
      WHERE LOWER(m.columnName)       LIKE LOWER(CONCAT('%', :kw, '%'))
         OR LOWER(m.tableName)        LIKE LOWER(CONCAT('%', :kw, '%'))
         OR LOWER(m.shortDescription) LIKE LOWER(CONCAT('%', :kw, '%'))
         OR LOWER(m.systemAlias)      LIKE LOWER(CONCAT('%', :kw, '%'))
  """)
  Page<MetadataColumn> searchByKeyword(@Param("keyword") String keyword, Pageable pageable);

}
