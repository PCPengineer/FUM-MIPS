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
public class Memory {

    final String[] memory = new String[128];
    private boolean memRead;

    public void initMemory(String[] data, int start) {
        for (int i = start; i < data.length; i++) {
            if (data[1].length() == 32) {
                memory[i] = data[i];
            } else {
                throw new IllegalArgumentException("data shoud be 32 bit");
            }
        }
    }

    public String getData(String address) {
        if(memRead){
        int index = Integer.parseInt(address, 2);
        index /= 4;
        return memory[index];
        }else{
            return "";
        }
    }

    public boolean isMemRead() {
        return memRead;
    }

    public void setMemRead(boolean memRead) {
        this.memRead = memRead;
    }
    
    
}
