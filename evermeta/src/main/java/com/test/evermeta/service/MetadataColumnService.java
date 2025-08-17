package com.test.evermeta.service;

import com.test.evermeta.entity.MetadataColumn;
import com.test.evermeta.dto.MetadataColumnResponse;
import org.springframework.data.domain.Page;

import java.util.List;

public interface MetadataColumnService {
  MetadataColumn create(MetadataColumn toCreate);
  List<MetadataColumn> getAll();
  MetadataColumn getById(Long id);                 // throws if not found
  MetadataColumn update(Long id, MetadataColumn updated); // throws if not found
  void delete(Long id);
  Page<MetadataColumnResponse> search(String rawKeyword, int page, int size);
}
