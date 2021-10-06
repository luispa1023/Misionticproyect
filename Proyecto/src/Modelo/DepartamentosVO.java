/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author JuanGui
 */
public class DepartamentosVO {

    private int iddpto;
    private String nomdpto ;

    public int getIddpto() {
        return iddpto;
    }

    public void setIddpto(int iddpto) {
        this.iddpto = iddpto;
    }

    public String getNomdpto() {
        return nomdpto;
    }

    public void setNomdpto(String nomdpto) {
        this.nomdpto = nomdpto;
    }

    @Override
    public String toString() {
        return nomdpto;
    }
    
   
    
}