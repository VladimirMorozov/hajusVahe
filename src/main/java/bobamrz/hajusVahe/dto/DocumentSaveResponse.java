package bobamrz.hajusVahe.dto;

import java.util.Date;

import javax.xml.bind.annotation.XmlElement;

import org.springframework.beans.BeanUtils;

import bobamrz.hajusVahe.generated.Catalog;
import bobamrz.hajusVahe.generated.Document;

public class DocumentSaveResponse {

	private Integer id;
	private String name;
	private String desription;
	private Date createdDate;
	
	private DocumentSaveResult result;
	
	public DocumentSaveResponse() {
		
	}
	
	public DocumentSaveResponse(Document document) {
		BeanUtils.copyProperties(document, this);
		this.setResult(DocumentSaveResult.SUCCESS);
	}
	
	public DocumentSaveResponse(DocumentSaveRequest request, DocumentSaveResult saveResult) {
		BeanUtils.copyProperties(request, this);
		this.setResult(saveResult);
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDesription() {
		return this.desription;
	}

	public void setDesription(String desription) {
		this.desription = desription;
	}

	public Date getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}


	public DocumentSaveResult getResult() {
		return result;
	}

	public void setResult(DocumentSaveResult result) {
		this.result = result;
	}

	@Override
	public String toString() {
		return "DocumentSaveResponse [id=" + id + ", name=" + name
				+ ", desription=" + desription + ", createdDate=" + createdDate
				+ ", result=" + result + "]";
	}
	
	
	
}
