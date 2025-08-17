package com.test.evermeta.config;

import static com.test.evermeta.util.Utils.normalize;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "search")
public class SearchProperties {
  private Map<String, List<String>> synonyms = new HashMap<>();

  public Map<String, List<String>> getSynonyms() { return synonyms; }
  public void setSynonyms(Map<String, List<String>> synonyms) { this.synonyms = synonyms; }

  public List<String> synonymsFor(String raw) {
    if (raw == null) return List.of();
    String key = normalize(raw);
    return synonyms.getOrDefault(key, List.of());
  }

}
