package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class jdbc {
private static String url="";
private static Connection con;

private static void setUrl() {

	url="jdbc:mysql://localhost:3306/docshot"+"?useUnicode=true&characterEncoding=UTF-8";
}


public static  Connection setConnection() throws SQLException {
	setUrl();
	con=DriverManager.getConnection(url,"root","159357258456");
	return con;
}
}
