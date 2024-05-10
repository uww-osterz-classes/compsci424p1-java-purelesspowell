/* COMPSCI 424 Program 1
 * Name:
 */
package compsci424.p1.java;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;

/**
 * Implements the process creation hierarchy for Version 2, which does
 * not use linked lists.
 * 
 * This is a template. Program1.java *must* contain the main class
 * for this program. Otherwise, feel free to edit these files, even
 * these pre-written comments or my provided code. You can add any
 * classes, methods, and data structures that you need to solve the
 * problem and display your solution in the correct format.
 */
public class Version2 {
    // Declare any class/instance variables that you need here.
List<Version2PCB> PCBList;
    /**
     * Default constructor. Use this to allocate (if needed) and
     * initialize the PCB array, create the PCB for process 0, and do
     * any other initialization that is needed. 
     */
    public Version2(int k) {
this.PCBList = new ArrayList<>();
for(int i = 0; i < k; i++){
    PCBList.add(new Version2PCB(-1));
}
    }
    
    /**
     * Creates a new child process of the process with ID parentPid. 
     * @param parentPid the PID of the new process's parent
     * @return 0 if successful, not 0 if unsuccessful
     */
    int create(int parentPid) {

        if(parentPid < 0 || parentPid>=PCBList.size()){
            return -1;
        }
        Version2PCB parent = PCBList.get(parentPid);
        Version2PCB child = new Version2PCB(parentPid);
        PCBList.add(child);
        if(parent.firstChild == -1){
            parent.firstChild = PCBList.indexOf((child)); // There are no other children so you are first
        }
        else{
            int siblingPid = parent.firstChild;
            while(PCBList.get(siblingPid).youngSibling != -1){
                siblingPid = PCBList.get(siblingPid).youngSibling;
            }
            PCBList.get(siblingPid).youngSibling = PCBList.indexOf(child);
            child.oldSibling = siblingPid;
        }
        // If parentPid is not in the process hierarchy, do nothing; 
        // your code may return an error code or message in this case,
        // but it should not halt

        // Assuming you've found the PCB for parentPid in the PCB array:
        // 1. Allocate and initialize a free PCB object from the array
        //    of PCB objects

        // 2. Connect the new PCB object to its parent, its older
        //    sibling (if any), and its younger sibling (if any)

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
    int destroy (int targetPid) {
if(targetPid < 0 || targetPid >= PCBList.size()){
    return -1;
}

Version2PCB target = PCBList.get(targetPid);
if(target.parent != -1){
    Version2PCB parent = PCBList.get(target.parent);

    if(target.youngSibling != -1){
        PCBList.get(target.youngSibling).oldSibling = target.oldSibling;
    }

    if(target.oldSibling != -1){
        PCBList.get(target.oldSibling).youngSibling = target.youngSibling;
    }
}
for(int childPid = target.firstChild; childPid != -1;){
    int next = PCBList.get(childPid).youngSibling;
    destroy(childPid);
    childPid = next;
}
PCBList.remove(targetPid);

        // If targetPid is not in the process hierarchy, do nothing; 
        // your code may return an error code or message in this case,
        // but it should not halt

        // Assuming you've found the PCB for targetPid in the PCB array:
        // 1. Recursively destroy all descendants of targetPid, if it
        //    has any, and mark their PCBs as "free" in the PCB array 
        //    (i.e., deallocate them)

        // 2. Adjust connections within the hierarchy graph as needed to
        //    re-connect the graph

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
   void showProcessInfo() {
for(int k = 0; k < PCBList.size();k++){
    Version2PCB PCB = PCBList.get(k);
    System.out.print(" Process " + k + ": parent is " + PCB.parent);
    if(PCB.firstChild != -1){
        System.out.print(" and children are ");
        int childPid = PCB.firstChild;
        while(childPid != -1){
            System.out.print(childPid + " ");
            childPid = PCBList.get(childPid).youngSibling;
        }
    }
    else{
        System.out.print(" and has no children");
    }
    System.out.println();
}
   }

   /* If you need or want more methods, feel free to add them. */

}
