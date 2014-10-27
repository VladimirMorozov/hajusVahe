package bobamrz.hajusVahe.exceptions;

import java.util.List;

public class CatalogMaxDocumentCountExceededException extends DocumentSaveException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3697024075935571425L;
	
	private List<String> catalogNames;

	public CatalogMaxDocumentCountExceededException(List<String> catalogNames) {
		super();
		this.setCatalogNames(catalogNames);  
	}

	public CatalogMaxDocumentCountExceededException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace, List<String> catalogNames) {
		super(message, cause, enableSuppression, writableStackTrace);
		this.setCatalogNames(catalogNames);  
	}

	public CatalogMaxDocumentCountExceededException(String message, Throwable cause, List<String> catalogNames) {
		super(message, cause);
		this.setCatalogNames(catalogNames);  
	}

	public CatalogMaxDocumentCountExceededException(String message, List<String> catalogNames) {
		super(message);
		this.setCatalogNames(catalogNames);  
	}

	public CatalogMaxDocumentCountExceededException(Throwable cause, List<String> catalogNames) {
		super(cause);
		this.setCatalogNames(catalogNames);  
	}

	public List<String> getCatalogNames() {
		return catalogNames;
	}

	public void setCatalogNames(List<String> catalogNames) {
		this.catalogNames = catalogNames;
	}
	
	

}
