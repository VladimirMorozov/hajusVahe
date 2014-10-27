package bobamrz.hajusvahe;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import bobamrz.hajusVahe.DocumentWebServiceImpl;
import bobamrz.hajusVahe.dto.DocumentArraySaveRequest;
import bobamrz.hajusVahe.dto.DocumentArraySaveResponse;
import bobamrz.hajusVahe.dto.DocumentArraySaveResult;
import bobamrz.hajusVahe.dto.DocumentArraySaveValidationResult;
import bobamrz.hajusVahe.dto.DocumentSaveRequest;
import bobamrz.hajusVahe.generated.CatalogHome;
import bobamrz.hajusVahe.generated.DocumentHome;
import bobamrz.hajusVahe.service.DocumentService;
import bobamrz.hajusVahe.service.DocumentValidator;
import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

public class DocumentServiceTest {

	private DocumentService documentService;
	private DocumentValidator mockedDocumentValidator;
	private DocumentHome mockedDocumentHome;
	private CatalogHome mockedCatalogHome;
	
	@Before
	public void setUp() {
		mockedDocumentValidator = 	mock(DocumentValidator.class);
		mockedDocumentHome 		= 	mock(DocumentHome.class);
		mockedCatalogHome 		= 	mock(CatalogHome.class);
		
		DocumentArraySaveValidationResult successfulValidation = new DocumentArraySaveValidationResult();
		successfulValidation.setResult(DocumentArraySaveResult.SUCCESS);
		when(mockedDocumentValidator.validateArraySaveRequest(any(List.class))).thenReturn(successfulValidation);
		
		//ApplicationContext applicationContext = new ClassPathXmlApplicationContext("/applicationContext.xml");
		documentService = new DocumentService();//applicationContext.getBean(DocumentService.class);
		documentService.setCatalogHome(mockedCatalogHome);
		documentService.setDocumentHome(mockedDocumentHome);
		documentService.setDocumentValidator(mockedDocumentValidator);
	}
	
	@Test
	public void saveTest() {
		
		List<DocumentSaveRequest> documentSaveRequests = new ArrayList<DocumentSaveRequest>();
		DocumentSaveRequest documentSaveRequest = new DocumentSaveRequest();
		documentSaveRequest.setId(9999);
		documentSaveRequest.setCatalogName("catalog");
		documentSaveRequest.setDesription("bla-bla");
		documentSaveRequest.setName("docName");
		documentSaveRequests.add(documentSaveRequest);
		
		DocumentArraySaveResponse response = documentService.saveDocuments(documentSaveRequests);
		assertEquals(DocumentArraySaveResult.SUCCESS, response.getResult());
	}

	public DocumentValidator getMockedDocumentValidator() {
		return mockedDocumentValidator;
	}

	public void setMockedDocumentValidator(DocumentValidator mockedDocumentValidator) {
		this.mockedDocumentValidator = mockedDocumentValidator;
	}
}
