package bobamrz.hajusvahe;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.transaction.CannotCreateTransactionException;

import bobamrz.hajusVahe.DocumentWebServiceImpl;
import bobamrz.hajusVahe.dto.DocumentArraySaveRequest;
import bobamrz.hajusVahe.dto.DocumentArraySaveResponse;
import bobamrz.hajusVahe.dto.DocumentSaveRequest;
import bobamrz.hajusVahe.service.DocumentService;
import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

public class DocumentWebServiceTest {
	
	@Test
	public void testExceptionHandling() {
		DocumentWebServiceImpl webService = new DocumentWebServiceImpl();
		DocumentService documentService = mock(DocumentService.class);
		webService.setDocumentService(documentService);
		
		when(documentService.saveDocuments(any(List.class))).thenReturn(new DocumentArraySaveResponse());
		webService.save(new DocumentArraySaveRequest());
		
		documentService = mock(DocumentService.class);
		webService.setDocumentService(documentService);
		when(documentService.saveDocuments(any(List.class))).thenThrow(new IllegalArgumentException());
		try {
			webService.save(new DocumentArraySaveRequest());
			fail();
		} catch (RuntimeException e) {
			//ok
		}
		
		documentService = mock(DocumentService.class);
		webService.setDocumentService(documentService);
		when(documentService.saveDocuments(any(List.class))).thenThrow(new CannotCreateTransactionException(""));
		try {
			webService.save(new DocumentArraySaveRequest());
			fail();
		} catch (RuntimeException e) {
			//ok
		}
		
	}

}
