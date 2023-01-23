package in.Jdbc.main;

import java.io.IOException;
import java.sql.*;

import in.Jdbc.util.JdbcUtil;

public class SensitiveNature {

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

			if (resultSet != null)
				while (resultSet.next()) {
					System.out.println(resultSet.getInt(1) + "\t" + resultSet.getString(2) + "\t"
							+ resultSet.getString(3) + "\t" + resultSet.getString(4) + "\t" + resultSet.getString(5));
				}
			System.in.read();
			resultSet.beforeFirst();//Before the first record

			while (resultSet.next()) {
				resultSet.refreshRow();//refresh the row to get updated records
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
