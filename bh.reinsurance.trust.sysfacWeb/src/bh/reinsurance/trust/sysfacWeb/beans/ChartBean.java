package bh.reinsurance.trust.sysfacWeb.beans;

import java.awt.Color;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYSplineRenderer;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.RectangleInsets;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;

@ManagedBean
@RequestScoped
public class ChartBean implements Serializable {
	// models
	private static final long serialVersionUID = 1L;
	private LineChartModel lineModel1;
	private StreamedContent thermoChart;

	// const
	public ChartBean() {

		// TODO Auto-generated constructor stub
	}

	@PostConstruct
	public void init() {
		try {
			test();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		createAnimatedModels();
	}

	// methods
	private void createAnimatedModels() {

		lineModel1 = initLinearModel();
		lineModel1.setShowPointLabels(false);
		lineModel1.setTitle("Linear Chart");
		lineModel1.setLegendPosition("e");
		Axis yAxis = lineModel1.getAxis(AxisType.Y);
		Axis xAxis = lineModel1.getAxis(AxisType.X);
		xAxis.setMin(0);
		yAxis.setMin(0.018);

		yAxis.setLabel("Premiums Rate");
		Axis xaxis = lineModel1.getAxis(AxisType.X);
		xaxis.setLabel("Deductible");
		lineModel1.setAnimate(true);

	}

	public void test() throws IOException {
		XYSeries xyseries = new XYSeries("44% ");
		xyseries.add(0, 0.0184275418376766);
		xyseries.add(10000, 0.0181106066179827);
		xyseries.add(20000, 0.0177936713982889);
		xyseries.add(30000, 0.017478673193647);
		xyseries.add(40000, 0.0171649720068071);
		xyseries.add(50000, 0.016854146859412);
		xyseries.add(60000, 0.0165443057930329);
		xyseries.add(70000, 0.0162420875865739);
		xyseries.add(80000, 0.0159461594334746);
		xyseries.add(90000, 0.0156519417718682);
		xyseries.add(100000, 0.0153608788150065);
		xyseries.add(120000, 0.0147906870946214);
		xyseries.add(130000, 0.0145120736333277);
		xyseries.add(140000, 0.0142387966837444);
		xyseries.add(150000, 0.0139703719568608);
		xyseries.add(160000, 0.0137058957560458);
		xyseries.add(170000, 0.0134463543898315);
		xyseries.add(180000, 0.0131901034706864);
		xyseries.add(190000, 0.0129351075218858);
		xyseries.add(200000, 0.0126877869483723);
		xyseries.add(210000, 0.0124451887142262);
		xyseries.add(220000, 0.0122060018523789);
		xyseries.add(230000, 0.0119699174540355);
		xyseries.add(240000, 0.0117338330556921);
		xyseries.add(250000, 0.0114977486573487);
		XYSeriesCollection xyseriescollection = new XYSeriesCollection(xyseries);
		
		NumberAxis numberaxis = new NumberAxis("Deductible");
		NumberAxis numberaxis1 = new NumberAxis("Premium rate");
		XYSplineRenderer xysplinerenderer = new XYSplineRenderer();

		XYPlot xyplot = new XYPlot(xyseriescollection, numberaxis, numberaxis1,
				xysplinerenderer);

		xyplot.setBackgroundPaint(Color.lightGray);
		xyplot.setDomainGridlinePaint(Color.white);
		xyplot.setRangeGridlinePaint(Color.white);
		xyplot.setAxisOffset(new RectangleInsets(4D, 4D, 4D, 4D));
		JFreeChart chart = new JFreeChart("Premium Rate=f(Deductible,L/R)",
				JFreeChart.DEFAULT_TITLE_FONT, xyplot, true);

		File chartFile = new File(
				"/Users/zribisofien/Desktop/GeneratedGraph.png");

		ChartUtilities.saveChartAsPNG(chartFile, chart, 700, 400);

		setThermoChart(new DefaultStreamedContent(
				new FileInputStream(chartFile), "image/png"));

	}

	private LineChartModel initLinearModel() {
		LineChartModel model = new LineChartModel();

		LineChartSeries series1 = new LineChartSeries();
		series1.setLabel(" L/R=48%");
		series1.set(0, 0.018);
		series1.set(10000, 0.018);
		series1.set(20000, 0.018);
		series1.set(30000, 0.017);
		series1.set(40000, 0.017);
		series1.set(50000, 0.017);
		series1.set(60000, 0.017);
		series1.set(70000, 0.016);
		series1.setShowMarker(false);

		model.addSeries(series1);
		model.setShadow(true);

		return model;

	}

	// get set

	public LineChartModel getLineModel1() {
		return lineModel1;
	}

	public void setLineModel1(LineChartModel lineModel1) {
		this.lineModel1 = lineModel1;
	}

	public StreamedContent getThermoChart() {
		return thermoChart;
	}

	public void setThermoChart(StreamedContent thermoChart) {
		this.thermoChart = thermoChart;
	}

}
