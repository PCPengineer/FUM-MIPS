/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MachinCode;

/**
 *
 * @author Aref
 */
public class InstructionMemory {
    char[][] instructionMemory;

    public InstructionMemory(char[][] instructionMemory) {
        this.instructionMemory=instructionMemory;
    }
    
    
    
    public char[] readInstructionAt(char[] address){
        char[] temp=new char[32];
        StringBuilder s=new StringBuilder();
        for (int i = 0; i < address.length; i++) {
            s.append(address[i]);
        }
        int addressLine=Integer.parseInt(s.toString(), 2);
        for (int i = 0; i < 32; i++) {
            temp[i]=instructionMemory[addressLine][i];
        }
        return temp;
    }
}
