package bobamrz.hajusVahe.dto;

import java.util.List;

public class DocumentArraySaveRequest {

	private List<DocumentSaveRequest> documentSaveRequest;

	public List<DocumentSaveRequest> getDocumentSaveRequest() {
		return documentSaveRequest;
	}

	public void setDocumentSaveRequest(List<DocumentSaveRequest> documentSaveRequest) {
		this.documentSaveRequest = documentSaveRequest;
	}
	
}
