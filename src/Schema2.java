import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class Schema2 {
	
//	CREATE TABLE Employee(Fname CHAR(20), Minit CHAR(10), Lname CHAR(20), ssn INT PRIMARY KEY, Bdate date, address CHAR(20), sex CHARACTER(1), salary INT, Super_snn INT REFERENCES Employee(ssn), dno INT);

	 public static long insertEmployee(String Fname, String Minit,String Lname,int ssn, Date Bdate, String address, String sex, int salary, int superSSN, int dno, Connection conn) {
         String SQL = "INSERT INTO Employee(Fname,Minit,Lname,ssn,Bdate,address,sex,salary,Super_snn,dno) "
                 + "VALUES(?,?,?,?,?,?,?,?,?,?);";
      
         long id = 0;
        try{
        	 conn.setAutoCommit(false);
            PreparedStatement pstmt = conn.prepareStatement(SQL,
                    Statement.RETURN_GENERATED_KEYS);
     
                pstmt.setString(1, Fname);
                pstmt.setString(2, Minit);
                pstmt.setString(3, Lname);
                pstmt.setInt(4, ssn);
                pstmt.setDate(5, Bdate);
                pstmt.setString(6, address);
                pstmt.setString(7, sex);
                pstmt.setInt(8, salary);
                pstmt.setInt(9, superSSN);
                pstmt.setInt(10, dno);

             int affectedRows = pstmt.executeUpdate();
             System.out.println("Number of affected rows is " + affectedRows);
             // check the affected rows 
             if (affectedRows > 0) {
                 // get the ID back
                 try (ResultSet rs = pstmt.getGeneratedKeys()) {
//                	 System.out.println(rs.next());
                     if (rs.next()) {
                         id = rs.getLong(4);
                         pstmt.close();
                         conn.commit();
                     }
                 } catch (SQLException ex) {
                	 ex.printStackTrace();
                     System.out.println(ex.getMessage());
                 }
             }
         } catch (SQLException ex) {
             System.out.println(ex.getMessage());
             ex.printStackTrace();
         }
         return id;
     }
//	 CREATE TABLE Department(Dname CHAR(20), Dnumber INT PRIMARY KEY, Mgr_snn int REFERENCES employee, Mgr_start_date date );

	 public static long insertDepartment(String Dname, int Dnumber,int MgrSSN, Date startDate, Connection conn) {
         String SQL = "INSERT INTO Department(Dname,Dnumber,Mgr_snn,Mgr_start_date) "
                 + "VALUES(?,?,?,?);";
      
         long id = 0;
        try{
        	 conn.setAutoCommit(false);
            PreparedStatement pstmt = conn.prepareStatement(SQL,
                    Statement.RETURN_GENERATED_KEYS);
     
                pstmt.setString(1, Dname);
                pstmt.setInt(2, Dnumber);
                pstmt.setInt(3, MgrSSN);
                pstmt.setDate(4, startDate);
                

             int affectedRows = pstmt.executeUpdate();
             System.out.println("Number of affected rows is " + affectedRows);
             // check the affected rows 
             if (affectedRows > 0) {
                 // get the ID back
                 try (ResultSet rs = pstmt.getGeneratedKeys()) {
//                	 System.out.println(rs.next());
                     if (rs.next()) {
                         id = rs.getLong(2);
                         pstmt.close();
                         conn.commit();
                     }
                 } catch (SQLException ex) {
                	 ex.printStackTrace();
                     System.out.println(ex.getMessage());
                 }
             }
         } catch (SQLException ex) {
             System.out.println(ex.getMessage());
             ex.printStackTrace();
         }
         return id;
     }
//	 CREATE TABLE Dept_locations(Dnumber integer REFERENCES Department, Dlocation CHAR(20), PRIMARY KEY(Dnumber,Dlocation));
	 public static long insertDeptLocations(int Dnumber,String Dlocation, Connection conn) {
         String SQL = "INSERT INTO Dept_locations(Dnumber,Dlocation) "
                 + "VALUES(?,?);";
      
         long id = 0;
        try{
        	 conn.setAutoCommit(false);
            PreparedStatement pstmt = conn.prepareStatement(SQL,
                    Statement.RETURN_GENERATED_KEYS);
     
                pstmt.setString(2, Dlocation);
                pstmt.setInt(1, Dnumber);
                
                

             int affectedRows = pstmt.executeUpdate();
             System.out.println("Number of affected rows is " + affectedRows);
             // check the affected rows 
             if (affectedRows > 0) {
                 // get the ID back
                 try (ResultSet rs = pstmt.getGeneratedKeys()) {
//                	 System.out.println(rs.next());
                     if (rs.next()) {
                         id = rs.getLong(1);
                         pstmt.close();
                         conn.commit();
                     }
                 } catch (SQLException ex) {
                	 ex.printStackTrace();
                     System.out.println(ex.getMessage());
                 }
             }
         } catch (SQLException ex) {
             System.out.println(ex.getMessage());
             ex.printStackTrace();
         }
         return id;
     }

//	 CREATE TABLE Project(Pname CHAR(20), Pnumber INT PRIMARY KEY, Plocation CHAR(50), Dnumber INT REFERENCES Department);
	 public static long insertProject(String Pname, int Pnumber,String pLocation, int Dnumber, Connection conn) {
         String SQL = "INSERT INTO Project(Pname,Pnumber,Plocation,Dnumber) "
                 + "VALUES(?,?,?,?);";
      
         long id = 0;
        try{
        	 conn.setAutoCommit(false);
            PreparedStatement pstmt = conn.prepareStatement(SQL,
                    Statement.RETURN_GENERATED_KEYS);
     
                pstmt.setString(1, Pname);
                pstmt.setInt(2, Pnumber);
                pstmt.setString(3, pLocation);
                pstmt.setInt(4, Dnumber);
                

             int affectedRows = pstmt.executeUpdate();
             System.out.println("Number of affected rows is " + affectedRows);
             // check the affected rows 
             if (affectedRows > 0) {
                 // get the ID back
                 try (ResultSet rs = pstmt.getGeneratedKeys()) {
//                	 System.out.println(rs.next());
                     if (rs.next()) {
                         id = rs.getLong(2);
                         pstmt.close();
                         conn.commit();
                     }
                 } catch (SQLException ex) {
                	 ex.printStackTrace();
                     System.out.println(ex.getMessage());
                 }
             }
         } catch (SQLException ex) {
             System.out.println(ex.getMessage());
             ex.printStackTrace();
         }
         return id;
     }
//	 CREATE TABLE Works_on(Essn int REFERENCES Employee, Pno int REFERENCES Project, Hours int, PRIMARY KEY(Essn,Pno));

	 public static long insertWorksOn(int Essn,int pNo, int hours, Connection conn) {
         String SQL = "INSERT INTO Works_on(Essn,Pno,Hours) "
                 + "VALUES(?,?,?);";
      
         long id = 0;
        try{
        	 conn.setAutoCommit(false);
            PreparedStatement pstmt = conn.prepareStatement(SQL,
                    Statement.RETURN_GENERATED_KEYS);
     
                pstmt.setInt(2, pNo);
                pstmt.setInt(1, Essn);
                pstmt.setInt(3, hours);

                

             int affectedRows = pstmt.executeUpdate();
             System.out.println("Number of affected rows is " + affectedRows);
             // check the affected rows 
             if (affectedRows > 0) {
                 // get the ID back
                 try (ResultSet rs = pstmt.getGeneratedKeys()) {
//                	 System.out.println(rs.next());
                     if (rs.next()) {
                         id = rs.getLong(1);
                         pstmt.close();
                         conn.commit();
                     }
                 } catch (SQLException ex) {
                	 ex.printStackTrace();
                     System.out.println(ex.getMessage());
                 }
             }
         } catch (SQLException ex) {
             System.out.println(ex.getMessage());
             ex.printStackTrace();
         }
         return id;
     }
//	 CREATE TABLE Dependent(Essn INT REFERENCES Employee, Dependent_name CHAR(20), sex CHARACTER(1), Bdate date, Relationship CHAR(20), PRIMARY KEY(Essn, Dependent_name));
	 public static long insertDependent(int Essn, String dependentName,String sex, Date Bdate,String relationship, Connection conn) {
         String SQL = "INSERT INTO Dependent(Essn,Dependent_name,sex,Bdate,Relationship) "
                 + "VALUES(?,?,?,?,?);";
      
         long id = 0;
        try{
        	 conn.setAutoCommit(false);
            PreparedStatement pstmt = conn.prepareStatement(SQL,
                    Statement.RETURN_GENERATED_KEYS);
     
                pstmt.setInt(1, Essn);
                pstmt.setString(2, dependentName);
                pstmt.setString(3, sex);
                pstmt.setDate(4, Bdate);
                pstmt.setString(5, relationship);


             int affectedRows = pstmt.executeUpdate();
             System.out.println("Number of affected rows is " + affectedRows);
             // check the affected rows 
             if (affectedRows > 0) {
                 // get the ID back
                 try (ResultSet rs = pstmt.getGeneratedKeys()) {
//                	 System.out.println(rs.next());
                     if (rs.next()) {
                         id = rs.getLong(1);
                         pstmt.close();
                         conn.commit();
                     }
                 } catch (SQLException ex) {
                	 ex.printStackTrace();
                     System.out.println(ex.getMessage());
                 }
             }
         } catch (SQLException ex) {
             System.out.println(ex.getMessage());
             ex.printStackTrace();
         }
         return id;
     }
	 
	 /////////////////////////////////////////////// Data Population Methods //////////////////////////////////////////////////////////////
	 @SuppressWarnings("deprecation")
	public static void populateEmployee(Connection conn) {
		 
		 // Insert 600 employees in department 1 with salary = 70k and lName = "employee1"
		 for (int i = 1; i <= 600; i++) {
			if (insertEmployee("EMPLOYEE_" + i, "M" + i,"employee1", i, new Date(22,1,1999), "address" + i ,"M",70000,i,1, conn) == 0) {
				System.err.println("insertion of record " + i + " failed");
				break;
			} else
				System.out.println("insertion was successful");
		}
		 
		 // Insert 400 employees in department 5 with salary = 60k
		 for (int i = 601; i <= 1000; i++) {
			if (insertEmployee("EMPLOYEE_" + i, "M" + i,"EMPLOYEE_" + i, i, new Date(22,1,1999), "address" + i ,"M",60000,i,5, conn) == 0) {
				System.err.println("insertion of record " + i + " failed");
				break;
			} else
				System.out.println("insertion was successful");
		}
		
		 // Insert 1000 employees for each department
		 // First 100 department employees make 50k while the rest make 20k
		 
		 for (int i = 1; i <= 150; i++) {
			 for (int j = 1; j <= 1000; j++) {
				if (insertEmployee("EMPLOYEE_" + j + "_" + i,
					"M_" + j + "_" + i,
					"EMPLOYEE_" + j + "_" + i,
					i*1000 + j, new Date(22,1,1999), "address" ,"M", 
					i<=100 ? 50000 : 20000,
					i*1000 + j,
					i, conn) == 0) {
					System.err.println("insertion of record " + i + " failed");
					break;
				} else
					System.out.println("insertion was successful"); 
			 }
		}

	 }
	 
	 @SuppressWarnings("deprecation")
	public static void populateDepartment(Connection conn) {
		 // Inserts 150 departments 
		 // Naming: DEPARTMENT_X
		 
		 for (int i = 1; i <= 150; i++) {
				if (insertDepartment("DEPARTMENT_" + i, i,i,new Date(1,1,1990), conn) == 0) {
					System.err.println("insertion of record " + i + " failed");
					break;
				} else
					System.out.println("insertion was successful");
			}
	 }
	 
	public static void populateDeptLocations(Connection conn) {
		// Inserts 150 locations for the 150 departments
		// Naming: LOCATION_X
		
		 for (int i = 1; i <= 150; i++) {
				if (insertDeptLocations(i, "LOCATION_" + i, conn) == 0) {
					System.err.println("insertion of record " + i + " failed");
					break;
				} else
					System.out.println("insertion was successful");
			}
	 }
	
	public static void populateProject(Connection conn) {
		// Insert 9200 projects
		// Naming PROJECT_X
		
		 for (int i = 1; i <= 9200; i++) {
				if (insertProject("PROJECT_" + i, i,"Location" + i, i%150==0 ? 150 : i%150, conn) == 0) {
					System.err.println("insertion of record " + i + " failed");
					break;
				} else
					System.out.println("insertion was successful");
			}
	 }
	
	public static void populateWorksOn(Connection conn) {
		 for (int i = 1; i <= 9200; i++) {
				if (insertWorksOn(i, i, i, conn) == 0) {
					System.err.println("insertion of record " + i + " failed");
					break;
				} else
					System.out.println("insertion was successful");
			}
	 }
	
	@SuppressWarnings("deprecation")
	public static void populateDependent(Connection conn) {
		// 600 dependents for the first 600 employees with the same first name and sex (M)
		// This satisfies queries 4 & 5
		
		 for (int i = 1; i <= 600; i++) {
			if (insertDependent(i, "EMPLOYEE_" + i, "M",new Date(1,1,1999),"child", conn) == 0) {
				System.err.println("insertion of record " + i + " failed");
				break;
			} else
				System.out.println("insertion was successful");
		}
	 }
	
	public static void insertSchema2(Connection connection) {
		populateEmployee(connection);
		populateDepartment(connection);
		populateDeptLocations(connection);
		populateProject(connection);
		populateWorksOn(connection);
		populateDependent(connection);
	}
		
	public static void main(String[] argv) {

		System.out.println("-------- PostgreSQL "
				+ "JDBC Connection Testing ------------");

		try {

			Class.forName("org.postgresql.Driver");

		} catch (ClassNotFoundException e) {

			System.out.println("Where is your PostgreSQL JDBC Driver? "
					+ "Include in your library path!");
			e.printStackTrace();
			return;

		}

		System.out.println("PostgreSQL JDBC Driver Registered!");

		Connection connection = null;

		try {

			connection = DriverManager.getConnection(
					"jdbc:postgresql://127.0.0.1:5432/schema2", "postgres",
					"0000");
            insertSchema2(connection);
			

		} catch (SQLException e) {

			System.out.println("Connection Failed! Check output console");
			e.printStackTrace();
			return;

		}

		if (connection != null) {
			System.out.println("You made it, take control your database now!");
		} else {
			System.out.println("Failed to make connection!");
		}
	}
}