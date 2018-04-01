package com.FileToPdf;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;

public class Reader {
    
	public static void main(String[] args) throws IOException, Exception {
		String line,str="";
		BufferedReader br=new BufferedReader(new FileReader("F:/codeshala/bfs/bfs traversal.txt"));
		line=br.readLine();
		while(line!=null)
		{
			str=str+line+'\n';
			line=br.readLine();
		}
		System.out.println(str);
		TextFile textfile=new TextFile();
	    textfile.setTextfile(str);
		SessionFactory sessionfactory=new Configuration().configure().buildSessionFactory();
		Session session=sessionfactory.openSession();
		session.beginTransaction();
		session.save(textfile);
		session.getTransaction().commit();
		session.close();
		sessionfactory.close();
		Connection conn=null;
	    try {
			Class.forName("com.mysql.jdbc.Driver");
			conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/texttopdf","root","root");
			String report="C:/Users/HP/JaspersoftWorkspace/MyReports/conFileToPdf.jrxml";
			JasperReport jasperreport=JasperCompileManager.compileReport(report);
			JasperPrint jp=JasperFillManager.fillReport(jasperreport,null,conn);
			JasperExportManager.exportReportToPdfFile(jp,"C:/Users/HP/finalfile.pdf");
			
			
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		}
	    
	}
}
