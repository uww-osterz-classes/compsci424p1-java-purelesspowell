/* COMPSCI 424 Program 1
 * Name:
 */
package compsci424.p1.java;

/**
 * The process control block structure that is used to track a
 * process's parent, first child, younger sibling, and older sibling
 * (if they exist) in Version 2.
 */
public class Version2PCB { //Version 2 needs first child and siblings
     int parent; //private?
     int firstChild;
     int youngSibling;
     int oldSibling;

     Version2PCB(int parent){
         this.parent = parent;
         this.firstChild = -1; //intiti to -1
         this.youngSibling = -1;
         this.oldSibling =-1;
     }
}
