package com.test.evermeta.controller;

import com.test.evermeta.dto.MetadataColumnResponse;
import com.test.evermeta.dto.SearchRequest;
import com.test.evermeta.entity.MetadataColumn;
import com.test.evermeta.repository.MetadataRepository;
import com.test.evermeta.service.MetadataColumnService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.List;

@Slf4j
@Validated
@RestController
@RequestMapping("/api/v1/metadata")
public class MetadataController {

  private final MetadataColumnService service;
  private static final Logger logger = LoggerFactory.getLogger(MetadataController.class);

  public MetadataController(MetadataColumnService service) {
    this.service = service;
  }

  @PostMapping
  public ResponseEntity<MetadataColumn> addMetadata(@Valid @RequestBody MetadataColumn metadata) {
    log.info("Create metadata request");
    return ResponseEntity.ok(service.create(metadata));
  }

  @GetMapping
  public List<MetadataColumn> getAll() {
    logger.info("Fetching all metadata entries");
    return service.getAll();
  }

  @GetMapping("/{id}")
  public ResponseEntity<MetadataColumn> getById(@PathVariable @Positive Long id) {
    log.info("Get metadata by id={}", id);
    return ResponseEntity.ok(service.getById(id));
  }

  @PutMapping("/{id}")
  public ResponseEntity<MetadataColumn> update(@PathVariable @Positive Long id,
      @Valid @RequestBody MetadataColumn updated) {
    log.info("Update metadata id={}", id);
    return ResponseEntity.ok(service.update(id, updated));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable @Positive Long id) {
    log.info("Delete metadata id={}", id);
    service.delete(id);
    return ResponseEntity.noContent().build();
  }

  @PostMapping("/search")
  public Page<MetadataColumnResponse> search(@RequestBody @Valid SearchRequest req) {
    logger.info("Search request: keyword='{}', page={}, size={}", req.getKeyword(), req.getPage(), req.getSize());
    return service.search(req.getKeyword(), req.getPage(), req.getSize());
  }
}


