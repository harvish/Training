import java.sql.*;
import java.io.*;

public class JdbcSample {

	private static Statement st;
	
	JdbcSample() throws Exception
	{
		Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
		Connection con= DriverManager.getConnection("jdbc:odbc:harv");
		st= con.createStatement();
	}
	static boolean check(String n, int id) throws Exception
	{
		String sql="Select * from login where "+(id==1?"user":"email")+"='"+n+"'";
		ResultSet res=st.executeQuery(sql);
		if(res.next()) return true;
		else return false;
	}
	public static void main(String[] args) throws Exception {
		
		new JdbcSample();
		BufferedReader in= new BufferedReader(new InputStreamReader(System.in));
		String uname,pass,email;
		uname=pass=email=null;
		String sql="select * from login";
		char t;
		int opt=0;
		l1:
		do{
			System.out.print("\n--------------------------------\nSelect command:\n1 to update\n2 to view \n3 to exit\nEnter Choice : ");
			opt=Integer.parseInt(in.readLine());
			switch(opt-1)	{
			case 0:	System.out.print("\nUser Name : "); uname=in.readLine(); if(check(uname,1)) {System.out.println("Already Exists!");continue l1;}
					System.out.print("\nPassword : "); pass=in.readLine(); //while((t=(char)in.read())!='\n') {System.out.print("*"); pass+=t;} 
					System.out.print("\nEmail : "); email=in.readLine();  if(check(email,3)) {System.out.println("Already Exists!");continue l1;}
					String sql1="insert into login (user,pass,email) values('"+uname+"','"+pass+"','"+email+"')";
					if((st.executeUpdate(sql1))>0) System.out.println("Successfully added :) ");
					else System.out.println("Unsuccesful :( ");
					break;
			case 1: System.out.println("\nFull Table view : ");
					ResultSet res= st.executeQuery(sql);
					while(res.next())	{
						System.out.println(res.getString("user") + "\t:\t" + res.getString("pass")+ "\t:\t" +res.getString("email"));
					}
					break;
			default: System.out.print("Exiting! Thank You :)");
			}
		} while(opt<=2);
	}
}
