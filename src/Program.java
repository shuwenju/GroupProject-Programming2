import java.util.ArrayList;
import java.util.Arrays;

public class Program {
    private String programID;
    private String programName;
    private String programDescription;
    private String[] courseIDList;
    private String[] openingCourseIDList;

    public Program() {
    }
    public String getProgramID() {
        return programID;
    }

    public void setProgramID(String programID) throws WrongInputException {
        if (!programID.startsWith("FSD")){
            throw new WrongInputException("Wrong Program Name, Not Start With \"FSD\"  letter");
        }
        this.programID = programID;
    }

    public String getProgramName() {
        return programName;
    }

    public void setProgramName(String programName) throws WrongInputException {
        if (programName.length()>50)
            throw new WrongInputException("Program name is more than 50 chars.");
        this.programName = programName;
    }

    public String getProgramDescription() {
        return programDescription;
    }

    public void setProgramDescription(String programDescription) throws WrongInputException {
        if (programDescription.length()>100)
            throw new WrongInputException("Program description is more than 100 chars.");

        this.programDescription = programDescription;
    }

    public String[] getCourseIDList() {
        return courseIDList;
    }

    public void setCourseIDList(String[] courseIDList) throws WrongInputException {
        if (courseIDList.length==0){
            throw  new WrongInputException("Your course list is null, please check it!");
        }
        this.courseIDList = courseIDList;
    }

    public String[] getOpeningCourseIDList() {
        return openingCourseIDList;
    }

    public void setOpeningCourseIDList(String[] openingCourseIDList) throws WrongInputException {
        if (openingCourseIDList.length==0){
            throw  new WrongInputException("Your opening course list is null, please check it!");
        }
        this.openingCourseIDList = openingCourseIDList;
    }

    @Override
    public String toString() {
        return "Program{" +
                "programID='" + programID + '\'' +
                ", programName='" + programName + '\'' +
                ", programDescription='" + programDescription + '\'' +
                ", courseIDList=" + Arrays.toString(courseIDList) +
                ", openingCourseIDList=" + Arrays.toString(openingCourseIDList) +
                '}';
    }


}