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
    Version1PCB head;
    /**
     * Default constructor. Use this to allocate (if needed) and
     * initialize the PCB array, create the PCB for process 0, and do
     * any other initialization that is needed. 
     */
    public Version1(int k) {
        this.head = new Version1PCB(-1);
    }

    /**
     * Creates a new child process of the process with ID parentPid. 
     * @param parentPid the PID of the new process's parent
     * @return 0 if successful, not 0 if unsuccessful
     */
    int create(int parentPid) {
       Version1PCB parent = findParent(parentPid);
       if(parent == null){
           return -1;
       }
        Version1PCB child = new Version1PCB(parentPid);

        parent.children.add(parentPid);
        appendTail(parent,child);

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
Version1PCB target = findTarget(targetPid, head);
if(target == null){
    return -1;
}

for(int childPid:target.children){
    destroy(childPid);
    //remove the PCB we want
}
removeFromList(head,target);
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
    Version1PCB findTarget(int targetPid, Version1PCB curr){
        while (curr != null && curr.parent!= targetPid){
            curr= curr.next;
        }
        return curr;
    }
    Version1PCB findParent(int parentPid){
        Version1PCB curr = head;
        while(curr != null && curr.parent != parentPid){
            curr = curr.next;
        }
        return curr;
    }
    void appendTail(Version1PCB parent, Version1PCB child){
        if(parent.next == null){
            parent.next = child;
        }else{
            Version1PCB temp = parent.next;
            while(temp.next != null){
                temp=temp.next;
            }
            temp.next = child;
        }
    }

    void removeFromList(Version1PCB head, Version1PCB target){
        Version1PCB curr = head;
        while(curr != null && curr.next != target){
            curr = curr.next;
        }
        if(curr != null){
            curr.next = target.next;
        }
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
Version1PCB curr = head.next;
while(curr != null){
    System.out.println("Process " + curr.parent + ": parent is " + curr.parent);
    if(!curr.children.isEmpty()){
        System.out.println(" and children are ");
        for(int childPid:curr.children){
            System.out.println(childPid + " ");
        }
    }
    else{
        System.out.println(" and has no children");
    }
    System.out.println();
    curr= curr.next;
}
    }

    /* If you need or want more methods, feel free to add them. */
    
}
