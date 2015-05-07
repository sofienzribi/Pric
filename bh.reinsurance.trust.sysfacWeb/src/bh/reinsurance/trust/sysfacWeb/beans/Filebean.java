package bh.reinsurance.trust.sysfacWeb.beans;

import java.io.InputStream;
import java.io.Serializable;

import javax.annotation.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
@RequestScoped
@ManagedBean
public class Filebean implements Serializable {

	 private StreamedContent file;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public StreamedContent getFile() {
		return file;
	}
	public void setFile(StreamedContent file) {
		this.file = file;
	}
	 public void FileDownloadView() {        
	        InputStream stream = ((ServletContext)FacesContext.getCurrentInstance().getExternalContext().getContext()).getResourceAsStream("/resources/Report/UserGuideOpenProject.pdf");
	        file = new DefaultStreamedContent(stream, "application/pdf", "downloaded_optimus.pdf");
	    }

}
