/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fum.mips.modules;

/**
 *
 * @author Aref
 */
public abstract class Module {
    String name;
    char[] input;
    char[] output;
    
    // get input bits from input array and place result of operation in output array
    public abstract void doOperation();
    
    public void setInput(char[] input){
        this.input=input;
    }
    
    public char[] getOutput(){
        return output;
    }
}
