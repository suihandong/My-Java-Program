import java.util.Scanner;
public class Gpa{
    public void calculategpa(){
        System.out.println("java GPA");
        Scanner sc = new Scanner(System.in);
        String letter = sc.next();
        int grade = sc.nextInt();
        double var = 0;
        double credit = 0;
        double sum1 = 0;
        double sum2 = 0;
        while(sc.hasNext()) {

            if (letter.equals("a")){
                credit = 4.0;
                var = 4.0 * grade;
            } else if (letter.equals("a-")){
                credit = 3.667 * grade;
                var = 3.667 * grade;
            } else if (letter.equals("b+")){
                credit = 3.333 * grade;
                var = 3.333 * grade;
            } else if (letter.equals("b")){
                credit = 3.0 * grade;
                var = 3.0 * grade;
            } else if (letter.equals("b-")){
                credit = 2.667 * grade;
                var = 2.667 * grade;
            } else if (letter.equals("c+")){
                credit = 2.333 * grade;
                var = 2.333 * grade;
            } else if (letter.equals("c")){
                credit = 2.0 * grade;
                var = 2.0 * grade;
            } else if (letter.equals("c-")){
                credit = 1.667 * grade;
                var = 1.667 * grade;
            } else if (letter.equals("d+")){
                credit = 1.333 * grade;
                var = 1.333* grade;
            } else if (letter.equals("d")){
                credit = 1.0 * grade;
                var = 1.0 * grade;
            } else if (letter.equals("f")){
                credit = 0.0 * grade;
                var = 0.0 * grade;
            }
            sum1 += var;
            sum2 += credit;
        }
        double gpa = sum1/sum2;
        System.out.println("The GPA is: " + gpa);
    }
}
