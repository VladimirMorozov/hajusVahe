package bobamrz.hajusVahe.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.Map.Entry;

import bobamrz.hajusVahe.dto.DocumentArraySaveResponse;
import bobamrz.hajusVahe.dto.DocumentArraySaveResult;
import bobamrz.hajusVahe.dto.DocumentArraySaveValidationResult;
import bobamrz.hajusVahe.dto.DocumentSaveRequest;
import bobamrz.hajusVahe.dto.DocumentSaveResponse;
import bobamrz.hajusVahe.dto.DocumentSaveResult;
import bobamrz.hajusVahe.dto.ValidatedDocumentSaveRequest;
import bobamrz.hajusVahe.exceptions.CatalogMaxDocumentCountExceededException;
import bobamrz.hajusVahe.generated.Catalog;
import bobamrz.hajusVahe.generated.CatalogHome;
import bobamrz.hajusVahe.generated.Document;
import bobamrz.hajusVahe.generated.DocumentHome;

public class DocumentService {
	
	
	
	private DocumentValidator documentValidator;
	private CatalogHome catalogHome;
	private DocumentHome documentHome;

	public DocumentArraySaveResponse saveDocuments(List<DocumentSaveRequest> saveRequest) {
		
		DocumentArraySaveValidationResult validationResult = documentValidator.validateArraySaveRequest(saveRequest);

		DocumentArraySaveResponse response;
		
		if (validationResult.getResult() == DocumentArraySaveResult.SUCCESS) {
			response = new DocumentArraySaveResponse();
			List<DocumentSaveResponse> documentSaveResponses = new ArrayList<DocumentSaveResponse>();
			
			for (DocumentSaveRequest documentSaveRequest : saveRequest) {
				Document savedDocument = saveDocument(documentSaveRequest);
				DocumentSaveResponse documentSaveResponse = new DocumentSaveResponse(savedDocument);
				documentSaveResponses.add(documentSaveResponse);
			}
			
			response.setResult(DocumentArraySaveResult.SUCCESS);
			response.setDocuments(documentSaveResponses);
			
		} else {
			response = new DocumentArraySaveResponse(validationResult);
		}
		return response;
	}
	
	public Document saveDocument(DocumentSaveRequest document) {	
		Catalog catalog;
		if(document.getCatalogId() == null) {
			catalog = new Catalog();
			catalog.setCreatedDate(new Date());
			catalog.setDocumentCount(1);
			catalog.setName(document.getCatalogName());
			catalogHome.attachDirty(catalog);
			//attach
		} else {
			catalog = catalogHome.findById(document.getCatalogId());
			catalog.setCreatedDate(new Date());
			catalog.setDocumentCount(1);
			catalog.setName(document.getCatalogName());
			catalogHome.attachDirty(catalog);
		}
		
		Document doc = new Document();
		doc.setCatalog(catalog);
		doc.setCreatedDate(new Date());
		doc.setDesription(document.getDesription());
		doc.setName(document.getName());
		documentHome.attachDirty(doc);
		return doc;
	}

	public DocumentValidator getDocumentValidator() {
		return documentValidator;
	}

	public void setDocumentValidator(DocumentValidator validator) {
		this.documentValidator = validator;
	}

	public CatalogHome getCatalogHome() {
		return catalogHome;
	}

	public void setCatalogHome(CatalogHome catalogHome) {
		this.catalogHome = catalogHome;
	}

	public DocumentHome getDocumentHome() {
		return documentHome;
	}

	public void setDocumentHome(DocumentHome documentHome) {
		this.documentHome = documentHome;
	}
	
	
	
	

}
