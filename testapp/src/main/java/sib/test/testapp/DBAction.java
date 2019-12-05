package sib.test.testapp;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class DBAction{
	
	private JdbcTemplate jdbcTemplate;
	private ApplicationContext context;
	private ArrayList<String> arrayToBase;
	private ResultSet rs;
	private int rowsCount = 0;
	private long time =  new java.util.Date().getTime();
	
	public DBAction(ArrayList<String> arrayToBase) {
		this.arrayToBase = arrayToBase;
	}
	
	public void connection() {
		context = new ClassPathXmlApplicationContext("context.xml");
        jdbcTemplate = context.getBean(JdbcTemplate.class);
	}
	
	public void inserContent() {
		startInser();
		checkRowsCount();
		if (rowsCount > 10) {
			try {
				jdbcTemplate.getDataSource().getConnection().createStatement().close();
				jdbcTemplate.execute("DELETE FROM testTable WHERE id = (SELECT min(id) FROM testTable)");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	private void startInser() {
        jdbcTemplate.execute("INSERT INTO testTable VALUES (" + time + ","  +"\"" +arrayToBase.get(0) + "\""+"," + "\"" + arrayToBase.get(1) +"\"" + "," +"\"" + arrayToBase.get(2)+"\""+");");
	}
	
	private void checkRowsCount() {
		try {
			rs = jdbcTemplate.getDataSource().getConnection().createStatement().executeQuery("SELECT COUNT(*) FROM testTable");
			rowsCount = Integer.parseInt(rs.getString(1));
			rs.close();
		} catch (SQLException e) {
			rowsCount = 10;
		}
	}
}
