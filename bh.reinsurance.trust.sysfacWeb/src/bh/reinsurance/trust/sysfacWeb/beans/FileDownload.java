package bh.reinsurance.trust.sysfacWeb.beans;

import java.io.InputStream;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

@ManagedBean
public class FileDownload {
	private StreamedContent file;

	public FileDownload() {

		InputStream stream = ((ServletContext) FacesContext
				.getCurrentInstance().getExternalContext().getContext())
				.getResourceAsStream("/resources/Report/UserGuideV00.pdf");
		setFile(new DefaultStreamedContent(stream, "application/pdf",
				"UserGuide.pdf"));

	}

	public StreamedContent getFile() {
		return file;
	}

	public void setFile(StreamedContent file) {
		this.file = file;
	}

}
