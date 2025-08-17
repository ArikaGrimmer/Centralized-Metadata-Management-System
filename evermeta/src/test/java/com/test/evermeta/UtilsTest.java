package com.test.evermeta;

import com.test.evermeta.util.Utils;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Utils.normalize")
class UtilsTest {

  @Test
  @DisplayName("returns empty string for null")
  void returnsEmptyForNull() {
    assertThat(Utils.normalize(null)).isEqualTo("");
  }


  @Test
  @DisplayName("trims and lowercases")
  void trimsAndLowercases() {
    assertThat(Utils.normalize("  AccouNT_Number  ")).isEqualTo("account_number");
  }

  @Test
  @DisplayName("removes spaces and punctuation")
  void removesSpacesAndPunctuation() {
    assertThat(Utils.normalize("-acc ount!!!"))
        .isEqualTo("account");
  }

}
