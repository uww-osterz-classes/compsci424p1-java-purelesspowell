/* COMPSCI 424 Program 1
 * Name:
 */
package compsci424.p1.java;

/*** import jdk.nashorn.internal.runtime.Version; ***/

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
Version2PCB head;
    /**
     * Default constructor. Use this to allocate (if needed) and
     * initialize the PCB array, create the PCB for process 0, and do
     * any other initialization that is needed. 
     */
    public Version2(int k) {
this.head = new Version2PCB(-1);
    }
    
    /**
     * Creates a new child process of the process with ID parentPid. 
     * @param parentPid the PID of the new process's parent
     * @return 0 if successful, not 0 if unsuccessful
     */
    int create(int parentPid) {
Version2PCB parent = findParent(parentPid);
        if(parent == null){
            return -1;
        }

        Version2PCB child = new Version2PCB(parentPid);
        appendTail(parent,child);

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
Version2PCB target = findTarget(targetPid, head);
if(target == null){
    return -1;
}
fix(target);
removeFromList(head,target);


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
   private Version2PCB firstChildNode(Version2PCB n){
        if(n == null){
            return null;
        }
        if(n.firstChild != -1){
            return findTarget(n.firstChild,head);
        }
        return null;
   }
   Version2PCB findParent(int parentPid){
        Version2PCB curr = head;

        while(curr != null && curr.parent != parentPid){
            curr = curr.next;
        }
        return curr;
   }
   Version2PCB findTarget(int targetPid, Version2PCB curr){
        while (curr != null && curr.parent!= targetPid){
            Version2PCB child = findTarget(targetPid,firstChildNode(curr));
            if(child != null){
                return child;
            }
            curr= curr.next;
        }
        return curr;
   }
   void fix(Version2PCB target){
        if(target.parent != -1){
            Version2PCB parent = findParent(target.parent);
            if(parent.firstChild == target.firstChild){
                parent.firstChild = target.youngSibling;
            }
            if(target.youngSibling != -1){
                Version2PCB youngSibling = findTarget(target.youngSibling, firstChildNode(parent));
                if(youngSibling != null){
                    youngSibling.oldSibling = target.oldSibling;
                }
            }
        }
   }
   void appendTail(Version2PCB parent, Version2PCB child){
        if(parent.firstChild == -1){
            parent.firstChild = child.parent;
        }
        else{
            Version2PCB temp = findTarget(parent.firstChild, head);
            while(temp.youngSibling != -1){
                temp = findTarget(temp.youngSibling, head);
            }
            temp.youngSibling = child.parent;
            child.oldSibling = temp.parent;
        }
   }
   void removeFromList(Version2PCB head, Version2PCB target){
        Version2PCB curr = head;
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
   void showProcessInfo() {
Version2PCB curr = firstChildNode(head);
while(curr != null){
    System.out.print("Process " + curr.parent + ": parent is " + curr.parent);
    if(curr.firstChild!=-1){
        System.out.print(" and childen are ");
        Version2PCB child = findTarget(curr.firstChild,head);
        while(child != null){

            System.out.print(child.parent + " ");
            child = findTarget(child.youngSibling, head);
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
