package staff.test;

import staff.StaffMember;
import staff.Lecturer;
import java.time.LocalDate;

public class StaffTest {

    public static void printStaffDetails(StaffMember member) {
        System.out.println(member.toString());
    }

    public static void main(String[] args) {
        LocalDate start = LocalDate.of(2019, 3, 21);
        LocalDate end = LocalDate.of(2022, 7, 30);
        StaffMember Adam = new StaffMember("Adam", 20000, start, end);
        Lecturer Ashesh = new Lecturer("CSE", 'A', "Ashesh", 20000, start, end);
        printStaffDetails(Adam);
        printStaffDetails(Ashesh);
        Lecturer AsheshClone = Ashesh;
        StaffMember AdamClone = Adam;
        // assert 10 < 5 : "Untrue";
        if (!Adam.equals(Ashesh)) {
            System.out.println("(Adam != Ashesh) PASSED");
        }
        if (Adam.equals(Adam)) {
            System.out.println("(Adam == Adam) PASSED");
        }
        if (Adam.equals(AdamClone)) {
            System.out.println("(Adam == AdamClone) PASSED");
        }
        if (!Ashesh.equals(Adam)) {
            System.out.println("(Ashesh != Adam) PASSED");
        }
        if (Ashesh.equals(Ashesh)) {
            System.out.println("(Ashesh == Ashesh) PASSED");
        }
        if (Ashesh.equals(AsheshClone)) {
            System.out.println("(Ashesh == AsheshClone) PASSED");
        }
    }
}
