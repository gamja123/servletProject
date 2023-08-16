package com.chart;

import java.io.IOException;

import javax.servlet.ServletOutputStream;
import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;


import org.jfree.chart.ChartFactory;//차트 생성
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;


//@WebServlet("/XYPlotServlet")
public class XYPlotServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final String DB_DRIVER="oracle.jdbc.driver.OracleDriver";
	private static final String DB_URL="jdbc:oracle:thin:@localhost:1521:orcl";
	private static final String DB_ID="scott";
	private static final String DB_PASS="tiger";
	

	public void init() throws ServletException {
		
		try {
			
			Class.forName(DB_DRIVER);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			
			Connection conn = DriverManager.getConnection(DB_URL, DB_ID,  DB_PASS);
			
			Statement st = conn.createStatement();
			String sql = "select value from stock";
			ResultSet rs = st.executeQuery(sql);
			
			XYSeries vs = new XYSeries("Stock Flow");
			int x = 1;
			
			while(rs.next()) {
				vs.add(x, rs.getInt(1));
				x += 1;
			}
			
			XYSeriesCollection ds = new XYSeriesCollection(vs);
			
			JFreeChart chart = ChartFactory.createXYLineChart("Stock Average", "Day", "Price", ds, PlotOrientation.VERTICAL, true, true, true);
			
			ServletOutputStream out = response.getOutputStream();
			ChartUtilities.writeChartAsJPEG(out, chart, 500, 500);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
	}

	
	
	
}
