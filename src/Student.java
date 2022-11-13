import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.IllegalFormatException;
import java.util.Scanner;

/**
 * @author Shuwen Ju
 */
public class Student {


    private String firstName;
    private String lastName;
    private int id;
    private ArrayList<OpeningCourse> CurrentCoursesList;
    //private ArrayList<Course> PassedCourseList;

    public Student() {
    }

    public Student(String firstName, String lastName, int id, ArrayList<OpeningCourse> currentCoursesList) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.id = id;
        CurrentCoursesList = currentCoursesList;
    }

    public Student(String firstName, String lastName, ArrayList<OpeningCourse> currentCoursesList) {
        this.firstName = firstName;
        this.lastName = lastName;
        CurrentCoursesList = currentCoursesList;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) throws InvalidInputException  {
        char[] firstname = firstName.toCharArray();
        for (int i = 0; i < firstname.length; i++) {
            if(Character.isDigit(firstname[i]) || firstname.length < 2) {
                throw new InvalidInputException("Name cannot contain digits / has to be at least 2 characters.");
            }
        }
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) throws InvalidInputException{
        char[] lastname = lastName.toCharArray();
        for (int i = 0; i < lastname.length; i++) {
            if(Character.isDigit(lastname[i]) || lastname.length < 2) {
                throw new InvalidInputException("Name cannot contain digits / has to be at least 2 characters.");
            }
        }
        this.lastName = lastName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        if(("" + id).length() != 5){
            throw new InvalidInputException("Student ID has to be 5 digits");
        }
        this.id = id;
    }




    public ArrayList<OpeningCourse> getCurrentCoursesList() {
        return CurrentCoursesList;
    }

    public void setCurrentCoursesList(ArrayList<OpeningCourse> currentCoursesList) {
        CurrentCoursesList = currentCoursesList;
    }



    @Override
    public String toString() {
        return "Student " + firstName + " " + lastName + " ID " + id + ", courses enrolled: " + CurrentCoursesList;
    }


    public String toData(){
        return firstName + "&" + lastName + "&" + id + "&" + CurrentCoursesList;
    }

}

class studentMethods {
    static final String DATA_FILE = "AllStudent";
    static File studentFile = new File(DATA_FILE);
    static Scanner input = new Scanner(System.in);

    static ArrayList<Student> allStudent = null;

    public ArrayList<Student> loadAllStudentDatatoArraylist() {
        allStudent = new ArrayList<Student>();

        try (Scanner reader = new Scanner(studentFile)) {
            while (reader.hasNext()) {
                String line = reader.nextLine();
                String[] dataLine = line.split("&");

                if (dataLine.length != 4) {
                    System.out.println("Error reading the file");
                    continue;
                }

                String firstname = dataLine[0];
                String lastname = dataLine[1];
                int studentid = Integer.parseInt(dataLine[2]);
                ArrayList<OpeningCourse> list = new ArrayList();
                String programid = "";
                String coursecode = "";
                String coursename = "";
                double credit = 0;
                int hour = 0;
                String courses = dataLine[3];
                String[] coursesplit = {""};
                for (int i = 0; i < courses.length(); i++) {
                    coursesplit = courses.split(",");// split to each course arrayinfo
                }
                String[] eachcourse = {""};

                for (int j = 0; j < coursesplit.length; j++) {
                    eachcourse = coursesplit[j].split(";");
                    programid = eachcourse[0].substring(1, eachcourse[0].length()); // remove {
                    coursecode = eachcourse[1];
                    coursename = eachcourse[2];
                    credit = Double.parseDouble(eachcourse[3]);
                    String hourstemp = eachcourse[4].substring(0, eachcourse[4].length() - 1); // remove }
                    hour = Integer.parseInt(hourstemp);
                    list.add(new OpeningCourse(coursename, coursecode, programid, hour, credit));
                }
                allStudent.add(new Student(firstname, lastname, studentid, list));
            }
        } catch (IOException e) {
            System.out.println("there is an exception in reading/writing the data " + e.getMessage());
        } catch (InvalidInputException e) {
            System.out.println(e.getMessage());
        }
        return allStudent;
    }

    {
        loadAllStudentDatatoArraylist();
    }

    public void viewCoursesTaking(){
        System.out.println("Please enter student ID");
        int studentidInput = input.nextInt();
        Student currentStudent = null;
        for (int i = 0; i < allStudent.size(); i++) {
            if (studentidInput == allStudent.get(i).getId()) {
                currentStudent = allStudent.get(i);
            }else{
                System.out.println("Student not found");
            }
        }
        System.out.println(currentStudent.getCurrentCoursesList());
    }



    public void addcourse() {
        try {
            System.out.println("Please enter student ID");
            int studentidInput = input.nextInt();
            Student currentStudent = null;
            for (int i = 0; i < allStudent.size(); i++) {
                if (studentidInput == allStudent.get(i).getId()) {
                    currentStudent = allStudent.get(i);
                }
            }
//            new MainMenuTest.initLoadFromCourseFile();
            System.out.println("Please choose a course you'd like to add, please enter course code");
            String coursecodeinput = input.next();
            ArrayList<Course> courses = new MainMenuTest().initLoadFromCourseFile();
            for (int i = 0; i < courses.size(); i++) {
                if (coursecodeinput.equals(courses.get(i).getCourseID())) {
                    ArrayList<OpeningCourse> list = currentStudent.getCurrentCoursesList();
                    list.add(list.size(), new OpeningCourse(list.get(i).getCourseName(), list.get(i).getCourseID(), list.get(i).getProgramID(),
                            list.get(i).getHours(), list.get(i).getCredit()));
                }
            }
        } catch (ProgramFileNotExistException e) {
            throw new RuntimeException(e);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (WrongInputException e) {
            throw new RuntimeException(e);
        }
    }



    public void dropcourse() {
        System.out.println("Please enter student ID");
        int studentidInput = input.nextInt();
        Student currentStudent = null;
        for (int i = 0; i < allStudent.size(); i++) {
            if (studentidInput == allStudent.get(i).getId()) {
                currentStudent = allStudent.get(i);
            }
        }


        //displaying what this student takes right now:
        ArrayList<OpeningCourse> list = currentStudent.getCurrentCoursesList();
        for (int i = 0; i < list.size(); i++) {
            System.out.println(currentStudent.getCurrentCoursesList().get(i));
        }
        //ask which course they want to drop:
        System.out.println("Please enter course that you'd like to drop by course ID");
        String courseidInput = input.next();

        //remove the selected course from the list
        for (int i = 0; i < list.size(); i++) {
            if (courseidInput.equals(list.get(i).getCourseID())) {
                list.remove(i);
            }else{
                System.out.println("Course not found");
            }
        }
    }
}

