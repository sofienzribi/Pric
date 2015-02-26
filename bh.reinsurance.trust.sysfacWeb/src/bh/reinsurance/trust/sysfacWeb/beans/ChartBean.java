package bh.reinsurance.trust.sysfacWeb.beans;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;

@ManagedBean
@ViewScoped
public class ChartBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private BarChartModel animatedModel2;

	// models

	public BarChartModel getAnimatedModel2() {
		return animatedModel2;
	}

	public void setAnimatedModel2(BarChartModel animatedModel2) {
		this.animatedModel2 = animatedModel2;
	}

	// const
	public ChartBean() {
		// TODO Auto-generated constructor stub
	}

	@PostConstruct
	public void init() {
		 createAnimatedModels();
	}
	// methods
	private void createAnimatedModels() {
        
         
        animatedModel2 = initBarModel();
        animatedModel2.setTitle("Claims and Premium chart");
        animatedModel2.setAnimate(true);
        animatedModel2.setLegendPosition("ne");
        Axis yAxis = animatedModel2.getAxis(AxisType.Y);
        yAxis.setMin(0);
        yAxis.setMax(200);
    }
	
	
	
	 private BarChartModel initBarModel() {
	        BarChartModel model = new BarChartModel();
	 
	        ChartSeries boys = new ChartSeries();
	        boys.setLabel("Premium");
	        boys.set("2010", 120);
	        boys.set("2011", 100);
	        boys.set("2012", 150);
	        boys.set("2013", 150);
	        boys.set("2014", 148);
	 
	        ChartSeries girls = new ChartSeries();
	        girls.setLabel("Claims");
	        girls.set("2010", 52);
	        girls.set("2011", 60);
	        girls.set("2012", 110);
	        girls.set("2013", 135);
	        girls.set("2014", 120);
	 
	        model.addSeries(boys);
	        model.addSeries(girls);
	         
	        return model;
	    }
	
	
	// get set
	
	
	

}
