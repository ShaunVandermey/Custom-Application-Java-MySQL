/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javastack;

import javax.swing.*;
import java.util.Stack;

/**
 *
 * @author User
 */
public class JavaStack {

    static Stack st = new Stack();

    /**
     * Adds an input into the stack
     * @param userString The input string to enter into the stack
     */
    public static void showPush(String userString) {
        st.push(userString);
   }

    /**
     * Shows the next item in the stack, then removes it
     */
   public static void showPopOne(){
       if (st.size() > 0){
           JOptionPane.showMessageDialog(null, st.pop());
       }
       else{
           JOptionPane.showMessageDialog(null, "Nothing else in the stack to pop.");
       }


   }

    /**
     * Shows and removes each item in the stack
     */
   public static void showPopAll() {
      if (st.size() > 0) {
          int size = st.size();
          String popped = "";
          for (int i = 0; i < size; i++) {
              popped += st.pop();
              popped += ", ";
          }
          JOptionPane.showMessageDialog(null, "Popped: " + popped);
      }
      else{
          JOptionPane.showMessageDialog(null, "The stack has nothing in it.");
      }
   }

    /*
    public static void main(String[] args) {
        // TODO code application logic here
        Stack st = new Stack();
      System.out.println("stack: " + st);
      showpush(st, 42);
      showpush(st, 66);
      showpush(st, 99);
      showpop(st);
      showpop(st);
      showpop(st);
      try {
         showpop(st);
      } catch (EmptyStackException e) {
         System.out.println("empty stack");
      }
      */
  
    }

