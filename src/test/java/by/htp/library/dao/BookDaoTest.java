package by.htp.library.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import by.htp.library.bean.Book;
import by.htp.library.dao.exception.DAOException;
import by.htp.library.dao.impl.BookDAOImpl;
import by.htp.library.dao.util.ConnectionPool;

public class BookDaoTest {

	private Connection connection;
	private List<Book> expectedList;
	private BookDAO dao;


	public void initDefaultDBConnection() {
		try {
			Connection connection = ConnectionPool.getConnection();
		} catch (DAOException e) {
			e.printStackTrace();
		}
		System.out.println("BeforeClass: connected to DB");
	}


	public void getExpectedList() throws SQLException {

		Statement st = connection.createStatement();
		ResultSet rs = st.executeQuery("select * from book");

		expectedList = new ArrayList<>();

		while (rs.next()) {
			expectedList.add(new Book());
		}
		System.out.println("BeforeMethod: expectedList was recieved");
	}

	public void initDao() {
		dao = new BookDAOImpl();
	}


	public void testRecievedCorrectBookCount() throws DAOException {

		List<Book> actualList = dao.readAll();

//		Assert.assertEquals(actualList.size(), expectedList.size(),
//				"The recieved count of books is not equal real count in DB");
		System.out.println("Test: TestRecievedBooksCount");
	}

	public void cleanExpectedValues() {
		expectedList = null;
		System.out.println("AfterMethod: expectedList null value");
	}

	public void closeDefaultDBConnection() {
		try {
			ConnectionPool.putConnection(connection);
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("AfterClass: disconnect DB");
	}
}
