package bobamrz.hajusVahe;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

import javax.jws.WebService;
import javax.jws.soap.SOAPBinding.ParameterStyle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.CannotCreateTransactionException;

import bobamrz.hajusVahe.dto.DocumentArraySaveRequest;
import bobamrz.hajusVahe.dto.DocumentArraySaveResponse;
import bobamrz.hajusVahe.dto.DocumentSaveRequest;
import bobamrz.hajusVahe.dto.DocumentSaveResponse;
import bobamrz.hajusVahe.generated.Document;
import bobamrz.hajusVahe.service.DocumentService;

@WebService(endpointInterface = "bobamrz.hajusVahe.DocumentWebService")
public class DocumentWebServiceImpl implements DocumentWebService {
	
	private static final Logger LOG = LoggerFactory.getLogger(DocumentWebServiceImpl.class);
	
	private DocumentService documentService; 

	public DocumentArraySaveResponse save(DocumentArraySaveRequest request) {
		LOG.debug("web service save invoked");
		try {
			return documentService.saveDocuments(request.getDocumentSaveRequest());
		} catch (CannotCreateTransactionException e) {
			throw new RuntimeException("Database is down", e);
		} catch (Exception e) {
			throw new RuntimeException("Internal server error", e);
		}
		
	}

	public DocumentService getDocumentService() {
		return documentService;
	}

	public void setDocumentService(DocumentService documentService) {
		this.documentService = documentService;
	}



}
