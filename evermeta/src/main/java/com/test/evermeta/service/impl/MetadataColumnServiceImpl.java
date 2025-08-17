package com.test.evermeta.service.impl;

import com.test.evermeta.dto.MetadataColumnResponse;
import com.test.evermeta.entity.MetadataColumn;
import com.test.evermeta.repository.MetadataColumnRepository;
import com.test.evermeta.service.MetadataColumnService;
import com.test.evermeta.util.Utils;
import com.test.evermeta.config.SearchProperties;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.*;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class MetadataColumnServiceImpl implements MetadataColumnService {
  private final MetadataColumnRepository repository;
  private final SearchProperties searchProps;

  public MetadataColumnServiceImpl(MetadataColumnRepository repo, SearchProperties searchProps) {
    this.repository = repo;
    this.searchProps = searchProps;
  }

  @Override
  public MetadataColumn create(MetadataColumn toCreate) {
    log.debug("Saving new metadata (columnName={})", toCreate.getColumnName());
    return repository.save(toCreate);
  }

  @Override
  public List<MetadataColumn> getAll() {
    log.debug("Fetching all metadata");
    return repository.findAll();
  }

  @Override
  public MetadataColumn getById(Long id) {
    Optional<MetadataColumn> opt = repository.findById(id);
    if (opt.isPresent()) {
      log.debug("Found metadata id={}", id);
      return opt.get();
    } else {
      log.warn("Metadata id={} not found", id);
      throw new IllegalArgumentException("metadata id " + id + " not found");
    }
  }

  @Override
  public MetadataColumn update(Long id, MetadataColumn updated) {
    Optional<MetadataColumn> opt = repository.findById(id);
    if (opt.isPresent()) {
      MetadataColumn existing = opt.get();
      updated.setId(existing.getId());
      log.debug("Updating metadata id={}", id);
      return repository.save(updated);
    } else {
      log.warn("Update failed: metadata id={} not found", id);
      throw new IllegalArgumentException("metadata id " + id + " not found");
    }
  }

  @Override
  public void delete(Long id) {
    boolean exists = repository.existsById(id);
    if (exists) {
      log.debug("Deleting metadata id={}", id);
      repository.deleteById(id);
    } else {
      log.warn("Delete failed: metadata id={} not found", id);
      throw new IllegalArgumentException("metadata id " + id + " not found");
    }
  }


  @Override
  public Page<MetadataColumnResponse> search(String rawKeyword, int page, int size) {
    String keyword = Utils.normalize(rawKeyword);
    PageRequest pageable = PageRequest.of(page, size, Sort.by("id").ascending());
    log.debug("Search keyword='{}' page={} size={}", keyword, page, size);

    Page<MetadataColumn> first = repository.searchByKeyword(keyword, pageable);
    if (!first.isEmpty()) {
      return first.map(this::toDto);
    }

    List<String> synonyms = searchProps.synonymsFor(keyword);
    for (String alt : synonyms) {
      Page<MetadataColumn> altPage = repository.searchByKeyword(alt, pageable);
      if (!altPage.isEmpty()) {
        log.debug("Search hit via synonym='{}'", alt);
        return altPage.map(this::toDto);
      }
    }
    return Page.empty(pageable);
  }


  private MetadataColumnResponse toDto(MetadataColumn m) {
    MetadataColumnResponse dto = new MetadataColumnResponse();
    dto.setId(m.getId());
    dto.setColumnName(m.getColumnName());
    dto.setTableName(m.getTableName());
    dto.setSchemaName(m.getSchemaName());
    dto.setDomainName(m.getDomainName());
    dto.setSubDomainName(m.getSubDomainName());
    dto.setSystemAlias(m.getSystemAlias());
    dto.setShortDescription(m.getShortDescription());
    dto.setLongDescription(m.getLongDescription());
    return dto;
  }
}
