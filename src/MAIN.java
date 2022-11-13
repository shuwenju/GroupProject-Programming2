import com.sun.jdi.PathSearchingVirtualMachine;

import java.util.ArrayList;

/**
 * @author Shuwen Ju
 */
public class MAIN {
    public static void main(String[] args) {
        new studentMethods().loadAllStudentDatatoArraylist();
        ArrayList<OpeningCourse> testlist = new ArrayList<>();

        OpeningCourse webdesign = new OpeningCourse("Website Design", "420-WB4-AB", "FSD001", 60,2.33);
        OpeningCourse UI = new OpeningCourse("User Interfaces", "420-WC4-AB", "FSD001", 60,2.33);

        testlist.add(webdesign);
        testlist.add(UI);


        Student student = new Student("Lili", "Abby", 10001, testlist);
    }
}
