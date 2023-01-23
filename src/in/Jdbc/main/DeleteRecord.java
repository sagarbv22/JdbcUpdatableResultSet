package in.Jdbc.main;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import in.Jdbc.util.JdbcUtil;

public class DeleteRecord {

	public static void main(String[] args) {

		Connection connection = null;
		Statement stmt = null;
		ResultSet resultSet = null;

		ResultSet rs = null;

		connection = JdbcUtil.getConnection();

		try {

			if (connection != null)
				stmt = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);

			if (stmt != null)
				resultSet = stmt.executeQuery("select id, firstName, lastName, profession, city from friends");

			System.out.println("Before Deletion..");
			if (resultSet != null)
				while (resultSet.next()) {
					System.out.println(resultSet.getInt(1) + "\t" + resultSet.getString(2) + "\t"
							+ resultSet.getString(3) + "\t" + resultSet.getString(4) + "\t" + resultSet.getString(5));
				}

			System.in.read();
			resultSet.last();// Before the first record
			resultSet.deleteRow();// delete the last row
			System.out.println("After Deletion..");
			resultSet.beforeFirst();
			while (resultSet.next()) {
				System.out.println(resultSet.getInt(1) + "\t" + resultSet.getString(2) + "\t" + resultSet.getString(3)
						+ "\t" + resultSet.getString(4) + "\t" + resultSet.getString(5));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
