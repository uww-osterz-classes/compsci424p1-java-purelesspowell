/* COMPSCI 424 Program 1
 * Name:
 * 
 * This is a template. Program1.java *must* contain the main class
 * for this program. Otherwise, feel free to edit these files, even
 * these pre-written comments or my provided code. You can add any
 * classes, methods, and data structures that you need to solve the
 * problem and display your solution in the correct format.
 */
package compsci424.p1.java;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Main class for this program. The required steps have been copied
 * into the main method as comments. Feel free to add more comments to
 * help you understand your code, or for any other reason. Also feel
 * free to edit this comment to be more helpful for you.
 */
public class Program1 {
    // Declare any class/instance variables that you need here.

    /**
     * @param args command-line arguments, which can be ignored
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<String> arr = new ArrayList<>();
        while(true){
            String word = sc.nextLine();
            if(word.equals("end")) break;
            arr.add(word);
        }
        Version1 v1 = new Version1(16);
        for(String ar : arr){
            String[] split = ar.split(" ");
            if(split[0].equals("create")){
                int result = v1.create(Integer.parseInt(split[1]));
                        if(result == 0){
                            System.out.println("Create successful");
                        }
                        else{
                            System.out.println("Create unsuccessful");
                        }
            }

            else if(split[0].equals("destroy")){
                int result = v1.destroy(Integer.parseInt(split[1]));
                if(result == 0){
                    System.out.println("Destroy successful");
                }
                else{
                    System.out.println("Destroy unsuccessful");
                }
            }
            v1.showProcessInfo();

        }

        Version2 v2 = new Version2(16);
        for(String ar : arr){
            String[] split = ar.split(" ");
            if(split[0].equals("create")){
                int result = v2.create(Integer.parseInt(split[1]));
                if(result == 0){
                    System.out.println("Create successful");
                }
                else{
                    System.out.println("Create unsuccessful");
                }
            }

            else if(split[0].equals("destroy")){
                int result = v2.destroy(Integer.parseInt(split[1]));
                if(result == 0){
                    System.out.println("Destroy successful");
                }
                else{
                    System.out.println("Destroy unsuccessful");
                }
            }
            v2.showProcessInfo();

        }
        // 1. Ask the user to enter commands of the form "create N",
        //    "destroy N", or "end", where N is an integer between 0 
        //    and 15.

        // 2. While the user has not typed "end", continue accepting
        //    commands. Add each command to a list of actions to take 
        //    while you run the simulation.
        // 3. When the user types "end" (or optionally any word that 
        //    is not "create" or "destroy"), stop accepting commands 
        //    and complete the following steps.
        //
        // Hint: Steps 2 and 3 refer to the same loop. ;-)

        // 4. Create an object of the Version 1 class and an object of
        //    the Version 2 class.

        // 5. Run the command sequence once with the Version 1 object, 
        //    calling its showProcessTree method after each command to
        //    show the changes in the tree after each command.

        // 6. Repeat step 5, but with the Version 2 object.

        // 7. Store the current system time in a variable

        // ... then run the command sequence 200 times with Version 1.

        // ... After this, store the new current system time in a
        //     second variable. Subtract the start time from the end 
        //     time to get the Version 1 running time, then display 
        //     the Version 1 running time.

        // 8. Repeat step 7, but with the Version 2 object.

        // This line is here just to test the Gradle build procedure.
        // You can delete it.
        System.out.println("Builds without errors and runs to completion.");
    }
}
