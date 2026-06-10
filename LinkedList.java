/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vision2030kpisystem;

/**
 *
 * @author dana
 */
public class LinkedList {
// Custom Linked List Class with Tail Pointer Optimization

    private Node head;
    private Node tail;
    private int size;
    
    
     //Initializes an empty linked list
    public LinkedList() {
        this.head = null;    // T(n) = 1
        this.tail = null;    // T(n) = 1
        this.size = 0;       // T(n) = 1
        //O(1)
    }
    
    
     //Add element to the end of linked list
    public void add(KPI kpi) {
        Node newNode = new Node(kpi);    // T(n) = 1
        
        if (head == null) {              // T(n) = 1
            head = newNode;              // T(n) = 1
            tail = newNode;              // T(n) = 1
        } else {
            tail.setNext(newNode);       // T(n) = 1
            tail = newNode;              // T(n) = 1
        }
        size++;                          // T(n) = 1
        //O(1), T(n)=7
    }
    
    
      //@return The size of the linked list
    public int size() {
        return size;         // T(n) = 1
        //O(1)
    }
    
    
     //@return The first Node in the linked list
    public Node getHead() {
        return head;         // T(n) = 1
        //O(1)
    }
    
    
     //@return true if the list is empty, false otherwise
    public boolean isEmpty() {
        return size == 0;    // T(n) = 1
        //O(1)
    }
    
    //@return Array of KPI objects
    public KPI[] toArray() {
        KPI[] array = new KPI[size];     // T(n) = 1
        Node current = head;             // T(n) = 1
        int index = 0;                   // T(n) = 1
        
        while (current != null) {        // T(n) = n
            array[index] = current.getData();   // T(n) = 1
            current = current.getNext();        // T(n) = 1
            index++;                            // T(n) = 1
        }
        
        return array;                    // T(n) = 1
        //O(n), T(n)=7+n
    }
}
    

