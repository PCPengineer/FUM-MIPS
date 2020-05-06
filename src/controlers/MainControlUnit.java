/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlers;

/**
 *
 * @author Mrhb
 */
public class MainControlUnit {

    private boolean regDest;
    private boolean jump;
    private boolean branch;
    private boolean memRead;
    private boolean memToReg;
    private String ALUOp;
    private boolean memWrite;
    private boolean ALUSrc;
    private boolean regWrite;

    private boolean[] opCode = new boolean[6];

    public void initSignal(String opCode) {
        if (opCode.length() != 6) {
            throw new IllegalArgumentException("opcode is false");
        }
        prepare(opCode);
        boolean gate1;
        boolean gate2;
        boolean gate3;
        boolean gate4;
        boolean gate5;
        gate1 = !this.opCode[0] & !this.opCode[1] & !this.opCode[2] & !this.opCode[3] & !this.opCode[4] & !this.opCode[5];
        gate2 = this.opCode[0] & this.opCode[1] & !this.opCode[2] & !this.opCode[3] & !this.opCode[4] & this.opCode[5];
        gate3 = this.opCode[0] & this.opCode[1] & !this.opCode[2] & this.opCode[3] & !this.opCode[4] & this.opCode[5];
        gate4 = !this.opCode[0] & !this.opCode[1] & this.opCode[2] & !this.opCode[3] & !this.opCode[4] & !this.opCode[5];
        gate5 = this.opCode[0] & !this.opCode[1] & this.opCode[2] & this.opCode[3] & this.opCode[4] & this.opCode[5];

        jump = gate5;
        regDest = gate1;
        branch = gate4;
        memRead = gate2;
        memToReg = gate2;
        memWrite = gate3;
        ALUSrc = gate2 | gate3;
        regWrite = gate1 | gate2;

        String temp;
        temp = gate1 ? "1" : "0";
        temp += gate4 ? "1" : "0";
        ALUOp = temp;

    }

    private void prepare(String opCode) {
        for (int i = 0; i < opCode.length(); i++) {
            this.opCode[opCode.length() - i - 1] = opCode.charAt(i) != '0';
        }
    }

    public String isRegDest() {
        return regDest?"1":"0";
    }

    public String isJump() {
        return jump?"1":"0";
    }

    public String isBranch() {
        return branch?"1":"0";
    }

    public String isMemRead() {
        return memRead?"1":"0";
    }

    public String isMemToReg() {
        return memToReg?"1":"0";
    }

    public String getALUOp() {
        return ALUOp;
    }

    public String isMemWrite() {
        return memWrite?"1":"0";
    }

    public String isALUSrc() {
        return ALUSrc?"1":"0";
    }

    public String isRegWrite() {
        return regWrite?"1":"0";
    }

}
