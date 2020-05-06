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
public class InstructionMemory extends Memory{
    @Override
    public String getData(String address) {
        int index = Integer.parseInt(address, 2);
        index /= 4;
        return memory[index];
    }
}
