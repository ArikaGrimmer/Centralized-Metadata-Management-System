package com.test.evermeta.controller;

import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.test.evermeta.entity.MetadataColumn;
import com.test.evermeta.repository.MetadataRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/metadata")
public class MetadataController {

  private final MetadataRepository repository;

  private static final Logger logger = LoggerFactory.getLogger(MetadataController.class);

  public MetadataController(MetadataRepository repository) {
    this.repository = repository;
  }

  @PostMapping
  public MetadataColumn addMetadata(@RequestBody MetadataColumn metadata) {
    logger.info("Creating metadata: {}", metadata);
    return repository.save(metadata);
  }

  @GetMapping("/{id}")
  public List<MetadataColumn> getAll() {
    logger.info("Fetching all metadata entries");
    return repository.findAll();
  }

  @GetMapping("/{id}")
  public ResponseEntity<MetadataColumn> getById(@PathVariable Long id) {
    logger.info("Fetching metadata with ID: {}", id);
    Optional<MetadataColumn> optionalMetadata = repository.findById(id);
    if (optionalMetadata.isPresent()) {
      MetadataColumn metadata = optionalMetadata.get();
      logger.info("Found metadata: {}", metadata);
      return ResponseEntity.ok(metadata);
    } else {
      logger.warn("Metadata with ID {} not found", id);
      return ResponseEntity.notFound().build();
    }
  }

  @PutMapping("/{id}")
  public ResponseEntity<MetadataColumn> update(@PathVariable Long id, @RequestBody MetadataColumn updated) {
    logger.info("Received update request for ID: {}", id);
    Optional<MetadataColumn> optionalMetadata = repository.findById(id);
    if (optionalMetadata.isPresent()) {
      updated.setId(id);
      MetadataColumn saved = repository.save(updated);
      logger.info("Updated metadata with ID {} successfully.", id);
      return ResponseEntity.ok(saved);
    } else {
      logger.warn("Metadata with ID {} not found for update.", id);
      return ResponseEntity.notFound().build();
    }
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable Long id) {
    logger.info("Received delete request for ID: {}", id);
    if (!repository.existsById(id)) {
      logger.warn("Metadata with ID {} not found for deletion.", id);
      return ResponseEntity.notFound().build();
    }
    repository.deleteById(id);
    logger.info("Deleted metadata with ID {} successfully.", id);
    return ResponseEntity.noContent().build();
  }

}
