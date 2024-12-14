
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;

/**
 * Servlet implementation class UserServlet
 */
@WebServlet("/UserServlet")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// Step 1: Prepare list of variables used for database connections
	private String jdbcURL = "jdbc:mysql://localhost:3306/inventorydetails";
	private String jdbcUsername = "root";
	private String jdbcPassword = "password";

	// Step 2: Prepare list of SQL prepared statements to perform CRUD to our
	// database
	// private static final String INSERT_USERS_SQL = "INSERT INTO INVENTORYDETAILS" + " (item, quantity) VALUES " + " (?);";
	private static final String INSERT_USERS_SQL = "insert into INVENTORYDETAILS values(?,?)";
	private static final String SELECT_USER_BY_ID = "select item,quantity from INVENTORYDETAILS where item =?";
	private static final String SELECT_ALL_USERS = "select * from INVENTORYDETAILS ";
	private static final String DELETE_USERS_SQL = "delete from INVENTORYDETAILS where item = ?;";
	private static final String UPDATE_USERS_SQL = "update INVENTORYDETAILS set item = ?,quantity= ? where item = ?;";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		// Step 4: Depending on the request servlet path, determine the function to
		// invoke using the follow switch statement.
		String action = request.getServletPath();
		try {
			switch (action) {
			case "/UserServlet/delete":
				deleteItem(request, response);
				break;
			case "/UserServlet/edit":
				showEditForm(request, response);
				break;
			case "/UserServlet/update":
				updateItem(request, response);
				break;
			case "/UserServlet/dashboard":
				listItems(request, response);
				break;
			case "/UserServlet/insert":
				addNewItem(request, response);
				break;
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	// Step 3: Implement the getConnection method which facilitates connection to
	// the database via JDBC
	protected Connection getConnection() {
		Connection connection = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return connection;
	}

	// Step 5: listItems function to connect to the database and retrieve all items
	// records
	private void listItems(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		List<InventoryItem> items = new ArrayList<>();
		try (Connection connection = getConnection();
				// Step 5.1: Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_USERS);) {
			// Step 5.2:
			// Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();
			// Step 5.3: Process the ResultSet object.
			while (rs.next()) {
				String item = rs.getString("item");
				String quantity = rs.getString("quantity");
				items.add(new InventoryItem(item, quantity));
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		// Step 5.4: Set the items list into the listItems attribute to be pass to the
		// userManagement.jsp
		request.setAttribute("listItems", items);
		request.getRequestDispatcher("/userManagement.jsp").forward(request, response);
	}

	// method to get parameter, query database for existing user data and redirect
	// to user edit page
	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		// get parameter passed in the URL
		String item = request.getParameter("item");
		InventoryItem existingUser = new InventoryItem("", "");
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();
				// Step 2:Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_ID);) {
			preparedStatement.setString(1, item);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();
			// Step 4: Process the ResultSet object
			while (rs.next()) {
				item = rs.getString("item");
				String quantity = rs.getString("quantity");
				existingUser = new InventoryItem(item, quantity);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} // Step 5: Set existingUser to request and serve up the userEdit form
		request.setAttribute("InventoryItem", existingUser);
		request.getRequestDispatcher("/userEdit.jsp").forward(request, response);
	}

	// method to update the user table base on the form data
	private void updateItem(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		// Step 1: Retrieve value from the request
		String oriItem = request.getParameter("oriItem");
		String item = request.getParameter("itemName");
		String quantity = request.getParameter("quantity");

		// Step 2: Attempt connection with database and execute update user SQL query
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(UPDATE_USERS_SQL);) {
			statement.setString(1, item);
			statement.setString(2, quantity);
			statement.setString(3, oriItem);
			int i = statement.executeUpdate();
		} // Step 3: redirect back to UserServlet (note: remember to change the url to
			// your project name)
		response.sendRedirect("http://localhost:8091/InventoryProject/UserServlet/dashboard");
	}

	// method to delete user
	private void deleteItem(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {

		// Step 1: Retrieve value from the request
		String item = request.getParameter("item");
		// Step 2: Attempt connection with database and execute delete user SQL query
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE_USERS_SQL);) {
			statement.setString(1, item);
			int i = statement.executeUpdate();
		}
		// Step 3: redirect back to UserServlet dashboard (note: remember to change the
		// url to your project name)
		response.sendRedirect("http://localhost:8091/InventoryProject/UserServlet/dashboard");
	}

	// method to trigger RegisterServlet
	// private void addNewItem(HttpServletRequest request, HttpServletResponse response)
			// throws SQLException, IOException, ServletException {
	private void addNewItem(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		// RequestDispatcher rd = null;
		// rd = getServletContext().getRequestDispatcher("/RegisterServlet");
		// rd.include(request, response);
		
		// request.getRequestDispatcher("/userEdit.jsp").forward(request, response);
		request.getRequestDispatcher("/userEdit.jsp").include(request, response);	
		
		InventoryItem existingUser = new InventoryItem("", "");
		String item = request.getParameter("itemName");
		String quantity = request.getParameter("quantity");

		request.setAttribute("InventoryItem", existingUser);
		// request.getRequestDispatcher("/userEdit.jsp").forward(request, response);
		
		// Step 2: Attempt connection with database and execute update user SQL query
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(INSERT_USERS_SQL);) {
			statement.setString(1, item);
			statement.setString(2, quantity);
			int i = statement.executeUpdate();
		}
		// Step 3: redirect back to UserServlet (note: remember to change the url to your project name)
		response.sendRedirect("http://localhost:8091/InventoryProject/UserServlet/dashboard");
	}

}
