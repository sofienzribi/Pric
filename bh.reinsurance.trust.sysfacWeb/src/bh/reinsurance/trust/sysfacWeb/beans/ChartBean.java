package bh.reinsurance.trust.sysfacWeb.beans;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;

@ManagedBean
@ViewScoped
public class ChartBean implements Serializable {
	// models
	private static final long serialVersionUID = 1L;
	private BarChartModel animatedModel2;
	private LineChartModel lineModel1;

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

		lineModel1 = initLinearModel();
		lineModel1.setShowPointLabels(false);
		lineModel1.setTitle("Linear Chart");
		lineModel1.setLegendPosition("e");
		Axis yAxis = lineModel1.getAxis(AxisType.Y);
		yAxis.setMin(0);
		yAxis.setMax(10);
		yAxis.setLabel("Premium Rate");
		Axis xaxis = lineModel1.getAxis(AxisType.X);
		xaxis.setLabel("Deductible");

		lineModel1.setAnimate(true);

		animatedModel2 = initBarModel();
		animatedModel2.setTitle("Claims and Premium chart");
		animatedModel2.setAnimate(true);
		animatedModel2.setLegendPosition("ne");
		Axis dAxis = animatedModel2.getAxis(AxisType.Y);
		dAxis.setMin(0);
		dAxis.setMax(200);

	}

	private BarChartModel initBarModel() {
		BarChartModel model = new BarChartModel();

		ChartSeries boys = new ChartSeries();
		boys.setLabel("Premium");

		boys.set("2007", 150);
		boys.set("2008", 190);
		boys.set("2009", 148);
		boys.set("2010", 120);
		boys.set("2011", 100);
		boys.set("2012", 150);
		boys.set("2013", 150);
		boys.set("2014", 148);

		ChartSeries girls = new ChartSeries();
		girls.setLabel("Claims");
		girls.set("2007", 53);
		girls.set("2008", 123);
		girls.set("2009", 109);
		girls.set("2010", 52);
		girls.set("2011", 60);
		girls.set("2012", 110);
		girls.set("2013", 135);
		girls.set("2014", 120);

		model.addSeries(boys);
		model.addSeries(girls);

		return model;
	}

	private LineChartModel initLinearModel() {
		LineChartModel model = new LineChartModel();

		LineChartSeries series1 = new LineChartSeries();
		series1.setLabel(" L/R=48%");
		series1.set(0, 9);
		series1.set(1, 8);
		series1.set(2, 7);
		series1.set(3, 6);
		series1.set(4, 5);
		series1.set(5, 4);

		series1.setShowMarker(false);
		LineChartSeries series2 = new LineChartSeries();
		series2.setLabel("L/R=100%");
		series2.set(0, 7);
		series2.set(1, 6);
		series2.set(2, 5);
		series2.set(3, 4);
		series2.set(4, 3);
		series2.set(5, 2);
		series2.setShowMarker(false);

		LineChartSeries series3 = new LineChartSeries();
		series3.setLabel("L/R=65%");
		series3.set(0, 8);
		series3.set(1, 7);
		series3.set(2, 6);
		series3.set(3, 5);
		series3.set(4, 4);
		series3.set(5, 3);
		series3.setShowMarker(false);

		LineChartSeries series4 = new LineChartSeries();
		series4.setLabel("L/R=32%");
		series4.set(0, 6);
		series4.set(1, 5);
		series4.set(2, 4);
		series4.set(3, 3);
		series4.set(4, 2);
		series4.set(5, 1);
		series4.setShowMarker(false);

		model.addSeries(series1);
		model.addSeries(series3);
		model.addSeries(series2);
		model.addSeries(series4);
		model.setShadow(true);

		return model;

	}

	// get set
	public BarChartModel getAnimatedModel2() {
		return animatedModel2;
	}

	public void setAnimatedModel2(BarChartModel animatedModel2) {
		this.animatedModel2 = animatedModel2;
	}

	public LineChartModel getLineModel1() {
		return lineModel1;
	}

	public void setLineModel1(LineChartModel lineModel1) {
		this.lineModel1 = lineModel1;
	}

}
