/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shiftAndSignEx;

/**
 *
 * @author Aref
 */
public class ShiftLeft2 {
    private String input,output;

    public void setInput(String input) {
        this.input = input;
    }

    public String getOutput() {
        return output;
    }

    public void doOperation(){
        StringBuilder sb=new StringBuilder(input);
        sb.append("00");
        output=sb.toString();
    }

}
