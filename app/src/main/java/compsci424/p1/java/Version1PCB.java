/* COMPSCI 424 Program 1
 * Name:
 */
package compsci424.p1.java;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * The process control block structure that is used to track a
 * process's parent and children (if any) in Version 1.
 */
public class Version1PCB { // The PCB is just for initializing parent and child
    int parent;
    LinkedList<Integer> children;
    Version1PCB next;

    Version1PCB(int parent){
        this.parent = parent;
        this.children = new LinkedList<>();
        this.next = null;
    }
}
