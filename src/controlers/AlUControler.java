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
public class AlUControler {

    private final static String ADD = "100000";
    private final static String SUB = "100010";

    public String getALUOPCode(String controlCode, String function) {
        switch (controlCode) {
            case "00":
                return ADD;
            case "01":
                return SUB;
            default:
                return function;
        }
    }
}
