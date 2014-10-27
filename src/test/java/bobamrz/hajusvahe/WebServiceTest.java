package bobamrz.hajusvahe;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import bobamrz.hajusVahe.DocumentWebServiceImpl;
import bobamrz.hajusVahe.dto.DocumentArraySaveRequest;
import bobamrz.hajusVahe.dto.DocumentArraySaveResponse;
import bobamrz.hajusVahe.dto.DocumentSaveRequest;

public class WebServiceTest {
	
	@Test
	public void a() {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("/applicationContext.xml");
		DocumentWebServiceImpl webService = applicationContext.getBean(DocumentWebServiceImpl.class);
		
		DocumentArraySaveRequest arraySaveRequest = new DocumentArraySaveRequest();
		List<DocumentSaveRequest> documentSaveRequests = new ArrayList<DocumentSaveRequest>();
		DocumentSaveRequest documentSaveRequest = new DocumentSaveRequest();
		documentSaveRequest.setId(9999);
		documentSaveRequest.setCatalogName("catalog");
		documentSaveRequest.setDesription("bla-bla");
		documentSaveRequest.setName("docName");
		documentSaveRequests.add(documentSaveRequest);
		arraySaveRequest.setDocumentSaveRequest(documentSaveRequests);
		
		DocumentArraySaveResponse response = webService.save(arraySaveRequest);
		System.out.println(response);
	}

}
