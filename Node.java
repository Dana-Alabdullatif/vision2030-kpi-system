/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vision2030kpisystem;

/**
 *
 * @author dana
 */
public class Node {

    private KPI data;
    private Node next;
    
    
     //Constructor for Node
    public Node(KPI data) {
        this.data = data;    // T(n) = 1
        this.next = null;    // T(n) = 1
        //O(1), T(n)=1+1
    }
    
    
     //Get the KPI data stored in this node
    public KPI getData() {
        return data;         // T(n) = 1
        //O(1)
    }
    
  
     //Get the next node in the linked list
    public Node getNext() {
        return next;         // T(n) = 1
        //O(1)
    }
    
    //Set the next node in the linked list
    public void setNext(Node next) {
        this.next = next;    // T(n) = 1
        //O(1)
    }
}
    

