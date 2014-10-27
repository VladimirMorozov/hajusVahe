package bobamrz.hajusvahe;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import bobamrz.hajusVahe.DocumentWebServiceImpl;
import bobamrz.hajusVahe.dto.DocumentArraySaveRequest;
import bobamrz.hajusVahe.dto.DocumentArraySaveResponse;
import bobamrz.hajusVahe.dto.DocumentArraySaveResult;
import bobamrz.hajusVahe.dto.DocumentArraySaveValidationResult;
import bobamrz.hajusVahe.dto.DocumentSaveRequest;
import bobamrz.hajusVahe.generated.Catalog;
import bobamrz.hajusVahe.generated.CatalogHome;
import bobamrz.hajusVahe.generated.DocumentHome;
import bobamrz.hajusVahe.service.DocumentValidator;
import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

public class ValidatorTest {
	
	private DocumentValidator validator;
	private CatalogHome mockedCatalogHome;
	
	@Before
	public void setUp() {
		//ApplicationContext applicationContext = new ClassPathXmlApplicationContext("/applicationContext.xml");
		validator = new DocumentValidator();//applicationContext.getBean(DocumentValidator.class);
		
		//created mocked catalog home where catalog with id=3 exists
		mockedCatalogHome = mock(CatalogHome.class);
		when(mockedCatalogHome.findById(3)).thenReturn(new Catalog());
		
		validator.setCatalogHome(mockedCatalogHome);
	}
	
	@Test
	public void testValid() {
		List<DocumentSaveRequest> documentSaveRequests = new ArrayList<DocumentSaveRequest>();
		DocumentSaveRequest documentSaveRequest = new DocumentSaveRequest();
		documentSaveRequest.setCatalogId(3);
		DocumentArraySaveValidationResult result = validator.validateArraySaveRequest(documentSaveRequests);
		assertEquals(DocumentArraySaveResult.SUCCESS, result.getResult());
		
		
	}

}
