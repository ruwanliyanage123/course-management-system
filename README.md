  # course-management-system Tutorial
This will provide a basic guidline to the beginners who are willing to create a stand alone application with java.                
	The class diagram
	
  ![class diagram](https://firebasestorage.googleapis.com/v0/b/firestorecrud-cdd76.appspot.com/o/course-management-system%2FClass%20Diagram.png?alt=media&token=079498be-d228-4a26-b895-afa9bf903eed)

*The EER diagram

![eer](https://firebasestorage.googleapis.com/v0/b/firestorecrud-cdd76.appspot.com/o/course-management-system%2FEER.png?alt=media&token=2642d98d-d567-476e-88fe-656b75d9b506)

![e](https://firebasestorage.googleapis.com/v0/b/firestorecrud-cdd76.appspot.com/o/course-management-system%2F20200222_134813.jpg?alt=media&token=bd0b1d2f-4cc7-4b80-bbe9-12229747c895)

*Table Mapping

![hh](https://firebasestorage.googleapis.com/v0/b/firestorecrud-cdd76.appspot.com/o/course-management-system%2FScreenshot%20from%202020-02-22%2019-51-21.png?alt=media&token=37ffb733-35c8-46ba-aa5e-a5f651a29de9)

*Logging

Logging is used to store exceptions, information, and warnings as messages that occur during the execution of a program.
Logging helps a programmer in the debugging process of a program. Java provides logging facility in the java.util.logging 
package

Add this dependancy into pom.xml

	<dependency>
	  <groupId>org.slf4j</groupId>
	  <artifactId>slf4j-simple</artifactId>
	  <version>1.7.5</version>
	</dependency>
	
Can import as this

	import org.slf4j.Logger;
	import org.slf4j.LoggerFactory;
	
Define logger variable 

	private static Logger logger = LoggerFactory.getLogger(DatabaseConnection.class);
	
Logger can use to show errors, warnings, informations so on

	logger.error("message",e);
	logger.warn("message");
	logger.debug("message");
	logger.info("message");
<<<<<<< HEAD
	
	
*Nested classes

In this project, I used a nested class(static nested class) named, CourseTable. By using this nested class we can keep course table logics in seperately but inside the courseUI class. the nested classes will increase the readability. two types of nested classes. 1.static nested class 2.non-static nested class(inner class)

call a static nested class
	
	OuterClass.InnerClass innerClass = new OuterClass.InnerClass();
call a non-static class alias inner class
	
	OuterClass outerClass = new OuterClass();
	OuterClass.InnerClass innerClass = outer.new InnerClass();
	
Design Patterns	

Design patterns are the best practises following by the OOP experts. Those are the reusable 
solutions for the commonly occuring problems
Those are mainly following into three categories.

1.creational  2.structural 3.behaviour

In this project I used Singleton Design pattern and DAO design pattern

1.singleton design pattern for the database connection.

Singleton design pattern belongs into the creational design pattern category. Those are control the object creation
and reduce the complexity of the system. This design pattern can use with database connection. By using 
singleton design pattern for the database connection we can ensure that we will be able to maintain the 
a single database connection over the project without any problems. other wise if there more than one connections, can
be happen problems. Inorder to do that, we have to do two things. number one is make constructor as private. number two is 
create the one and only object inside the static method. why make constructor as private? It will make sure
that there cannot be create more than one object. Then why use static method? it used to take an object 
without create an instance of the class. (hide the object creation by avoid from use "new" keyword directly.)

    public class DBConnection {
        private static DBConnection dbConnection;
        private DBConnection(){
            //keep constructor as private to make sure restrict to the single object creation
        }
    
        public static DBConnection getInstance(){
            if(dbConnection==null){
                //create an object inside the static method to take instance without using the new keyword
                dbConnection = new DBConnection();
            }
            return dbConnection;
        }
    }
    
    #You can use the following way to avoid the deadlock handling
    
    public final class DatabaseConnection(){
     
	private DatabaseConnection(){} 
    
	private static class DatabaseConnectionLazyLoader{
	    pubilc static final INSTANCE = new DatabaseConnection();
	}
	
	public static DatabaseConnection getInstance(){
	    return DatabaseConnectionLazyLoader.INSTANCE;
	}
    }


2.DAO design pattern. (dao interface)

DAO design pattern using to seperate the low level data accessing API from high level business level
services. In this design pattern, there are three main parts. 

1.DAO Interface -Declare the all methods are need to perform on the model

2.DAO Implementation - Take data from the Data source.(database)

3.Model - Java bean (use to store data to transmission purpose)


CourseDao.java (dao interface)

    public interface CourseDao<Course> {    
        public abstract String[][] getAllCourses();    
        public abstract void addCourse(Course course);    
        public abstract Course getOneCourse(int courseID);    
        public abstract void updateCourse(Course course, int courseId);
        public abstract void deleteCourse(int courseId);
    }
    
CourseDaoImpl.java (implementation of dao interface)

    public interface CourseDao<Course> {    
        public  String[][] getAllCourses(){
            //implementation to take all courses
        }    
        public void addCourse(Course course){
            //implementation to add one course
        }   
        public Course getOneCourse(int courseID){
            //implementation to take one course
        }   
        public void updateCourse(Course course, int courseId){
            //implementation to update one course
        }
        public void deleteCourse(int courseId){
            //implementation to delete one course
        }
    }
    
Course.java (model)    
    
    public class Course {
        private int courseId;
        private String courseName;
    
        public Course(String courseName) {
            this.courseName = courseName;
        }
    
        public int getCourseId() {
            return courseId;
        }
    
        public String getCourseName() {
            return courseName;
        }
    
        public void setCourseName(String courseName) {
            this.courseName = courseName;
        }
    }
=======
>>>>>>> bf5ee6e68b40064c592f9bfb9ba7f7b4bd32c2ee

*GIT commands

basic commands
basically local git repository contains three parts. those are

1.working directory (the actual files)

2.index (staging are)
	
	git add filename (will add files from working directory to index)
		
3.HEAD (points to the last commit)

	git commit -m "commit message" (add changes from index to HEAD)
	
basics
	
	git clone path/to/repository (to clone a local repository)
	git clone url(to clone a global repository)
	git config --global user.email your email (to configurations)
	git status (to check the state)
	git push
	git pull 
	git branch <branch name> 
	git checkout <branch name>
	git branch -d <branch name>
	git remote -v
	git diff <source branch> <target branch>
	
git advanced

	git cherry-pick commit-hash (make sure that you are in correct branch. then can pick the commit from another branch into it)
	git push -f origin commitid^:branch (to delete last commit message in remote repository)
	git reset commitid^ (to remove last commit from local repository)
	git commit --amend -m "new commit message" (to rename the commit message)


https://docs.google.com/document/d/118Uds9zaS6CfzBkvikYWRdxjR_BN3G3-ikVsPg4Bp4A/edit
changed
