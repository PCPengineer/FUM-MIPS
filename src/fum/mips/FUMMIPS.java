/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fum.mips;

import alu.ALU;
import controlers.AlUControler;
import controlers.MainControlUnit;
import memory.*;
import mux.Multiplexer;
import pc.Pc;
import register.RegisterFile;
import shiftAndSignEx.And;
import shiftAndSignEx.ShiftLeft2;
import shiftAndSignEx.SignExtend;

/**
 *
 * @author Aref
 */
public class FUMMIPS {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Pc pc = new Pc();
        InstructionMemory instructionMemory = new InstructionMemory();
        MainControlUnit mainControlUnit = new MainControlUnit();
        RegisterFile registerFile = new RegisterFile();
        SignExtend signExtend = new SignExtend();
        ShiftLeft2 shiftLeft = new ShiftLeft2();
        ShiftLeft2 shiftLeft2 = new ShiftLeft2();
        ALU jumpAlu = new ALU();
        ALU mainAlu = new ALU();
        ALU alu = new ALU();
        AlUControler alUControler = new AlUControler();
        Multiplexer mux1 = new Multiplexer();
        Multiplexer mux2 = new Multiplexer();
        Multiplexer mux3 = new Multiplexer();
        Multiplexer mux4 = new Multiplexer();
        Multiplexer mux5 = new Multiplexer();
        And and = new And();
        DataMemory dataMemory = new DataMemory();
        int counter ;
        String[] memoryIns = {};
        String[] storage = {};

        counter=memoryIns.length;
        pc.setInput("0000000000000000000000000000000000000000");
        alu.setInput1("0000000000000000000000000000000000000100");
        alu.setOpcode("100000");
        jumpAlu.setOpcode("100000");

        instructionMemory.initMemory(memoryIns, 0);
        dataMemory.initMemory(storage, 0);
        
        while (counter>0) {

            alu.setInput2(pc.getOutput());
            alu.doOperatoin();
            mainControlUnit.initSignal(instructionMemory.getData(pc.getOutput()).substring(0, 6));

            shiftLeft.setInput(instructionMemory.getData(pc.getOutput()).substring(6));

            mux1.setInput0(instructionMemory.getData(pc.getOutput()).substring(11, 16));
            mux1.setInput1(instructionMemory.getData(pc.getOutput()).substring(16, 21));
            mux1.setSelect(mainControlUnit.isRegDest());
            mux1.doOperation();

            registerFile.setRegWrite(mainControlUnit.isRegWrite());

            signExtend.setInput(instructionMemory.getData(pc.getOutput()).substring(16));
            signExtend.doOperation();

            shiftLeft2.setInput(signExtend.getOutput());
            shiftLeft2.doOperation();

            jumpAlu.setInput1(alu.getOutput());
            jumpAlu.setInput2(shiftLeft2.getOutput());
            jumpAlu.doOperatoin();

            mux2.setInput0(registerFile.readData2(instructionMemory.getData(pc.getOutput()).substring(11, 16)));
            mux2.setInput1(signExtend.getOutput());
            mux2.setSelect(mainControlUnit.isALUSrc());
            mux2.doOperation();

            mainAlu.setOpcode(alUControler.getALUOPCode(mainControlUnit.getALUOp(), instructionMemory.getData(pc.getOutput()).substring(26)));
            mainAlu.setInput1(registerFile.readData1(instructionMemory.getData(pc.getOutput()).substring(6, 11)));
            mainAlu.setInput2(mux2.getOutput());
            mainAlu.doOperatoin();

            and.setInput0(mainControlUnit.isBranch());
            and.setInput1(mainAlu.getZero());
            and.doOperation();

            mux3.setInput0(alu.getOutput());
            mux3.setInput1(jumpAlu.getOutput());
            mux3.setSelect(and.getOutput());
            mux3.doOperation();

            mux4.setInput0(mux3.getOutput());
            mux4.setInput1(shiftLeft.getOutput());
            mux4.setSelect(mainControlUnit.isJump());
            mux4.doOperation();

            dataMemory.setMemRead(mainControlUnit.isMemRead());
            dataMemory.setMemWrite(mainControlUnit.isMemWrite());
            dataMemory.write(mainAlu.getOutput(), registerFile.readData2(instructionMemory.getData(pc.getOutput()).substring(11, 16)));

            mux5.setInput0(mainAlu.getOutput());
            mux5.setInput1(dataMemory.getData(mainAlu.getOutput()));
            mux5.setSelect(mainControlUnit.isMemToReg());
            mux5.doOperation();

            registerFile.writeData(mux1.getOutput(), mux5.getOutput());

            pc.setInput(mux4.getOutput());
            counter--;
        }

    }

}
