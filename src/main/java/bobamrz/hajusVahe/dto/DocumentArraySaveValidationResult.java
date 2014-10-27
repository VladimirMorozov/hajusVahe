package bobamrz.hajusVahe.dto;

import java.util.List;

public class DocumentArraySaveValidationResult {
	
	private List<ValidatedDocumentSaveRequest> validatedRequests;
	private DocumentArraySaveResult result;
	
	private List<String> catalogsWithExceededDocumentCount;

	public List<ValidatedDocumentSaveRequest> getValidatedRequests() {
		return validatedRequests;
	}

	public void setValidatedRequests(List<ValidatedDocumentSaveRequest> validatedRequests) {
		this.validatedRequests = validatedRequests;
	}

	public DocumentArraySaveResult getResult() {
		return result;
	}

	public void setResult(DocumentArraySaveResult result) {
		this.result = result;
	}

	public List<String> getCatalogsWithExceededDocumentCount() {
		return catalogsWithExceededDocumentCount;
	}

	public void setCatalogsWithExceededDocumentCount(
			List<String> catalogsWithExceededDocumentCount) {
		this.catalogsWithExceededDocumentCount = catalogsWithExceededDocumentCount;
	}

}
