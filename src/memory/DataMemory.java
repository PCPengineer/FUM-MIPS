/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package memory;

/**
 *
 * @author Mrhb
 */
public class DataMemory extends Memory{

    private boolean memWrite;

    public void write(String address, String data) {
        if (address.length() != 32 && data.length() != 32) {
          throw new IllegalArgumentException("input is invalid");
        }
        if (memWrite) {
            int index = Integer.parseInt(address, 2) / 4;
            memory[index]=data;
        }
    }

    public boolean isMemWrite() {
        return memWrite;
    }

    public void setMemWrite(String memWrite) {
        this.memWrite = memWrite.equals("1");
    }
    
    
}
