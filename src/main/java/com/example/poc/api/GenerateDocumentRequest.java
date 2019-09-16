package com.example.poc.api;

public class GenerateDocumentRequest {

	public enum DocumentTypeEnum {

		PDF("PDF"), CSV("CSV");

		private String value;

		DocumentTypeEnum(String value) {
			this.value = value;
		}
	}
	
	private DocumentTypeEnum documentType = null;

	public DocumentTypeEnum getDocumentType() {
		return documentType;
	}

	public void setDocumentType(DocumentTypeEnum documentType) {
		this.documentType = documentType;
	}
	

}
