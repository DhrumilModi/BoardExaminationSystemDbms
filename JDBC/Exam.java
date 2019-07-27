/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package labexamination;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.Period;
import java.util.Scanner;

/**
 *
 * @author Dhrumil
 */
public class Exam {
   
           Scanner sc=new Scanner(System.in);     
    public static void main(String args[])
    {
        	Connection c = null;
                try
		{
                //         Load Postgresql Driver class
    		        Class.forName("org.postgresql.Driver");
			// Using Driver class connect to databased on localhost, port=5432, database=postgres, user=postgres, password=postgres. If cannot connect then exception will be generated (try-catch block)
			c = DriverManager.getConnection("jdbc:postgresql://localhost:5499/Exam","postgres", "postgres");
                        Scanner sc=new Scanner(System.in);
                        System.out.println("Opened database successfully");
			
			// Create instance of this class to call other methods
			Exam e = new Exam();
		while(true)
                {
                        System.out.println("1. View Tables");
                        System.out.println("2. Delete ");
                        System.out.println("3. Management Information System");
                        System.out.println("4. Exit");
                        
                        System.out.print("Choice :");
                        int ch1=sc.nextInt();
                       
               
                        switch(ch1)
                        {
                            case 1:
                                    e.view(e,c);
                                    break;
                            case 2:                        
                                    e.delete(e,c);
                                    break;
                            case 3:
                                    e.queries(e,c);
                                    break;
                            case 4:
                                    return;
                            default:
                                    System.out.println("Invalid Choice...");
                        
                        }
                    
                }
   //             c.close();
			
			
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			System.err.println(e.getClass().getName()+": "+e.getMessage());
			System.exit(0);
		}

    
    }
    void queries(Exam e,Connection c)
    {
                                   Scanner sc=new Scanner(System.in);
                                    System.out.println("1. Give names of schools in gujarat which are centers.");
                                    System.out.println("2. Details of examinar who are papersater for particular subject.(Economics)");
                                    System.out.println("3. Details of examiner who are supervisor of particular school");
                                    System.out.println("4. Count the number of student study outsidde there hometown");
                                    System.out.println("5. Count the number of students giving exam who is male and are from commerce field..");
                                    System.out.println("6. Exam");


                                    System.out.print("Choice :");
             try{
                                    int ch=sc.nextInt();
                                    switch(ch)
                                    {
                                        case 1:
                                                c.setAutoCommit(false);
                                                e.queryFirst(c);
                                                c.setAutoCommit(true);
                                                break;
                                        case 2:
                                                c.setAutoCommit(false);
                                                e.queryTwo(c);
                                                c.setAutoCommit(true);
                                                break;
                                        case 3:
                                                c.setAutoCommit(false);
                                                e.queryThird(c);
                                                c.setAutoCommit(true);
                                                break;
                                        case 4:
                                                c.setAutoCommit(false);
                                                e.queryFour(c);
                                                c.setAutoCommit(true);
                                                break;
                                        case 5:
                                                c.setAutoCommit(false);
                                                e.queryFive(c);
                                                c.setAutoCommit(true);
                                                break;
                                        case 6:
                                                c.setAutoCommit(false);
                         //                       e.showExam(c);
                                                c.setAutoCommit(true);
                                                break;

                                        default:
                                                System.out.println("Inavalid Choice...");

                                    }
             }catch(Exception e1)
             {
                    System.err.println( e1.getClass().getName()+": "+ e1.getMessage() );
                    System.exit(0);
            
             }

    }
    void queryFirst(Connection c)
    {
          	Statement stmt = null;
		try
		{
			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("select * from school join centers on centers.schoolid=school.schoolid where school.state='Gujarat';");
                        System.out.println("\t" + "pincode" + "\t" + " city "+ "\t" + "School"    + "\t");
			
			while(rs.next())
                        {
                        	String name=rs.getString("schoolname");
                                String city=rs.getString("city");
                                long pcode=rs.getLong("pincode");
                               
				System.out.println("\t" + pcode +"\t" + city + "\t" + name + "\t");
			
                        }
			stmt.close();
			System.out.println("Table Queried successfully");
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			System.err.println(e.getClass().getName()+": "+e.getMessage());
			System.exit(0);
		}
    
    
    }
    void queryTwo(Connection c)
    {
          	Statement stmt = null;
		try
		{
			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("select * from examinar join exam on examinar.examid=exam.examid join subject on subject.subjectid=exam.subjectid AND subject.subject_name='Economics'where issetter=true;");
                        System.out.println("\t" + "phone" + "\t\t" + " gen " + "\t" + " email "+ "\t\t\t" + "name" + "\t");
			
			while(rs.next())
                        {
                                long phone=rs.getLong("phone");
                                String gen=rs.getString("gender");
                                String email=rs.getString("email");
                                String name=rs.getString("fname")+" "+rs.getString("mname")+" "+rs.getString("lname");
                              
                               
				System.out.println("\t" + phone +"\t" + gen + "\t" + email + "\t\t" +name);
			
                        }
			stmt.close();
			System.out.println("Table Queried successfully");
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			System.err.println(e.getClass().getName()+": "+e.getMessage());
			System.exit(0);
		}
    
    
    }
void queryThird(Connection c)
    {
          	Statement stmt = null;
		try
		{
			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("select * from examinar  join centers on centers.centerid=examinar.center join school on  centers.schoolid=school.schoolid and school.schoolname='St Marry' where issupervisor=true;");
                        System.out.println("\t" + "phone" + "\t\t" + " gen " + "\t" + " email "+ "\t\t\t" + "name" + "\t" +"school");
			
			while(rs.next())
                        {
                                long phone=rs.getLong("phone");
                                String gen=rs.getString("gender");
                                String email=rs.getString("email");
                                String name=rs.getString("fname")+" "+rs.getString("mname")+" "+rs.getString("lname");
                                String school=rs.getString("schoolname");
                             
                               
				System.out.println("\t" + phone +"\t" + gen + "\t" + email + "\t\t" +name +"\t" + school);
			
                        }
			stmt.close();
			System.out.println("Table Queried successfully");
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			System.err.println(e.getClass().getName()+": "+e.getMessage());
			System.exit(0);
		}
    
    
    }
    void queryFive(Connection c)
    {
          	Statement stmt = null;
		try
		{
			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("select count(*) as c from student where gender='M' and stream = 1;");
			
			rs.next();
			int cnt=rs.getInt("c");
				System.out.println("Total Students : "+cnt);
			
			
			stmt.close();
			System.out.println("Table Queried successfully");
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			System.err.println(e.getClass().getName()+": "+e.getMessage());
			System.exit(0);
		}
    
    }
    void queryFour(Connection c)
    {
          	Statement stmt = null;
		try
		{
			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("select count(*) as c from student join school on student.school=school.SchoolId where student.city != school.city;");
			
			rs.next();
			int cnt=rs.getInt("c");
				System.out.println("Total Students : "+cnt);
			
			
			stmt.close();
			System.out.println("Table Queried successfully");
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			System.err.println(e.getClass().getName()+": "+e.getMessage());
			System.exit(0);
		}
    
    }
    void view(Exam e,Connection c)
    {
                                   Scanner sc=new Scanner(System.in);
                                    System.out.println("1. Subject");
                                    System.out.println("2. Student");
                                    System.out.println("3. School");
                                    System.out.println("4. Stream");
                                    System.out.println("5. School Representative");
                                    System.out.println("6. Exam");


                                    System.out.print("Choice :");
             try{
                                    int ch=sc.nextInt();
                                    switch(ch)
                                    {
                                        case 1:
                                                c.setAutoCommit(false);
                                                e.showSubject(c);
                                                c.setAutoCommit(true);
                                                break;
                                        case 2:
                                                c.setAutoCommit(false);
                                                e.showStudent(c);
                                                c.setAutoCommit(true);
                                                break;
                                        case 3:
                                                c.setAutoCommit(false);
                                                e.showSchool(c);
                                                c.setAutoCommit(true);
                                                break;
                                        case 4:
                                                c.setAutoCommit(false);
                                                e.showStream(c);
                                                c.setAutoCommit(true);
                                                break;
                                        case 5:
                                                c.setAutoCommit(false);
                                                e.showSchoolRepresentative(c);
                                                c.setAutoCommit(true);
                                                break;
                                        case 6:
                                                c.setAutoCommit(false);
                                                e.showExam(c);
                                                c.setAutoCommit(true);
                                                break;

                                        default:
                                                System.out.println("Inavalid Choice...");

                                    }
             }catch(Exception e1)
             {
                    System.err.println( e1.getClass().getName()+": "+ e1.getMessage() );
                    System.exit(0);
            
             }
    }
              
    
    void delete(Exam e,Connection c)
    {
                                    Scanner sc=new Scanner(System.in);
                                    System.out.println("1. Subject");
                                    System.out.println("2. Student");
                                    System.out.println("3. School");
                                    System.out.println("4. Stream");
                                    System.out.println("5. School Representative");
                                    System.out.println("6. Exam");


                                    System.out.print("Choice :");
             try{
                                    int ch=sc.nextInt();
                                    switch(ch)
                                    {
                                        case 1:
                                                c.setAutoCommit(false);
                                                e.deleteSubject(c);
                                                c.setAutoCommit(true);
                                                break;
                                        case 2:
                                                c.setAutoCommit(false);
                                                e.deleteStudent(c);
                                                c.setAutoCommit(true);
                                                break;
                                        case 3:
                                                c.setAutoCommit(false);
                                                e.deleteSchool(c);
                                                c.setAutoCommit(true);
                                                break;
                                        case 4:
                                                c.setAutoCommit(false);
                              //                  e.deleteStream(c);
                                                c.setAutoCommit(true);
                                                break;
                                        case 5:
                                                c.setAutoCommit(false);
                                //                e.deleteSchoolRepresentative(c);
                                                c.setAutoCommit(true);
                                                break;
                                        case 6:
                                                c.setAutoCommit(false);
                                  //              e.deleteExam(c);
                                                c.setAutoCommit(true);
                                                break;

                                        default:
                                                System.out.println("Inavalid Choice...");

                                    }
             }catch(Exception e1)
             {
                    System.err.println( e1.getClass().getName()+": "+ e1.getMessage() );
                    System.exit(0);
            
             }
                                        
    }
    void deleteSubject(Connection c)
    {
             PreparedStatement stmt = null;
            try{
                    stmt = c.prepareStatement("delete from subject where subject_name = ?;");
                    System.out.print("Enter Subject Name :");
                    String sub=sc.nextLine();
                    stmt.setString(1,sub);
                    int i=stmt.executeUpdate();
                    stmt.close();
                    
                    if(i>0)
                    {
                        System.out.println("Deleted Successfully....");
                    }
                    else
                    {
                        System.out.println("Please.... Write Proper Name...");
                 
                    }
                    
                 }
            catch ( Exception e ) 
            {
                    System.err.println( e.getClass().getName()+": "+ e.getMessage() );
                    System.exit(0);
            }
                 System.out.println("Operation done successfully"); 
    
    }
     void deleteStudent(Connection c)
    {       
     PreparedStatement stmt = null;
            try{
                    stmt = c.prepareStatement("delete from student where  enrollment_number = ?;");
                    System.out.print("Enter Enrollment Number :");
                    int eno=sc.nextInt();
                    stmt.setInt(1,eno);
                    int i=stmt.executeUpdate();
                    stmt.close();
                    
                    if(i>0)
                    {
                        System.out.println("Deleted Successfully....");
                    }
                    else
                    {
                        System.out.println("Please.... Write Proper Enrollment Number...");
                 
                    }
                    
                 }
            catch ( Exception e ) 
            {
                    System.err.println( e.getClass().getName()+": "+ e.getMessage() );
                    System.exit(0);
            }
                 System.out.println("Operation done successfully"); 
    
    
    }
     void deleteSchool(Connection c)
    {       
     PreparedStatement stmt = null;
            try{
                    stmt = c.prepareStatement("delete from school where schoolname = ?;");
                    System.out.print("Enter School Name :");
                    String sname=sc.nextLine();
                    stmt.setString(1,sname);
                    int i=stmt.executeUpdate();
                    stmt.close();
                    
                    if(i>0)
                    {
                        System.out.println("Deleted Successfully....");
                    }
                    else
                    {
                        System.out.println("Please.... Write Proper School Name...");
                 
                    }
                    
                 }
            catch ( Exception e ) 
            {
                    System.err.println( e.getClass().getName()+": "+ e.getMessage() );
                    System.exit(0);
            }
                 System.out.println("Operation done successfully"); 
    
    
    }
        void showSubject(Connection c)
    {
             Statement stmt=null;
             System.out.println("---------------Subject----------------------");
            try{
                    stmt = c.createStatement();
                    ResultSet rs = stmt.executeQuery( "select subject.subject_name, stream.stream_name from subject join stream on (subject.streamid = stream.sid)" );
                    System.out.println(  "\t" + "stream_name"+ "\t" +"sub_name");
                    
                    while ( rs.next() ) 
                    {
                            
                            String  name = rs.getString("subject_name");
                            String  sname = rs.getString("stream_name");
                            
                          
                            System.out.print(  "\t" +sname+ "\t" + name);
                            System.out.println();
                    }
                    rs.close();
                    stmt.close();
                    //c.close();
                 }
            catch ( Exception e ) 
            {
                    System.err.println( e.getClass().getName()+": "+ e.getMessage() );
                    System.exit(0);
            }
                 System.out.println("Operation done successfully"); 
    
    }

    
    void showStudent(Connection c)
    {       
             Statement stmt=null;
             System.out.println("---------------Student----------------------");
            try{
                    stmt = c.createStatement();
                    ResultSet rs = stmt.executeQuery( "SELECT enrollment_number,mobilenumber,gender,email,stream,school,concat(firstname,' ',middlename,' ',lastname) as name,age(date_of_birth) as a FROM student;" );
                    System.out.println( "\t" + "E_id" + "\t" + "Mobile Number"+ "\t" + "Gen" + "\t" + "email"+ "\t" + "BDate" +"\t" + "Name"  );  
                    
                    while ( rs.next() ) 
                    {
                            int eid = rs.getInt("enrollment_number");
                            long mno= rs.getLong("mobilenumber");
                            String  gen = rs.getString("gender");
                            String email=rs.getString("email");
                            int sid  = rs.getInt("stream");
                            String sname=rs.getString("school");
                            String age=rs.getString("a");
                            String name=rs.getString("name");
                            System.out.print("\t" + eid + "\t" + mno + "\t" + gen + "\t" + email + "\t" + name + "\t"  + age);
                            System.out.println();
                    }
                    rs.close();
                    stmt.close();
                    //c.close();
                 }
            catch ( Exception e ) 
            {
                    System.err.println( e.getClass().getName()+": "+ e.getMessage() );
                    System.exit(0);
            }
                 System.out.println("Operation done successfully"); 
    
    
    }
    void showSchool(Connection c)
    {
                 Statement stmt=null;
             System.out.println("---------------School----------------------");
            try{
                    stmt = c.createStatement();
                    ResultSet rs = stmt.executeQuery( "SELECT schoolname,city,pincode FROM school;" );
                    System.out.println("\t" + "Pincode" + "\t" + "City" + "\t" +"School_Name");  
                    
                    while ( rs.next() ) 
                    {
                            String  city = rs.getString("city");
                            long pcode  = rs.getInt("pincode");
                            String name=rs.getString("schoolname");
                            System.out.print("\t" +  pcode + "\t" + city + "\t" + name );
                            System.out.println();
                    }
                    rs.close();
                    stmt.close();
                    //c.close();
                 }
            catch ( Exception e ) 
            {
                    System.err.println( e.getClass().getName()+": "+ e.getMessage() );
                    System.exit(0);
            }
                 System.out.println("Operation done successfully"); 
    
    
    }
    
    void showStream(Connection c)
    {
                 Statement stmt=null;
                  System.out.println("---------------Stream----------------------");
            try{
                    stmt = c.createStatement();
                    ResultSet rs = stmt.executeQuery( "SELECT stream_name FROM stream;" );
                    System.out.println("\t" + "Stream_Name");  
                    
                    while ( rs.next() ) 
                    {
                            String name=rs.getString("stream_name");
                            System.out.print("\t" + name );
                            System.out.println();
                    }
                    rs.close();
                    stmt.close();
                    //c.close();
                 }
            catch ( Exception e ) 
            {
                    System.err.println( e.getClass().getName()+": "+ e.getMessage() );
                    System.exit(0);
            }
                 System.out.println("Operation done successfully"); 
    
    
        
    }
    void showSchoolRepresentative(Connection c)
    {
                 Statement stmt=null;
                  System.out.println("---------------School_representative----------------------");
            try{
                    stmt = c.createStatement();
                    ResultSet rs = stmt.executeQuery( "select concat(school_representative.fname,' ',school_representative.mname,' ',school_representative.lname) as name," +
                                                       "public.school_representative.phone,school_representative.email,school.schoolname as school from school natural join school_representative;" );
                    System.out.println("\t" + "Name" + "\t" + "Phone" + "\t" + "Email" + "\t" + "Schoolname");  
                    
                    while ( rs.next() ) 
                    {
                            String name=rs.getString("name");
                            long phone=rs.getLong("phone");
                            String email=rs.getString("email");
                            String sname=rs.getString("school");
                            
                            System.out.print("\t" + name + "\t" + phone + "\t" + email + "\t" + sname);
                            
                            System.out.println();
                    }
                    rs.close();
                    stmt.close();
                    //c.close();
                 }
            catch ( Exception e ) 
            {
                    System.err.println( e.getClass().getName()+": "+ e.getMessage() );
                    System.exit(0);
            }
                 System.out.println("Operation done successfully"); 
    
    
        
    }
    void showExam(Connection c)
    {
                 Statement stmt=null;
                  System.out.println("---------------Exam----------------------");
            try{
                    stmt = c.createStatement();
                    ResultSet rs = stmt.executeQuery("select exam.date,subject.subject_name,exam.exam_type from exam natural join subject;");
                    System.out.println("\t" + "Date" + "\t\t"+ "Exam_Type"  + "\t\t" + "Subject" );  
                    
                    while ( rs.next() ) 
                    {
                            String date=rs.getString("date");
                            String subject=rs.getString("subject_name");
                            String etype=rs.getString("exam_type");
                            
                            
                            System.out.print("\t" + date + "\t" + etype+ "\t\t" +subject  );
                            
                            System.out.println();
                    }
                    rs.close();
                    stmt.close();
                    //c.close();
                 }
            catch ( Exception e ) 
            {
                    System.err.println( e.getClass().getName()+": "+ e.getMessage() );
                    System.exit(0);
            }
                 System.out.println("Operation done successfully"); 
    
    
    }
}