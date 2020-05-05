/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package register;

import java.util.InputMismatchException;

/**
 *
 * @author Mrhb
 */
public class Register {

    private boolean enable = true;
    private String data;

    private boolean isValidData(String data) {
        if (data.length() != 32) {
            return false;
        }
        for (int i = 0; i < data.length(); i++) {
            if (data.charAt(i) != '0' || data.charAt(i) != '1') {
                return false;
            }
        }
        return true;
    }

    public String getData() {
        if (data != null) {
            return data;
        } else {
            throw new RuntimeException("register is empty");
        }
    }

    public boolean setData(String data) {
        if (isValidData(data)) {
            if (enable) {
                this.data = data;
                return true;
            } else {
                return false;
            }
        } else {
            throw new InputMismatchException("invalidData data");
        }
    }

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

}
