package com.annoying.dictionary.models;

public class Word extends LongEntity {
	public static final String WORD_ID = "word_id";
	public static final String WORD_SIGNATURE = "signature";
	public static final String WORD_TRANSLATION = "translation";
	public static final String WORD_TRANSCRIPTION = "transcription";

	private String signature;
	private String translation;
	private String transcription;

	public Word(Long id, String signature, String translation, String transcription) {
		super(id);
		this.signature = signature;
		this.translation = translation;
		this.transcription = transcription;
	}

	public Word(String signature, String translation, String transcription) {
		this(null, signature, translation, transcription);
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	public String getTranslation() {
		return translation;
	}

	public void setTranslation(String translation) {
		this.translation = translation;
	}

	public String getTranscription() {
		return transcription;
	}

	public void setTranscription(String transcription) {
		this.transcription = transcription;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((signature == null) ? 0 : signature.hashCode());
		result = prime * result + ((transcription == null) ? 0 : transcription.hashCode());
		result = prime * result + ((translation == null) ? 0 : translation.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Word other = (Word) obj;
		if (signature == null) {
			if (other.signature != null)
				return false;
		} else if (!signature.equals(other.signature))
			return false;
		if (transcription == null) {
			if (other.transcription != null)
				return false;
		} else if (!transcription.equals(other.transcription))
			return false;
		if (translation == null) {
			if (other.translation != null)
				return false;
		} else if (!translation.equals(other.translation))
			return false;
		return true;
	}

}
