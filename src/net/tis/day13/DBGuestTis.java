package net.tis.day13;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;
import java.util.Scanner;


public class DBGuestTis { 
		Connection CN=null;//DB���������� user/pwd���, CN�����ؼ� ��ɾ����
		Statement ST=null;//�����θ�ɾ� ST=CN.createStatement(X);
		PreparedStatement PST=null; //�����θ�ɾ� PST=CN.prepareStatememt(msg)
		CallableStatement CST=null; //storedprocedure����
		ResultSet RS=null;//RS=ST.executeQuery("select~") ; ��ȸ����� RS���
		String msg="" ; 
		int Gtotal=0; //��ü���ڵ尹��
		Scanner sc = new Scanner(System.in);
			
	 public DBGuestTis() {
		 try{
	     Class.forName("oracle.jdbc.driver.OracleDriver"); //����̺�ε�
	     String url="jdbc:oracle:thin:@127.0.0.1:1521:XE" ;
	     CN=DriverManager.getConnection(url,"system","1234");
	     System.out.println("����Ŭ���Ἲ��success ������");
	     ST=CN.createStatement();
		 }catch (Exception e) {	
			 System.out.println(e);
		 }
	 }//������
	 
	public static void main(String[] args) {
		DBGuestTis gg = new DBGuestTis();
				
		Scanner scin = new Scanner(System.in);
		while(true) {
			System.out.print("\n1���  2��ü���  9����>>> ");
			int sel=scin.nextInt();
			if(sel==1){gg.guestInsert();}
			else if(sel==2){gg.guestSelectAll(); }
			else if(sel==9){ gg.myexit(); break; }
		}
		scin.close();
	}//main end
	
	

	public void guestSelectAll( ) {//��ü���
		try {
		msg="select * from guest  order by sabun" ;
		System.out.println(msg);
		RS = ST.executeQuery(msg);
		while(RS.next()==true) {
		  System.out.println(RS.getInt("sabun")
				  +"\t" + RS.getString("name")
				  +"\t" + RS.getString("title"));
		}
		System.out.println("=============================================");
		}catch (Exception e) { System.out.println("��ü��ȸ����");}
	}//end--------------------
	

	
	public void myexit() {
		System.out.println("7/13 ������ ���α׷��� �����մϴ�");
		System.exit(1);
	}//end--------------------
	
	
	public void guestInsert() {
		try{
			System.out.println("����Է�>>");
			int s=Integer.parseInt(sc.nextLine());	 
			System.out.println("�̸��Է�>>");
			String n=sc.nextLine(); 
			System.out.println("�����Է�>>");
			String t=sc.nextLine();   
			System.out.println("�޿��Է�>>");
			int p=Integer.parseInt(sc.nextLine()); 
			int h=0;//ī��Ʈ
			System.out.println("�����Է�>>");
			String e=sc.nextLine() ;
			
			CST=CN.prepareCall("{call guest_sp_insert(?, ?, ?, ?, ?, ?) }");
			CST.setInt(1,s);	
			CST.setString(2, n);
			CST.setString(3, t);
			CST.setInt(4, p);
			CST.setInt(5, h);
			CST.setString(6, e);
			CST.executeUpdate() ;
			 System.out.println("Stored procedure���强��"); 
		}catch(Exception ex) { System.out.println(ex + "Stored procedure�������"); }
	}//end--------------------
}/////////////////////////////////////////////class END





