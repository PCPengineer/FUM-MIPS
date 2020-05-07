/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asembler;

import java.math.BigInteger;
import java.util.InputMismatchException;

/**
 *
 * @author Aref
 */
public class Asembler {

    private String[] mipsCode;
    private static char[] tempMachinCode;
    private String[] machineCode;
    private static int index = 0;
    private String[] R_TYPE = {"add:000000ssssstttttddddd00000100000",
        "sub:000000ssssstttttddddd00000100010", "slt:000000ssssstttttddddd00000101010",
        "or:000000ssssstttttddddd00000100101", "and:000000ssssstttttddddd00000100100"};
    private String[] I_TYPE = {"sw:101011ssssstttttiiiiiiiiiiiiiiii",
        "lw:100011ssssstttttiiiiiiiiiiiiiiii", "addi:001000ssssstttttiiiiiiiiiiiiiiii",
        "slti:001010ssssstttttiiiiiiiiiiiiiiii", "andi:001100ssssstttttiiiiiiiiiiiiiiii",
        "ori:001101ssssstttttiiiiiiiiiiiiiiii", "beq:000100ssssstttttiiiiiiiiiiiiiiii",
        "bne:000101ssssstttttiiiiiiiiiiiiiiii"};
    private String[] J_TYPE = {"j:000010iiiiiiiiiiiiiiiiiiiiiiiiii"};

    // format:
    //add $5,$6,$7;
    public Asembler(String mipsCode) {
        this.mipsCode = mipsCode.split(";");
        machineCode = new String[this.mipsCode.length];
        parse();
    }

    private void parse() {
        for (int i = 0; i < mipsCode.length; i++) {
            parseSingleCode(mipsCode[i]);
        }
    }

    private void parseSingleCode(String line) {
        String[] t;
        t = line.split(" ");
        switch (findType(t[0])) {
            case "R":
                generateMachinCodeRtype(t);
                break;
            case "I":
                generateMachinCodeItype(t);
                break;
            case "J":
                generateMachinCodeJtype(t);
                break;
        }
    }

    private void generateMachinCodeRtype(String[] commandAndData) {
        StringBuilder sb = new StringBuilder();
        String[] registers = commandAndData[1].replace("$", "").split(",");
        String source1, source2, dest;
        source1 = getNLenghtBinaryString(registers[1], 5);
        source2 = getNLenghtBinaryString(registers[2], 5);
        dest = getNLenghtBinaryString(registers[0], 5);
        System.out.println("s1:"+source1+" s2:"+source2+" des:"+dest);
        for (int i = 6; i < 11; i++) {
            tempMachinCode[i] = source1.charAt(i - 6);
        }
        for (int i = 11; i < 16; i++) {
            tempMachinCode[i] = source2.charAt(i - 11);
        }
        for (int i = 16; i < 21; i++) {
            tempMachinCode[i] = dest.charAt(i - 16);
        }
        for (int i = 0; i < tempMachinCode.length; i++) {
            sb.append(tempMachinCode[i]);
        }
        machineCode[index] = sb.toString();
        index++;
    }

    //braye dastor j bayad adad decimal mazrab 4 benvisim
    private void generateMachinCodeJtype(String[] commandAndData) {
        StringBuilder stringBuilder = new StringBuilder();
        String imm = getNLenghtBinaryString(String.valueOf(Integer.valueOf(commandAndData[1]) / 4), 26);
        for (int i = 6; i < 32; i++) {
            tempMachinCode[i] = imm.charAt(i - 6);
        }
        for (int i = 0; i < tempMachinCode.length; i++) {
            stringBuilder.append(tempMachinCode[i]);
        }
        machineCode[index] = stringBuilder.toString();
        index++;
    }

    private void generateMachinCodeItype(String[] commandAndData) {
        StringBuilder stringBuilder = new StringBuilder();
        if (commandAndData[0].equals("lw") || commandAndData[0].equals("sw")) {
            String destReg, startReg, offset;
            destReg = commandAndData[1].replace("$", "").split(",")[0];
            offset = commandAndData[1].replace("$", "").split(",")[1].substring(0, commandAndData[1].replace("$", "").split(",")[1].indexOf("("));
            startReg = commandAndData[1].replace("$", "").split(",")[1].substring(commandAndData[1].replace("$", "").split(",")[1].indexOf("(") + 1, commandAndData[1].replace("$", "").split(",")[1].indexOf(")"));
            destReg = getNLenghtBinaryString(destReg, 5);
            startReg = getNLenghtBinaryString(startReg, 5);
            offset = getNLenghtBinaryString(offset, 16);
            for (int i = 6; i < 11; i++) {
                tempMachinCode[i] = startReg.charAt(i - 6);
            }
            for (int i = 11; i < 16; i++) {
                tempMachinCode[i] = destReg.charAt(i - 11);
            }
            for (int i = 16; i < 32; i++) {
                tempMachinCode[i] = offset.charAt(i - 16);
            }
            for (int i = 0; i < tempMachinCode.length; i++) {
                stringBuilder.append(tempMachinCode[i]);
            }
            machineCode[index] = stringBuilder.toString();
            index++;
        } else {
            String destReg,startReg,imm;
            destReg=commandAndData[1].replace("$", "").split(",")[0];
            startReg=commandAndData[1].replace("$", "").split(",")[1];
            imm=commandAndData[1].split(",")[2];
            destReg=getNLenghtBinaryString(destReg, 5);
            startReg=getNLenghtBinaryString(startReg, 5);
            imm=getNLenghtBinaryString(imm, 16);
            for (int i = 6; i < 11; i++) {
                tempMachinCode[i] = startReg.charAt(i - 6);
            }
            for (int i = 11; i < 16; i++) {
                tempMachinCode[i] = destReg.charAt(i - 11);
            }
            for (int i = 16; i < 32; i++) {
                tempMachinCode[i] = imm.charAt(i - 16);
            }
            for (int i = 0; i < tempMachinCode.length; i++) {
                stringBuilder.append(tempMachinCode[i]);
            }
            machineCode[index] = stringBuilder.toString();
            index++;
        }
    }

    private String getNLenghtBinaryString(String number, int numberOfBits) {
        StringBuffer sb = new StringBuffer();
        number = Integer.toBinaryString(Integer.valueOf(number));
        for (int i = 0; i < numberOfBits - number.length(); i++) {
            sb.append("0");
        }
        sb.append(number);
        return sb.toString();
    }

    private String findType(String code) {
        for (int i = 0; i < R_TYPE.length; i++) {
            if (R_TYPE[i].contains(code)) {
                tempMachinCode = R_TYPE[i].split(":")[1].toCharArray();
                return "R";
            }
        }
        for (int i = 0; i < I_TYPE.length; i++) {
            if (I_TYPE[i].contains(code)) {
                tempMachinCode = I_TYPE[i].split(":")[1].toCharArray();
                return "I";
            }
        }
        for (int i = 0; i < J_TYPE.length; i++) {
            if (J_TYPE[i].contains(code)) {
                tempMachinCode = J_TYPE[i].split(":")[1].toCharArray();
                return "J";
            }
        }
        throw new InputMismatchException("given type:" + code + " is not supported!!!");
    }

    public String[] getMachinCode() {
        return machineCode;
    }

    public static void main(String[] args) {
        Asembler s = new Asembler("lw $17,0($0);addi $18,$0,0;addi $16,$0,8;lw $19,0($16);lw $20,0($16);slt $21,$18,$17;beq $21,$0,200;add $22,$18,$18;add $22,$22,$22;add $21,$16,$22;lw $23,0($21);slt $24,$23,$20;beq $24,$0,15;add $20,$23,$0;j 5;slt $25,$23,$19;beq $25,$0,18;j 5;add $19,$23,$0;j 5;");
        for (int i = 0; i < s.getMachinCode().length; i++) {
            System.out.println("\""+s.getMachinCode()[i]+"\""+",");
        }
//lw $17,0($0);addi $18,$0,0;addi $16,$0,8;lw $19,0($16);lw $20,0($16);slt $21,$18,$17;beq $21,$0,200;add $22,$18,$18;add $22,$22,$22;add $21,$16,$22;lw $23,0($21);slt $24,$23,$20;beq $24,$0,15;add $20,$23,$0;j 5;slt $25,$23,$19;beq $25,$0,18;j 5;add $19,$23,$0;j 5;
    }

}
