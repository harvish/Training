import java.io.*;
//import java.sql.*;
public class IOPackageTry1 {
	public static void main(String[] args) throws IOException {
		String s="this is a sample string";
		byte b[]=s.getBytes();
		int c;
		ByteArrayInputStream bin= new ByteArrayInputStream(b);
		PushbackInputStream pb= new PushbackInputStream(bin);
		while((c=pb.read())!=-1)	{
			System.out.print((char)c);
			
		}
	}
}
