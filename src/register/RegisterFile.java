/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package register;

/**
 *
 * @author Mrhb
 */
public class RegisterFile {

    private final Register[] registers = new Register[32];
    private boolean regWrite = true;

    public RegisterFile() {
        init();
    }

    private void init() {
        for (int i = 0; i < registers.length; i++) {
            registers[i] = new Register();
        }
        registers[0].setData(String.format("%32s", Integer.toHexString(0)).replaceAll(" ", "0"));
        registers[0].setEnable(false);
    }

    public String readData1(String regCode) {
        if (getValue(regCode) > 31 || getValue(regCode) < 0) {
            throw new IndexOutOfBoundsException("register " + getValue(regCode) + " not found");
        }
        return registers[getValue(regCode)].getData();
    }

    public String readData2(String regCode) {
        if (getValue(regCode) > 31 || getValue(regCode) < 0) {
            throw new IndexOutOfBoundsException("register " + getValue(regCode) + " not found");
        }
        return registers[getValue(regCode)].getData();
    }

    public boolean writeData(String regCode, String data) {
        if (getValue(regCode) > 31 || getValue(regCode) < 0) {
            if (getValue(data) == 0) {
                throw new IllegalArgumentException("you cant write in register 0 ");
            }
            throw new IndexOutOfBoundsException("register " + getValue(regCode) + " not found");
        }
        if (!regWrite) {
            return false;
        }
        return registers[getValue(regCode)].setData(data);
    }

    private int getValue(String value) {
        return Integer.parseInt(value, 2);
    } 

    public boolean isRegWrite() {
        return regWrite;
    }

    public void setRegWrite(boolean regWrite) {
        this.regWrite = regWrite;
    }
    
    
}
