/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simple.atm.project;


import java.util.LinkedList;

/**
 *
 * @author Amir
 */
public class History {
   private int top=0;
     LinkedList<Integer> Amount = new LinkedList<Integer>();
     LinkedList<String> Type = new LinkedList<String>();
    public void stack(int amount, String message)
{

  Amount.add(amount);
 Type.add(message);
    if (top>5)
    {
      Amount.removeFirst();
      Type.removeFirst();
      top--;
    }
    this.top++;
    
}

    public int getTop() {
        return top;
    }
    
}
