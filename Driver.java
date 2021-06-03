import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.io.FileWriter;

public class Driver_alt {
    public static void main(String args[]) throws IOException{
        File myObj = new File("./A2_test_2.in");
        FileWriter fw=new FileWriter("./A2_res_latest.out");
        Scanner sc = new Scanner(myObj);
        int numTestCases;
        numTestCases = sc.nextInt();
        while (numTestCases-- > 0) {
            int size;
            size = sc.nextInt();
            A2DynamicMem obj = new A2DynamicMem(size,2);
            int numCommands = sc.nextInt();
            while (numCommands-- > 0) {
                String command;
                command = sc.next();
                int argument;
                argument = sc.nextInt();
                int result = -5;
                switch (command) {
                    case "Allocate":
                        result = obj.Allocate(argument);
                        break;
                    case "Free":
                        result = obj.Free(argument);
                        break;
                    case "Defragment":
                        obj.Defragment();
                        result = -100;
                        break;
                    default:
                        break;
                }
                String str = String.valueOf(result);
                fw.write(str);
                fw.write("\n");
            }

        }
        fw.close();
        sc.close();
    }
}