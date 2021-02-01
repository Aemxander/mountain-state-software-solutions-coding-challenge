import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Connect {
	public static Connection connect() {
		String url = "jdbc:sqlite:" + System.getProperty("user.dir") + "/ms3interview.db";
		Connection conn = null;

		try {
			conn = DriverManager.getConnection(url);
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}

		return conn;
	}
	
	public static void dropTable() {
		// Drop table sql
		String sql = "DROP TABLE IF EXISTS defaultTable;";
		
		try (Connection conn = connect(); Statement stmt = conn.createStatement()) {
			stmt.execute(sql);
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}
	}

	public static void createTable() {
		// Not using a primary key because every column can have an empty value
		String sql = "CREATE TABLE IF NOT EXISTS defaultTable (\n" + "	a TEXT,\n" + "	b TEXT,\n" + "	c TEXT,\n"
				+ "	d TEXT,\n" + "	e TEXT,\n" + "	f TEXT,\n" + "	g TEXT,\n" + "	h TEXT,\n" + "	i TEXT,\n"
				+ "	j TEXT" + ");";

		try (Connection conn = connect(); Statement stmt = conn.createStatement()) {
			stmt.execute(sql);
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}
	}

	public static void closeConnection() {
		Connection conn = connect();

		try {
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}
	}

	public static void insert(String a, String b, String c, String d, String e, String f, String g, String h, String i,
			String j) {
		String sql = "INSERT INTO defaultTable(a,b,c,d,e,f,g,h,i,j) VALUES(?,?,?,?,?,?,?,?,?,?)";

		try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, a);
			pstmt.setString(2, b);
			pstmt.setString(3, c);
			pstmt.setString(4, d);
			pstmt.setString(5, e);
			pstmt.setString(6, f);
			pstmt.setString(7, g);
			pstmt.setString(8, h);
			pstmt.setString(9, i);
			pstmt.setString(10, j);
			pstmt.executeUpdate();
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}
	}

	public static void main(String[] args) throws IOException {
		Scanner scanner = new Scanner(System.in); // Create a Scanner object
		System.out.print("Enter filename: "); // Print instructions

		String filename = scanner.nextLine(); // Save input
		String filenameBase = filename.substring(0, filename.indexOf(".")); // Get base part of filename

		scanner.close(); // Close scanner

		dropTable(); // Drop table from database file
		createTable(); // Create table in database file

		BufferedReader br = new BufferedReader(new FileReader(filename)); // Create file reader
		br.readLine(); // Skip first line (column headers)
		String line = br.readLine(); // Read second line
		
		int totalCount = 1; // Start total count at 1 since first line already read
		int successCount = 0;
		int failCount = 0;

		BufferedWriter bw = new BufferedWriter(new FileWriter(filenameBase + "-bad.csv")); // Create file writer

		// While current line is valid, implement commands and then try next line
		while (line != null) {
			String[] s = line.split(","); // Split line by comma

			// If line has length of 10
			if (s.length == 10) {
				insert(s[0], s[1], s[2], s[3], s[4], s[5], s[6], s[7], s[8], s[9]); // Insert data into table
				successCount++;
			} else {
				bw.write(line); // Write data to file
				bw.newLine(); // Go to new line
				failCount++;
			}

			line = br.readLine(); // Read in new line
			
			// If line read isn't null
			if (line != null) {
				totalCount ++;
			}
		}

		br.close(); // Close file reader

		bw.close(); // Close file writer

		closeConnection(); // Close database connection
		
		bw = new BufferedWriter(new FileWriter(filenameBase + ".log")); // Create file writer
		
		bw.write("Total records received: " + totalCount);
		bw.newLine();
		bw.write("Records successful: " + successCount);
		bw.newLine();
		bw.write("Records failed: " + failCount);
		bw.newLine();
		
		System.out.println("Program has completed!");
	}
}
