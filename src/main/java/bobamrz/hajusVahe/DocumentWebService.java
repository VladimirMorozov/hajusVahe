package bobamrz.hajusVahe;

import java.util.List;

import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.ParameterStyle;

import bobamrz.hajusVahe.dto.DocumentArraySaveRequest;
import bobamrz.hajusVahe.dto.DocumentArraySaveResponse;
import bobamrz.hajusVahe.dto.DocumentArraySaveResult;
import bobamrz.hajusVahe.dto.DocumentSaveRequest;
import bobamrz.hajusVahe.dto.DocumentSaveResponse;
import bobamrz.hajusVahe.generated.Document;

@WebService
@SOAPBinding(parameterStyle=ParameterStyle.BARE)
public interface DocumentWebService {
	
	@WebResult(name="documentSaveResponse")
	public DocumentArraySaveResponse save(@WebParam(name="documentArraySaveRequest")DocumentArraySaveRequest request);

}
