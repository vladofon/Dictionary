CREATE TABLE IF NOT EXISTS dictionary.synonyms
(
	current_word_id INTEGER NOT NULL,
	synonym_id INTEGER NOT NULL,
	CONSTRAINT synonym_current_word_fkey
		FOREIGN KEY (current_word_id)
			REFERENCES dictionary.words(word_id),
	CONSTRAINT synonym_synonym_fkey
		FOREIGN KEY (synonym_id)
			REFERENCES dictionary.words(word_id)
);