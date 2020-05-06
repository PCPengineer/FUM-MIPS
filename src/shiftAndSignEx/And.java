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
public class And {
    private String input0,input1,output;

    public void setInput0(String input0) {
        this.input0 = input0;
    }

    public void setInput1(String input1) {
        this.input1 = input1;
    }

    public String getOutput() {
        return output;
    }

    public void doOperation(){
        if (input0.charAt(0)=='1' && input1.charAt(0)=='1') {
            output="1";
        }else{
            output="0";
        }
    }
    
}
