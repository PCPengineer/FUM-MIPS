/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mux;

import java.util.InputMismatchException;
import jdk.nashorn.internal.runtime.regexp.joni.constants.OPCode;

/**
 *
 * @author Aref
 */
public class Multiplexer {
    private String input0,input1,select,output;

    public void setInput0(String input0) {
        this.input0 = input0;
    }

    public void setInput1(String input1) {
        this.input1 = input1;
    }

    public void setSelect(String select) {
        this.select = select;
    }

    public String getOutput() {
        return output;
    }

    public void doOperation(){
        switch(select){
            case "0":output=input0;break;
            case "1":output=input1;break;
            default:
                throw new InputMismatchException("select signal: "+select+" is not a valid select signal");
        }
    }
    
}
