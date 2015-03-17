package bh.reinsurance.trust.sysfacWeb.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;

@javax.faces.bean.ManagedBean(name = "applicationbean")
@ApplicationScoped
public class ApplicationBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<LoginBean> list;

	public ApplicationBean() {
		// TODO Auto-generated constructor stub
	}

	@PostConstruct
	public void init() {
		list = new ArrayList<LoginBean>();
	}
	//test
	public void getrr() {
		System.out.println(list.size());

	}

	public List<LoginBean> getList() {
		return list;
	}

	public void setList(List<LoginBean> list) {
		this.list = list;
	}
}
