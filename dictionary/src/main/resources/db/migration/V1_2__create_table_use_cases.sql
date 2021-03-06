CREATE TABLE IF NOT EXISTS dictionary.use_cases
(
	word_id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 2147483647 CACHE 1 ),
	example_id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 2147483647 CACHE 1 ),
	CONSTRAINT use_cases_word_fkey 
		FOREIGN KEY (word_id)
			REFERENCES dictionary.words(word_id),
	CONSTRAINT use_cases_example_fkey 
		FOREIGN KEY (example_id)
			REFERENCES dictionary.examples(example_id)
);