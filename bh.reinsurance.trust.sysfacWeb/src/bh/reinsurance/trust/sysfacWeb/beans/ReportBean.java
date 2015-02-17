package bh.reinsurance.trust.sysfacWeb.beans;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.SessionScoped;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.base.JRBaseField;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import org.primefaces.context.RequestContext;

import al.assu.trust.GestionImageSinistre.domain.Facultative;
import al.assu.trust.GestionImageSinistre.impl.FacultativeServicesLocal;

@javax.faces.bean.ManagedBean
@SessionScoped
public class ReportBean {

	private List<Facultative> facultatives;
	private JasperPrint jasperPrint;
	@EJB
	private FacultativeServicesLocal facultativeServicesLocal;
	private Facultative facultative;
	private String ProjectName;
	private List<Facultative> facultativesbychoice;
	public ReportBean() {
		// TODO Auto-generated constructor stub
	}

	@PostConstruct
	public void ini() {
		facultatives = facultativeServicesLocal.facultatives();

	}

	public void init() throws JRException {

		RequestContext context = RequestContext.getCurrentInstance();
		context.execute("popup.hide();");
		JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(
				facultatives);
		
		setJasperPrint(JasperFillManager.fillReport(
				"/Users/zribisofien/report1.jasper", new HashMap(),
				beanCollectionDataSource));

	}
	public void addchoicetoreport(List< Facultative> facultatives1) throws JRException{
		RequestContext context = RequestContext.getCurrentInstance();
		context.execute("popup.hide();");
		JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(
				facultatives1);
		setJasperPrint(JasperFillManager.fillReport(
				"/Users/zribisofien/report1.jasper", new HashMap(),
				beanCollectionDataSource));
		JasperExportManager.exportReportToPdfFile(jasperPrint,
				"/Users/zribisofien/Desktop/TestChoice.pdf");
	}
	public void ExportToPDF() throws JRException, IOException {
		init();

		JasperExportManager.exportReportToPdfFile(jasperPrint,
				"/Users/zribisofien/Desktop/"+ProjectName+".pdf");

	}

	public List<Facultative> getFacultatives() {
		return facultatives;
	}

	public void setFacultatives(List<Facultative> facultatives) {
		this.facultatives = facultatives;
	}

	public JasperPrint getJasperPrint() {
		return jasperPrint;
	}

	public void setJasperPrint(JasperPrint jasperPrint) {
		this.jasperPrint = jasperPrint;
	}

	public Facultative getFacultative() {
		return facultative;
	}

	public void setFacultative(Facultative facultative) {
		this.facultative = facultative;
	}

	public String getProjectName() {
		return ProjectName;
	}

	public void setProjectName(String projectName) {
		ProjectName = projectName;
	}

	public List<Facultative> getFacultativesbychoice() {
		return facultativesbychoice;
	}

	public void setFacultativesbychoice(List<Facultative> facultativesbychoice) {
		this.facultativesbychoice = facultativesbychoice;
	}

}
