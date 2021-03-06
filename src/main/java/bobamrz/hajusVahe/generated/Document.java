package bobamrz.hajusVahe.generated;

// Generated 06.10.2014 17:11:28 by Hibernate Tools 3.4.0.CR1

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Document generated by hbm2java
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Document {

	private Integer id;
	private Catalog catalog;
	private String name;
	private String desription;
	@XmlElement
	private Date createdDate;

	public Document() {
	}

	public Document(int id) {
		this.id = id;
	}

	public Document(int id, Catalog catalog, String name, String desription,
			Date createdDate) {
		this.id = id;
		this.catalog = catalog;
		this.name = name;
		this.desription = desription;
		this.createdDate = createdDate;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Catalog getCatalog() {
		return this.catalog;
	}

	public void setCatalog(Catalog catalog) {
		this.catalog = catalog;
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

}
