package bobamrz.hajusVahe.dto;

import java.util.ArrayList;
import java.util.List;

public class DocumentArraySaveResponse {
	
	
	private DocumentArraySaveResult result;
	
	private List<DocumentSaveResponse> documents;
	private List<String> catalogNamesWithExceededDocumentCount;
	
	public DocumentArraySaveResponse(DocumentArraySaveValidationResult validationResult) {
		if (validationResult.getResult() == DocumentArraySaveResult.SUCCESS) {
			throw new RuntimeException("response construction from validation result is only allowed for failed results");
		}
		
		this.result = validationResult.getResult();
		this.documents = new ArrayList<DocumentSaveResponse>();
		this.catalogNamesWithExceededDocumentCount = validationResult.getCatalogsWithExceededDocumentCount();
		for (ValidatedDocumentSaveRequest validatedDocument : validationResult.getValidatedRequests()) {
			DocumentSaveResponse documentSaveResponse = new DocumentSaveResponse(
					validatedDocument.documentSaveRequest, 
					validatedDocument.validationResult);
			
			this.documents.add(documentSaveResponse);
		}
	}
	
	public DocumentArraySaveResponse() {
		// TODO Auto-generated constructor stub
	}

	public DocumentArraySaveResult getResult() {
		return result;
	}
	public void setResult(DocumentArraySaveResult result) {
		this.result = result;
	}
	public List<DocumentSaveResponse> getDocuments() {
		return documents;
	}
	public void setDocuments(List<DocumentSaveResponse> documents) {
		this.documents = documents;
	}
	public List<String> getCatalogNamesWithExceededDocumentCount() {
		return catalogNamesWithExceededDocumentCount;
	}
	public void setCatalogNamesWithExceededDocumentCount(
			List<String> catalogNamesWithExceededDocumentCount) {
		this.catalogNamesWithExceededDocumentCount = catalogNamesWithExceededDocumentCount;
	}

	@Override
	public String toString() {
		return "DocumentArraySaveResponse [result=" + result + ", documents="
				+ documents + ", catalogNamesWithExceededDocumentCount="
				+ catalogNamesWithExceededDocumentCount + "]";
	}
	
	

}
