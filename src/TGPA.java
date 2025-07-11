import java.io.IOException;
import java.util.Scanner;

enum semester
{

    CSE326(2),
    MTH174(4),
    INT108(4),
    MEC136(4),
    CSE111(2),
    PES318(3),
    PHY110(3),
    
    ECE249(4),
    INT306(4),
    CSE101(4),
    MTH401(3),
    CSE320(3),
    PEL130(3),
    CSE121(2),
    CHE110(2),
    ECE279(1),
    
    CSE202(4),
    CSE205(4),
    CSE209(4),
    CSE306(3),
    CSE307(1),
    CSE316(3),
    CSE325(1),
    GEN231(2),
    PEL136(3),
    
    CSE310(4),
    CSE211(4),
    MTH302(4),
    CSE408(3),
    INT255(3),
    INT256(3),
    PEA305(3);

    final int credit;
    semester(int credit)
    {
        this.credit = credit;
    }
}

enum grade
{
    OGRADE(10),
    APGRADE(9),
    AGRADE(8),
    BPGRADE(7),
    BGRADE(6),
    CGRADE(5),
    DGRADE(4),
    EGRADE(0),
    GGRADE(0),
    FGRADE(0),
    IGRADE(0);

    final int point;
    grade(int point)
    {
        this.point = point;
    }
}

abstract class exceptionHandling
{
    abstract int parseIntegerInputException(String str);
}

class callCheck extends exceptionHandling
{

    int parseIntegerInputException(String str)
    {
        int varT = 0;
        try{
            Integer.parseInt(str);
        }
        catch (NumberFormatException e){
            System.out.println(Colours.ANSI_RED + "ERROR: Please enter a numeric value!\n" + Colours.ANSI_RESET);
            varT = 1;
        }
        return varT;
    }
}

class calculateTGPA
{
    static int tCredits = 0;
    static int sum = 0;

    void calculate(Scanner sc)
    {
        callCheck check = new callCheck();
        int flag = 1;
        int till = 0;
        StringBuilder subject;
        StringBuilder updatedGrade;

        while(flag != 0) {
            System.out.print(Colours.ANSI_CYAN + "Enter number of Subjects to calculate TGPA for: " + Colours.ANSI_RESET);
            {
                String num = sc.nextLine();
                flag = check.parseIntegerInputException(num);
                if(flag == 0)
                    till = Integer.parseInt(num);
            }

        }

        for(int i = 1; i <= till; i++)
        {
            {
                String temp1;
                String temp2;

                System.out.println(i);

                System.out.print(Colours.ANSI_BLUE + "Enter Subject: " + Colours.ANSI_RESET);
                temp1 = sc.next().toUpperCase();
                System.out.print(Colours.ANSI_BLUE + "Enter Grade: " + Colours.ANSI_RESET);
                temp2 = sc.next().toUpperCase();

                subject = new StringBuilder(temp1);
                updatedGrade = new StringBuilder();

                String[] temp = temp2.split("");
                if (temp.length == 2) {
                    updatedGrade.append(temp[0]).append("P").append("GRADE");
                } else {
                    updatedGrade.append(temp2).append("GRADE");
                }
            }

            try {
                semester.valueOf(subject.toString());
                grade.valueOf(updatedGrade.toString());
            }
            catch (IllegalArgumentException e) {
                System.out.println(Colours.ANSI_RED + "ERROR: Please enter valid Subject and Grade details!\n" + Colours.ANSI_RESET);
                i--;
                continue;
            }
            tCredits = tCredits + semester.valueOf(subject.toString()).credit;
            sum = sum + semester.valueOf(subject.toString()).credit * grade.valueOf(updatedGrade.toString()).point;
        }

        System.out.printf(Colours.ANSI_GREEN + "Total TGPA: %.2f", sum/(double)tCredits);
        System.out.println(Colours.ANSI_RESET);
    }

}

public class TGPA
{
    public static void main(String[] args)
    {
        Scanner input = new Scanner(System.in);
        calculateTGPA obj = new calculateTGPA();
        obj.calculate(input);
        System.out.print(Colours.ANSI_RED + "\nPress Enter to exit the program:" + Colours.ANSI_RESET);
        try
        {
            System.in.read();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
class Colours
{
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_CYAN = "\u001B[36m";

}