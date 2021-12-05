package com.annoying.dictionary.models;

public class Example extends LongEntity {
	public static final String EXAMPLE_ID = "example_id";
	public static final String EXAMPLE_SIGNATURE = "example_id";

	private String signature;

	public Example(Long id, String signature) {
		super(id);
		this.signature = signature;
	}

	public Example(String signature) {
		this(null, signature);
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((signature == null) ? 0 : signature.hashCode());
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
		Example other = (Example) obj;
		if (signature == null) {
			if (other.signature != null)
				return false;
		} else if (!signature.equals(other.signature))
			return false;
		return true;
	}

}
