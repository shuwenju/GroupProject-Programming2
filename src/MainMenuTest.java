import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.Collectors;

public class MainMenuTest {
    public static ArrayList<Program> programs;

    public static void main(String[] args) throws ProgramFileNotExistException, FileNotFoundException, WrongInputException {
        MainMenuTest mainMenuTest = new MainMenuTest();
        programs = mainMenuTest.initLoadFromProgramFile();
        System.out.println(programs.get(0));
        mainMenuTest.getProgramInfoByProgramID("FSD001");
        ArrayList<Course> courses = mainMenuTest.initLoadFromCourseFile();
        System.out.println(courses.stream().collect(Collectors.toList()));

    }

    public Program getProgramInfoByProgramID(String programID) {
        return programs.stream().filter(p -> p.getProgramID().equals(programID)).findFirst().get();
    }


    public ArrayList<Program> initLoadFromProgramFile() throws ProgramFileNotExistException, FileNotFoundException, WrongInputException {

        File file = new File("programFile.txt");
        ArrayList<Program> programList = new ArrayList<>(); //later, if we change mind to many programs, use this
        if (!file.exists()) {
            throw new ProgramFileNotExistException("Program File Not Existed!");
        } else {
            Scanner scannerFile = new Scanner(file);
            while (scannerFile.hasNext()) {
                Program program = new Program();
                String[] splitProgram = scannerFile.nextLine().split(";");
                String programID = splitProgram[0];
                String programName = splitProgram[1];
                String programDescription = splitProgram[2];


                String courseIDList = splitProgram[3];
                String[] splitCourseIDList = courseIDList.split(",");
                program.setCourseIDList(splitCourseIDList);

                String openingcourseIDList = splitProgram[4];
                String[] splitOpeningCourseIDList = openingcourseIDList.split(",");
                program.setOpeningCourseIDList(splitOpeningCourseIDList);
                program.setProgramID(programID);
                program.setProgramName(programName);
                program.setProgramDescription(programDescription);
                System.out.println("Program add " + program);
                programList.add(program);

            }
        }

        return programList;
    }


    public ArrayList<Course> initLoadFromCourseFile() throws ProgramFileNotExistException, FileNotFoundException, WrongInputException {

        File file = new File("courseFile.txt");
        ArrayList<Course> courseList = new ArrayList<>();
        if (!file.exists()) {
            throw new ProgramFileNotExistException("course File Not Existed!");
        } else {
            Scanner scannerFile = new Scanner(file);
            while (scannerFile.hasNext()) {
                Course course = new Course();
                String[] splitCourse = scannerFile.nextLine().split(";");
                String programID = splitCourse[0];
                String courseID = splitCourse[1];
                String courseName = splitCourse[2];
                String courseCredit = splitCourse[3];
                String courseHour = splitCourse[4];
                course.setProgramID(programID);
                course.setCourseID(courseID);
                course.setCourseName(courseName);
                course.setCredit(Double.parseDouble(courseCredit));
                course.setHours(Integer.parseInt(courseHour));
                courseList.add(course);
                System.out.println("**************" + course);
            }
        }
        return courseList;
    }
}






