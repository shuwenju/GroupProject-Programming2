/**
 * @author Shuwen Ju
 */
import java.sql.Date;

public class OpeningCourse extends Course{
    private Date startDate;
    private Date endDate;

    public OpeningCourse(String courseName, String courseID, String programID, int hours, double credit) {
        super(courseName, courseID, programID, hours, credit);
    }
    //    public OpeningCourse(String courseName, String courseID, String programID, int hours, double credit) {
//        //       super(courseName, courseID, programID, hours, credit);
//    }
// public OpeningCouse(String courseName, String courseID, String programID, String programName, int hours, double credit) {
    //super(courseName, courseID, programID, programName, hours, credit);
    // }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }


//    @Override
//    public String toString() {
//        return super.getCourseName();
//    }

    @Override
    public String toString() {
        return getProgramID() + ";" + getCourseID() + ";" + getCourseName() + ";" + getCredit() + ";" + getHours();
    }

    public static void listAllOpeningCourses(){

    }
}
