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
public class Logicw {
    private int balance=0;
    private int validwithdraw=0;
    public int getCurrentBalance(){
return balance;}
public void withdraw(int amount){
    if(amount>balance)
        validwithdraw=0;
    else {
        balance= this.balance - amount;
        validwithdraw=1;
    }
    }
public void deposit(int amount){
balance= balance + amount;
}
    public int getValidwithdraw() {
        return validwithdraw;
    }
}