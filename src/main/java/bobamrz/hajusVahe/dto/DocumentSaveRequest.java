package bobamrz.hajusVahe.dto;

import java.util.Date;

import javax.xml.bind.annotation.XmlElement;

import bobamrz.hajusVahe.generated.Catalog;

public class DocumentSaveRequest {
	
	private Integer id;
	private String name;
	private String desription;
	private String catalogName;
	private Integer catalogId;

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

	public String getCatalogName() {
		return catalogName;
	}

	public void setCatalogName(String catalogName) {
		this.catalogName = catalogName;
	}

	public Integer getCatalogId() {
		return catalogId;
	}

	public void setCatalogId(Integer catalogId) {
		this.catalogId = catalogId;
	}
	
	

}
