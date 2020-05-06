/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alu;

import java.util.InputMismatchException;

/**
 *
 * @author Aref
 */
public class ALU {

    private String input1, input2, opcode, output, zero;
    private final String AND = "0000", OR = "0001", ADD = "100000", SUB = "100010", SLT = "0111";
    private final int bits = 32;

//    public ALU(String input1, String input2, String opcode) {
//        this.input1 = input1;
//        this.input2 = input2;
//        this.opcode = opcode;
//    }

    public void setInput1(String input1) {
        this.input1 = input1;
    }

    public void setInput2(String input2) {
        this.input2 = input2;
    }

    public void setOpcode(String opcode) {
        this.opcode = opcode;
    }

    public String getOutput() {
        return output;
    }

    public String getZero() {
        return zero;
    }
    
    public void doOperatoin(){
        switch(opcode){
            case ADD:output=add();break;
            case SUB:output=sub();break;
            case SLT:zero=slt();break;
            case AND:output=and();break;
            case OR:output=or();break;
            default:throw new InputMismatchException("opcode: "+opcode+" is not a valid opcode");
        }
    }
    
    private String slt(){
        int i1,i2;
        i1=(int) Long.parseLong(input1, 2);
        i2=(int) Long.parseLong(input2, 2);
        if (i1<i2) {
            return "1";
        }else{
            return "0";
        }
    }
    
    private String or(){
        StringBuilder result=new StringBuilder();
        for (int i = 0; i < bits; i++) {
            if (input2.charAt(i)=='0' && input1.charAt(i)=='0') {
                result.append("0");
            }else{
                result.append("1");
            }
        }
        return result.toString();
    }

    private String and(){
        StringBuilder result=new StringBuilder();
        for (int i = 0; i < bits; i++) {
            if (input2.charAt(i)=='1' && input1.charAt(i)=='1') {
                result.append("1");
            }else{
                result.append("0");
            }
        }
        return result.toString();
    }
    
    private String add() {
        String result;
        result=addBitStrings(new StringBuilder(input1), new StringBuilder(input2));
        result = result.substring(result.length() - (bits));
        return result;
    }

    //return: input1-input2 in two's complement
    private String sub() {
        String twosCompliment,result;
        twosCompliment=findTwoscomplement(new StringBuffer(input2));
        result=addBitStrings(new StringBuilder(input1), new StringBuilder(twosCompliment));
        result = result.substring(result.length() - (bits), result.length());
        return result;
    }
    
    private String addBitStrings(StringBuilder str1,StringBuilder str2)  { 
        String result = ""; // To store the sum bits 
  
        // make the lengths same before adding 
        int length = makeEqualLength(str1, str2); 
  
        // Convert StringBuilder to Strings 
        String first = str1.toString(); 
        String second = str2.toString(); 
  
        int carry = 0; // Initialize carry 
  
        // Add all bits one by one 
        for (int i = length - 1; i >= 0; i--) 
        { 
            int firstBit = first.charAt(i) - '0'; 
            int secondBit = second.charAt(i) - '0'; 
  
            // boolean expression for sum of 3 bits 
            int sum = (firstBit ^ secondBit ^ carry) + '0'; 
  
            result = String.valueOf((char) sum) + result; 
  
            // boolean expression for 3-bit addition 
            carry = (firstBit & secondBit) |  
                    (secondBit & carry) |  
                    (firstBit & carry); 
        } 
          
        // if overflow, then add a leading 1 
        if (carry == 1) 
            result = "1" + result; 
        return result; 
    }   
// Method to find two's complement 
    private String findTwoscomplement(StringBuffer str) { 
        int n = str.length(); 
       
        // Traverse the string to get first '1' from 
        // the last of string 
        int i; 
        for (i = n-1 ; i >= 0 ; i--) 
            if (str.charAt(i) == '1') 
                break; 
       
        // If there exists no '1' concat 1 at the 
        // starting of string 
        if (i == -1) 
            return "1" + str; 
       
        // Continue traversal after the position of 
        // first '1' 
        for (int k = i-1 ; k >= 0; k--) 
        { 
            //Just flip the values 
            if (str.charAt(k) == '1') 
                str.replace(k, k+1, "0"); 
            else
                str.replace(k, k+1, "1"); 
        } 
       
        // return the modified string 
        return str.toString(); 
    } 
    
    private int makeEqualLength(StringBuilder str1,StringBuilder str2)  { 
        int len1 = str1.length(); 
        int len2 = str2.length(); 
        if (len1 < len2) 
        { 
            for (int i = 0; i < len2 - len1; i++) 
                str1.insert(0, '0'); 
            return len2; 
        }  
        else if (len1 > len2) 
        { 
            for (int i = 0; i < len1 - len2; i++) 
                str2.insert(0, '0'); 
        } 
  
        return len1; // If len1 >= len2 
    } 
    
}

