/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simple.atm.project;

/**
 *
 * @author Amir
 */
public class Valid {
    public static boolean validate(String message)
{ 
  if("2222060".equals(message)){
      return true;}
  else return false;
}
    public static int convert(String input)
    {
        return Integer.parseInt(input);  
    }
    
}