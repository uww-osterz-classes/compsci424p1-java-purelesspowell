/* COMPSCI 424 Program 1
 * Name:
 */
package compsci424.p1.java;

import java.util.ArrayList;
import java.util.List;

/**
 * Implements the process creation hierarchy for Version 1, which uses
 * linked lists.
 * 
 * This is a template. Program1.java *must* contain the main class
 * for this program. Otherwise, feel free to edit these files, even
 * these pre-written comments or my provided code. You can add any
 * classes, methods, and data structures that you need to solve the
 * problem and display your solution in the correct format.
 */
public class Version1 {
    // Declare any class/instance variables that you need here.
    List<Version1PCB> PCBList;
    /**
     * Default constructor. Use this to allocate (if needed) and
     * initialize the PCB array, create the PCB for process 0, and do
     * any other initialization that is needed. 
     */
    public Version1(int k) {
        this.PCBList = new ArrayList<>();
        for(int i = 0; i < k; i++){
            PCBList.add(new Version1PCB(-1)); //empty
        }
    }

    /**
     * Creates a new child process of the process with ID parentPid. 
     * @param parentPid the PID of the new process's parent
     * @return 0 if successful, not 0 if unsuccessful
     */
    int create(int parentPid) {
        if(parentPid < 0 || parentPid >= PCBList.size()){
            return -1;
        }
        Version1PCB child = new Version1PCB(parentPid);

        Version1PCB parent = PCBList.get(parentPid);

        parent.children.add(parentPid);
        PCBList.add(child);
        // If parentPid is not in the process hierarchy, do nothing; 
        // your code may return an error code or message in this case,
        // but it should not halt

        // Assuming you've found the PCB for parentPid in the PCB array:
        // 1. Allocate and initialize a free PCB object from the array
        //    of PCB objects

        // 2. Insert the newly allocated PCB object into parentPid's
        //    list of children

        // You can decide what the return value(s), if any, should be.
        // If you change the return type/value(s), update the Javadoc.
        return 0; // often means "success" or "terminated normally"
    }

    /**
     * Recursively destroys the process with ID parentPid and all of
     * its descendant processes (child, grandchild, etc.).
     * @param targetPid the PID of the process to be destroyed
     * @return 0 if successful, not 0 if unsuccessful
     */
    public int destroy (int targetPid) {
if(targetPid < 0 || targetPid >= PCBList.size()){
    return -1;//
}

Version1PCB target = PCBList.get(targetPid);
for(int childPid: target.children){
    destroy(childPid);
}
PCBList.remove(targetPid); //remove the PCB we want
         // If targetPid is not in the process hierarchy, do nothing; 
         // your code may return an error code or message in this case,
         // but it should not halt

         // Assuming you've found the PCB for targetPid in the PCB array:
         // 1. Recursively destroy all descendants of targetPid, if it
         //    has any, and mark their PCBs as "free" in the PCB array 
         //    (i.e., deallocate them)

         // 2. Remove targetPid from its parent's list of children

         // 3. Deallocate targetPid's PCB and mark its PCB array entry
         //    as "free"

         // You can decide what the return value(s), if any, should be.
         // If you change the return type/value(s), update the Javadoc.
        return 0; // often means "success" or "terminated normally"
    }

    /**
     * Traverse the process creation hierarchy graph, printing
     * information about each process as you go. See Canvas for the
     * *required* output format. 
     *         
     * You can directly use "System.out.println" statements (or
     * similar) to send the required output to stdout, or you can
     * change the return type of this function to return the text to
     * the main program for printing. It's your choice. 
     */
    public void showProcessInfo() {
for(int i = 0; i < PCBList.size();i++){
    Version1PCB PCB = PCBList.get(i);
    System.out.println("Process " + i + "; parent is " + PCB.parent);
    if(!PCB.children.isEmpty()){
        System.out.print(" and children are ");
        for(int childPid : PCB.children){
            System.out.println(childPid+" ");
        }
    }
    else{
        System.out.print(" and has no children");

    }
    System.out.println();
    System.out.println();

}
    }

    /* If you need or want more methods, feel free to add them. */
    
}
