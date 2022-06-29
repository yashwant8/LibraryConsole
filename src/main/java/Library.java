import java.util.*;
import java.util.Date;

import javax.rmi.CORBA.Util;

import java.sql.*;

public class Library {
	public static void main(String[] arg) throws ClassNotFoundException {
		try{  
			Class.forName("com.mysql.jdbc.Driver");  
			Connection con=DriverManager.getConnection(  
			"jdbc:mysql://localhost:3306/liberary","root","yash3094");
			Statement stmt=con.createStatement();  
			ResultSet rs=stmt.executeQuery("select * from book");  
		}catch(Exception e){ System.out.println(e);}  
		  
		
		Scanner scan = new Scanner(System.in);
		while(true) {
		System.out.println(" ");
		System.out.println(" ");
		System.out.println("Enter your choice : ");
		System.out.println("Press 1 to login as Admin");
		System.out.println("Press 2 to Search Book");
		System.out.println("Press 3 to view issued book");
		System.out.println("Press 4 to get list of all book");
		String bookid,bookname,bookaut;
		
		int choice = scan.nextInt();
		switch(choice)
		{
		case 1 : 
			System.out.println(" ");
			System.out.println(" ");
			System.out.println("Enter your choice : ");
			System.out.println("Press 1 to add book");
			System.out.println("Press 2 to update a book");
			System.out.println("Press 3 to delete a book");
			System.out.println("Press 4 to issue a book");
			System.out.println("Press 5 to return a book");
			System.out.println("Press 6 to view list of issued books to particular Student");
			System.out.println("Press 7 to view list of aLL issued books");
			System.out.println("Press 8 to Go Back");
			
			
			int ca = scan.nextInt();
			switch(ca) {
			case 1:
				System.out.println("Enter bookid : ");
				bookid=scan.next();
				scan.nextLine();
				System.out.println("Enter book_name");
				bookname=scan.nextLine();
				
				System.out.println("Enter book author name ");
				bookaut=scan.nextLine();
				//scan.nextLine();
				addbook(bookid,bookname,bookaut);
				break;
			
			case 2:
				System.out.println("Enter your choice");
				System.out.println("Press 1 to update book id");
				System.out.println("Press 2 to update book name");
				System.out.println("Press 3 to update author name");
				int ch = scan.nextInt();
				switch(ch) {
				case 1:
					System.out.println("Enter book name ");
					scan.nextLine();
					bookname=scan.nextLine();
					System.out.println("Enter new book id");
					//scan.next();
					bookid=scan.next();
					updateid(bookid,bookname);
					break;
				case 2:
					System.out.println("Enter book id");
					bookid=scan.next();
					System.out.println("Enter new name");
					scan.nextLine();
					bookname = scan.nextLine();
					updatename(bookid,bookname);
					break;
				case 3:
					System.out.println("Enter book id");
					bookid=scan.next();
					System.out.println("Enter new author name");
					scan.nextLine();
					bookaut=scan.nextLine();
					updateauthor(bookid,bookaut);
					break;
				}
			
				break;
			
			case 3:
				System.out.println("Enter book id to delete book ");
				bookid = scan.next();
				deletebook(bookid);
				break;
			
			case 4:
				System.out.println("Enter Student ID");
				String sid=scan.next();
				int x=num(sid);
				//System.out.println(x);
				if(x==4) {
					System.out.println("Student has issued four books already");
				}
				else {
					
					System.out.println("Enter book ID");
					bookid = scan.next();
					//System.out.println(isissued(bookid));
					if(isissued(bookid)) {
						System.out.println("This book is issued by another student");
					}
					else {issuebook(sid,bookid);}
				}
				//issuebook();
				break;
			case 5:
				System.out.println("Enter Student ID");
				String idd = scan.next();
				int xx = num(idd);
				if(xx==0) {
					System.out.println("Student Has not issued any book");
				}
				else {
					
					updatefine(idd);
					System.out.println("Student Has isssued these books");
					viewissue(idd);
					int fi = fine(idd);
					if(fi!=0) {System.out.println("Total fine of Student : "+fi);}
					System.out.println("Enter book ID to return book");
					bookid = scan.next();
					if(calbook(bookid)!=0) {System.out.println("Fine to return this book is : "+calbook(bookid));} else {System.out.println("No Fine for this book ");}
					returnbook(idd,bookid);
				}
				break;
			case 6:
				System.out.println("Enter Student ID");
				 String stid = scan.next();
				 viewissue(stid);
				 break;
			case 7:
				allissuedbook();
				break;
			case 8:
				break;
			default:
				System.out.println("NOT VALID CHOICE : ");
			}
		break;
			
		
		case 2 :
			
			System.out.println("Enter your choice : ");
			System.out.println("Press 1 to search using book id");
			System.out.println("Press 2 to search using book name ");
			System.out.println("Press 3 to search using author name");
			int se= scan.nextInt();
			switch(se) {
			case 1:
				System.out.println("Enter book Id");
				bookid=scan.next();
				searchbyid(bookid);
				break;
			case 2:
				System.out.println("Enter book name");
				scan.nextLine();
				bookname=scan.nextLine();
				searchbyname(bookname);
				break;
			case 3:
				System.out.println("Enter author name ");
				scan.nextLine();
				bookaut=scan.nextLine();
				searchbyauthor(bookaut);
				break;
			default:
				System.out.println("NOT A VALID CHOICE !!");
			}
			break;
		case 3:
			 System.out.println("Enter your ID");
			 String stid = scan.next();
			 updatefine(stid);
			 viewissue(stid);
			 break;
		case 4:
			viewallbook();
			break;
		
		default:
			System.out.println("NOT A VALID CHOICE!!!");
		}
	}
	}
	
	
	 public static int num(String id){
		 int x = 0;
		 try {
			 Class.forName("com.mysql.jdbc.Driver");  
				Connection connect=DriverManager.getConnection(  
				"jdbc:mysql://localhost:3306/liberary","root","yash3094"); 
				
		 Statement statement = connect.createStatement();
		 ResultSet resultSet = statement.executeQuery("select count(*) from student where student_id = '"+id+"'");
		 
		 while (resultSet.next()) {
			 x= resultSet.getInt(1);
		 }
		 
		} catch (Exception e) {System.out.println("Not a Valid Student Id");}
		return x; 
	}

	public static int fine(String id) {
		int f=0;
		int x = 0;
		try {
			 Class.forName("com.mysql.jdbc.Driver");  
				Connection connect=DriverManager.getConnection(  
				"jdbc:mysql://localhost:3306/liberary","root","yash3094"); 
				
		 Statement statement = connect.createStatement();
		 ResultSet resultSet = statement.executeQuery("select sum(fine) from student where student_id = '"+id+"'");
		 
		 while (resultSet.next()) {
			 x= resultSet.getInt(1);
		 }
		 
		} catch (Exception e) {System.out.println("Not a Valid Student Id");}
		return x; 
	}
	public static boolean isissued(String bid) {
		boolean fl = false;
		
		try{  
			String str="";
			Class.forName("com.mysql.jdbc.Driver");  
			Connection con=DriverManager.getConnection(  
			"jdbc:mysql://localhost:3306/liberary","root","yash3094");
			Statement stmt=con.createStatement();  
			ResultSet resultSet=stmt.executeQuery("select book_status from book where book_id= '"+bid+"'");
			while (resultSet.next()) {
				 str= resultSet.getString(1);
			 }
			if(str.equals("issued")) {
				fl=true;
			}
			else {
				fl=false;
			}
		}catch(Exception e){ System.out.println("Not a Valid Book Id");} 
		
		return fl;
		
	}
	public static void addbook(String bi,String bn,String ba) {
		try{  
			Class.forName("com.mysql.jdbc.Driver");  
			Connection con=DriverManager.getConnection(  
			"jdbc:mysql://localhost:3306/liberary","root","yash3094");  
			Statement stmt=con.createStatement();
			String query = " insert into book (book_id,book_name,author_name,book_status)"
			        + " values (?, ?, ?, ?)";
			PreparedStatement ps =  con.prepareStatement(query);
			ps.setString(1, bi);
			ps.setString(2, bn);
			ps.setString(3, ba);
			ps.setString(4, "available");
			ps.execute();
			System.out.println("book added succesfully !");
		}catch(Exception e){ System.out.println("Book with given id already exists !");} 
		
	}
	public static void viewallbook() {
		try{  
			Class.forName("com.mysql.jdbc.Driver");  
			Connection con=DriverManager.getConnection(  
			"jdbc:mysql://localhost:3306/liberary","root","yash3094");  
			//here sonoo is database name, root is username and password  
			Statement stmt=con.createStatement();  
			ResultSet rs=stmt.executeQuery("select * from book"); 
			System.out.println("---------------------------------------------------------------------------------");
			System.out.printf("| %12s %20s %15s %10s   |\n","Book_id","Book_name","Author","Status","  |\n");
			System.out.println("---------------------------------------------------------------------------------");
			while(rs.next())  
				System.out.printf("| %12s %20s %15s %10s   |\n",rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),"  |\n");  
			System.out.println("---------------------------------------------------------------------------------");
		}catch(Exception e){ System.out.println(e);}  
	}
	public static void deletebook(String id) {
		try{  
			Class.forName("com.mysql.jdbc.Driver");  
			Connection con=DriverManager.getConnection(  
			"jdbc:mysql://localhost:3306/liberary","root","yash3094"); 
			Statement stmt=con.createStatement();
			stmt.executeUpdate("delete from book where book_id = '"+id+"'");
			System.out.println("Deleted SuccesFully!!");
			con.close();
		}catch(Exception e){ System.out.println("book does not exists !!");}
	}
	public static void allissuedbook() {
		try{  
			Class.forName("com.mysql.jdbc.Driver");  
			Connection con=DriverManager.getConnection(  
			"jdbc:mysql://localhost:3306/liberary","root","yash3094"); 
			Statement stmt=con.createStatement();  
			ResultSet rs=stmt.executeQuery("select book.*,student.student_id,student.issue_date,student.due_date,student.fine from book inner join student on book.book_id=student.book_id;");  
			System.out.println("------------------------------------------------------------------------------------------------------------");
			System.out.printf("| %12s %22s %15s %10s %10s %10s %10s %5s  |\n","Book_id","Book_name","Author","Status","Student_id","Issue_date","Due_date","fine","  |\n");
			System.out.println("------------------------------------------------------------------------------------------------------------");
			while(rs.next())  
			//System.out.println(" |  "+rs.getString(1)+"\t"+rs.getString(2)+"\t"+rs.getString(3)+"\t"+rs.getString(4)+"\t"+rs.getString(5)+"\t"+rs.getDate(6)+"\t"+rs.getDate(7)+"\t"+rs.getInt(8)); 
			System.out.printf("| %12s %22s %15s %10s %10s %10s %10s %5s  |\n",rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getDate(6),rs.getDate(7),rs.getInt(8),"  |\n");
			System.out.println("------------------------------------------------------------------------------------------------------------");
			con.close();
		}catch(Exception e){ System.out.println(e);} 
		
	}
	public static int calbook(String bid) {
		int f=0;
		int x = 0;
		try {
			 Class.forName("com.mysql.jdbc.Driver");  
				Connection connect=DriverManager.getConnection(  
				"jdbc:mysql://localhost:3306/liberary","root","yash3094"); 
				
		 Statement statement = connect.createStatement();
		 ResultSet resultSet = statement.executeQuery("select fine from student where book_id = '"+bid+"'");
		 
		 while (resultSet.next()) {
			 x= resultSet.getInt(1);
		 }
		 
		} catch (Exception e) {System.out.println("Not a Valid Student Id");}
		return x; 
	}
	public static void issuebook(String sid,String bid) {
		try{  
			Class.forName("com.mysql.jdbc.Driver");  
			Connection con=DriverManager.getConnection(  
			"jdbc:mysql://localhost:3306/liberary","root","yash3094");  
			Statement stmt=con.createStatement();
			String query = " insert into student (student_id,book_id,issue_date,due_date,fine)"
			        + " values (?, ?, ?, ?,?)";
			PreparedStatement ps =  con.prepareStatement(query);
			ps.setString(1, sid);
			ps.setString(2, bid);
			ps.setDate(3, getCurrentDate());
			ps.setDate(4, getDate(getCurrentDate()));
			ps.setInt(5, 0);
			ps.execute();
			stmt.executeUpdate("update book set book_status = 'issued' where book_id = '"+bid+"'");
			//System.out.println("book added succesfully !");
		}catch(Exception e){ System.out.println("This Book Does Not Exists!");} 
	}
	public static java.sql.Date getduedate(String bid) {
		Date date=new Date();
		
		 try {
			 Class.forName("com.mysql.jdbc.Driver");  
				Connection connect=DriverManager.getConnection(  
				"jdbc:mysql://localhost:3306/liberary","root","yash3094"); 
				
		 Statement statement = connect.createStatement();
		 ResultSet resultSet = statement.executeQuery("select due_date from student where book_id = '"+bid+"'");
		 
		 while (resultSet.next()) {
			 date= resultSet.getDate(1);
		 }
		 
		} catch (Exception e) {}
		 return new java.sql.Date(date.getTime());
	}
	public static void insertfine(String id,java.sql.Date due){
		int fine = 0;
		Date today = new Date();
		if(today.after(due)) {
			long dd = today.getTime()-due.getTime();
			//System.out.println(dd);
			long diff = dd/(24*60*60*1000) ;
			//SSystem.out.println(diff);
			fine=((int)diff)*5;
			try{  
				Class.forName("com.mysql.jdbc.Driver");  
				Connection con=DriverManager.getConnection(  
				"jdbc:mysql://localhost:3306/liberary","root","yash3094");  
				Statement stmt=con.createStatement();
				stmt.executeUpdate("update student set fine = "+fine+" where book_id = '"+id+"'");
				
			}catch(Exception e){ System.out.println(e);}
		}
		
	}
	public static void updatefine(String sid) {
		try{  
			Class.forName("com.mysql.jdbc.Driver");  
			Connection con=DriverManager.getConnection(  
			"jdbc:mysql://localhost:3306/liberary","root","yash3094"); 
			Statement stmt=con.createStatement();
			String query = "select book_id from student where student_id Like '"+sid+"'";
			ResultSet rs=stmt.executeQuery(query);
			while(rs.next())  
				  insertfine(rs.getString(1),getduedate(rs.getString(1)));
			
			con.close();
		}catch(Exception e){ System.out.println("Book with given name does not exits");} 
	}
	public static Date addDays(Date date, int days) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DATE, days);
        return new Date(c.getTimeInMillis());
    }
	private static java.sql.Date getCurrentDate() {
	    Date today = new Date();
	    return new java.sql.Date(today.getTime());
	}
	private static java.sql.Date getDate(java.sql.Date dt) {
	    Date today = addDays(dt, 15);
	    return new java.sql.Date(today.getTime());
	}
	public static void searchbyid(String id) {
		try{  
			Class.forName("com.mysql.jdbc.Driver");  
			Connection con=DriverManager.getConnection(  
			"jdbc:mysql://localhost:3306/liberary","root","yash3094"); 
			Statement stmt=con.createStatement();
			String query = "select * from book where book_id Like '"+id+"'";
			ResultSet rs=stmt.executeQuery(query);
			System.out.println("---------------------------------------------------------------------------------");
			System.out.printf("| %12s %20s %15s %10s   |\n","Book_id","Book_name","Author","Status","  |\n");
			System.out.println("---------------------------------------------------------------------------------");
			while(rs.next())  
				System.out.printf("| %12s %20s %15s %10s   |\n",rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),"  |\n");  
			System.out.println("---------------------------------------------------------------------------------");
			con.close();
		}catch(Exception e){ System.out.println("Book with given Id does not exits");} 
		
	}
	public static void searchbyname(String bn) {
		try{  
			Class.forName("com.mysql.jdbc.Driver");  
			Connection con=DriverManager.getConnection(  
			"jdbc:mysql://localhost:3306/liberary","root","yash3094"); 
			Statement stmt=con.createStatement();
			String query = "select * from book where book_name Like '%"+bn+"%'";
			ResultSet rs=stmt.executeQuery(query);
			System.out.println("---------------------------------------------------------------------------------");
			System.out.printf("| %12s %20s %15s %10s   |\n","Book_id","Book_name","Author","Status","  |\n");
			System.out.println("---------------------------------------------------------------------------------");
			while(rs.next())  
				System.out.printf("| %12s %20s %15s %10s   |\n",rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),"  |\n");  
			System.out.println("---------------------------------------------------------------------------------");
			con.close();
		}catch(Exception e){ System.out.println("Book with given name does not exits");} 
		
	}
	public static void searchbyauthor(String ba) {
		try{  
			Class.forName("com.mysql.jdbc.Driver");  
			Connection con=DriverManager.getConnection(  
			"jdbc:mysql://localhost:3306/liberary","root","yash3094"); 
			Statement stmt=con.createStatement();
			String query = "select * from book where author_name Like '%"+ba+"%'";
			ResultSet rs=stmt.executeQuery(query);
			System.out.println("---------------------------------------------------------------------------------");
			System.out.printf("| %12s %20s %15s %10s   |\n","Book_id","Book_name","Author","Status","  |\n");
			System.out.println("---------------------------------------------------------------------------------");
			while(rs.next())  
				System.out.printf("| %12s %20s %15s %10s   |\n",rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),"  |\n");  
			System.out.println("---------------------------------------------------------------------------------");
			con.close();
		}catch(Exception e){ System.out.println("Book with given author not exists !");}
		
	}
	public static void viewissue(String id) {
		
		try{  
			Class.forName("com.mysql.jdbc.Driver");  
			Connection con=DriverManager.getConnection(  
			"jdbc:mysql://localhost:3306/liberary","root","yash3094");  
			//here sonoo is database name, root is username and password  
			Statement stmt=con.createStatement();  
			ResultSet rs=stmt.executeQuery("select * from student where student_id = '"+id+"'");  
			System.out.println("---------------------------------------------------------------------------------");
			System.out.printf("| %12s %23s %15s %10s %3s    |\n","Student_id","Book_id","Issue_date","Due_date","Fine");
			System.out.println("---------------------------------------------------------------------------------");
			/*while(rs.next()) {
				insertfine(rs.getString(2),getduedate(rs.getString(2)));
			}*/
			while(rs.next()) {
				
				System.out.printf("| %12s %23s %15s %10s %3d   |\n",rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getInt(5),"  |\n");  
			}
			System.out.println("---------------------------------------------------------------------------------");
		}catch(Exception e){ System.out.println(e);}  
	
	}
	public static void returnbook(String sid,String bid) {
		try{  
			Class.forName("com.mysql.jdbc.Driver");  
			Connection con=DriverManager.getConnection(  
			"jdbc:mysql://localhost:3306/liberary","root","yash3094"); 
			Statement stmt=con.createStatement();
			stmt.executeUpdate("delete from student where book_id = '"+bid+"'");
			stmt.executeUpdate("update book set book_status = 'available' where book_id = '"+bid+"'");
			con.close();
		}catch(Exception e){ System.out.println("Book with given Id does not exists !!");}
	}
	public static void updateid(String id,String bn) {
		try{  
			Class.forName("com.mysql.jdbc.Driver");  
			Connection con=DriverManager.getConnection(  
			"jdbc:mysql://localhost:3306/liberary","root","yash3094"); 
			Statement stmt=con.createStatement();
			stmt.executeUpdate("update book set book_id = '"+id+"' where book_name = '"+bn+"'");
			con.close();
		}catch(Exception e){ System.out.println("Enter Proper Name!!");}
		
	}
	public static void updatename(String id,String nam) {
		try{  
			Class.forName("com.mysql.jdbc.Driver");  
			Connection con=DriverManager.getConnection(  
			"jdbc:mysql://localhost:3306/liberary","root","yash3094"); 
			Statement stmt=con.createStatement();
			stmt.executeUpdate("update book set book_name = '"+nam+"' where book_id = '"+id+"'");
			con.close();
		}catch(Exception e){ System.out.println("Book with given Id does not exists !!");}
		
	}
	public static void updateauthor(String id,String aut) {
		try{  
			Class.forName("com.mysql.jdbc.Driver");  
			Connection con=DriverManager.getConnection(  
			"jdbc:mysql://localhost:3306/liberary","root","yash3094"); 
			Statement stmt=con.createStatement();
			stmt.executeUpdate("update book set author_name = '"+aut+"' where book_id = '"+id+"'");
			con.close();
		}catch(Exception e){ System.out.println("Book with given Id does not exists !!");}
		
	}
}
