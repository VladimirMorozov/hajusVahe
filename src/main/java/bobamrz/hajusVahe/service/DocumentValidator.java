package bobamrz.hajusVahe.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.Map.Entry;

import bobamrz.hajusVahe.dto.DocumentArraySaveResult;
import bobamrz.hajusVahe.dto.DocumentArraySaveValidationResult;
import bobamrz.hajusVahe.dto.DocumentSaveRequest;
import bobamrz.hajusVahe.dto.DocumentSaveResult;
import bobamrz.hajusVahe.dto.ValidatedDocumentSaveRequest;
import bobamrz.hajusVahe.exceptions.CatalogMaxDocumentCountExceededException;
import bobamrz.hajusVahe.generated.Catalog;
import bobamrz.hajusVahe.generated.CatalogHome;
import bobamrz.hajusVahe.generated.DocumentHome;

public class DocumentValidator {
	//private DocumentHome documentHome;
	private CatalogHome catalogHome;
	
	public static final int CATALOG_MAX_DOCUMENT_COUNT = 10;

	public DocumentValidator() {
	}
	
	/**
	 * Documents are filtered by validity. In case of global error exception is thrown.
	 * @param documents
	 * @throws CatalogMaxDocumentCountExceededException
	 */
	public DocumentArraySaveValidationResult validateArraySaveRequest(List<DocumentSaveRequest> documents) {
		
		DocumentArraySaveValidationResult result = new DocumentArraySaveValidationResult();
		List<ValidatedDocumentSaveRequest> validatedDocumentSaveRequests = new ArrayList<ValidatedDocumentSaveRequest>();
		boolean globalValidationHasFailed = false;
		boolean documentValidationHasFailed = false;
		
		
		//validate global
		Map<String, Integer> catalogDocumentCountIncrements = new TreeMap<String, Integer>();
		for (DocumentSaveRequest document : documents) {
			ValidatedDocumentSaveRequest validatedRequest = new ValidatedDocumentSaveRequest();
			validatedRequest.documentSaveRequest = document;
			
			//not a new catalog:
			if (document.getCatalogId() != null) {
				Catalog catalog = catalogHome.findById(document.getCatalogId());
				
				//check that id corresponds to name
				if (!catalog.getName().equals(document.getCatalogName())) {
					validatedRequest.validationResult = DocumentSaveResult.FAIL_CATALOG_ID_AND_NAME_DONT_MATCH;
					globalValidationHasFailed = true;
					documentValidationHasFailed = true;
				} else {
					validatedRequest.validationResult = DocumentSaveResult.SUCCESS;
					incrementCatalogDocumentIncrementValue(catalogDocumentCountIncrements, document);
				}
			} else {
				//check that name doesn't exist
				Catalog exampleCatalog = new Catalog();
				exampleCatalog.setName(document.getCatalogName());
				List<Catalog> catalogs = (List<Catalog>)catalogHome.findByExample(exampleCatalog);
				if (catalogs.size() != 0) {
					validatedRequest.validationResult = DocumentSaveResult.FAIL_CATALOG_WITH_SPECIFIED_NAME_ALREADY_EXISTS;
					globalValidationHasFailed = true;
					documentValidationHasFailed = true;
				} else {
					validatedRequest.validationResult = DocumentSaveResult.SUCCESS;
					incrementCatalogDocumentIncrementValue(catalogDocumentCountIncrements, document);
				}
				
				
			}
			
			validatedDocumentSaveRequests.add(validatedRequest);
		}
		
		if (documentValidationHasFailed) {
			result.setResult(DocumentArraySaveResult.FAIL_DOCUMENT_ERROR);
		} else {
			List<String> catalogsWithExceededMaxDocumentCount = new ArrayList<String>();
			Set<Entry<String, Integer>> catalogDocumentCountIncrementsSet = catalogDocumentCountIncrements.entrySet();
			for (Entry<String, Integer> entry : catalogDocumentCountIncrementsSet) {
				Catalog catalogExample = new Catalog();
				catalogExample.setName(entry.getKey());
				int documentsInCatalogCount = catalogHome.findByExample(catalogExample).size();
				int projectedCount = documentsInCatalogCount + entry.getValue();
				if (projectedCount > CATALOG_MAX_DOCUMENT_COUNT) {
					catalogsWithExceededMaxDocumentCount.add(entry.getKey());
				}
				
			}
			
			if (catalogsWithExceededMaxDocumentCount.size() > 0) {
				globalValidationHasFailed = true;
				result.setResult(DocumentArraySaveResult.FAIL_DOCUMENT_COUNT_EXCEEDS_CATALOG_MAX);
				result.setCatalogsWithExceededDocumentCount(catalogsWithExceededMaxDocumentCount);
			} else {
				result.setResult(DocumentArraySaveResult.SUCCESS);
			}
		}
		
		
		
		if (globalValidationHasFailed) {
			for (ValidatedDocumentSaveRequest validatedDocument : validatedDocumentSaveRequests) {
				if (validatedDocument.validationResult.equals(DocumentSaveResult.SUCCESS)) {
					validatedDocument.validationResult = DocumentSaveResult.FAIL_GLOBAL_VALIDATION;
				}
				
			}
		}
		
		result.setValidatedRequests(validatedDocumentSaveRequests);
		return result;
	}
	
	private void incrementCatalogDocumentIncrementValue(
			Map<String, Integer> catalogDocumentCountIncrements,
			DocumentSaveRequest document) {
		Integer catalogDocumentCountIncrement = catalogDocumentCountIncrements.get(document.getCatalogName());
		if (catalogDocumentCountIncrement == null) {
			catalogDocumentCountIncrement = 0;
		}
		catalogDocumentCountIncrements.put(document.getCatalogName(), catalogDocumentCountIncrement++);
	}

	/*public DocumentHome getDocumentHome() {
		return documentHome;
	}

	public void setDocumentHome(DocumentHome documentHome) {
		this.documentHome = documentHome;
	}*/

	public CatalogHome getCatalogHome() {
		return catalogHome;
	}

	public void setCatalogHome(CatalogHome catalogHome) {
		this.catalogHome = catalogHome;
	}
}