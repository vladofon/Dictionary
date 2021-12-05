package com.annoying.dictionary;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootTest
class DictionaryApplicationTests {

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Test
	void contextLoads() {
		assertThat(jdbcTemplate).isNotNull();
	}

	@Test
	void migrationTest() {
		List<String> tables = jdbcTemplate
				.queryForList("SELECT table_name FROM information_schema.tables WHERE table_schema='dictionary'", String.class);

		System.out.println(tables.toString());
		assertThat(tables)
				.containsAll(Arrays.asList("words", "examples", "use_cases", "synonyms", "flyway_schema_history"));
	}

}
