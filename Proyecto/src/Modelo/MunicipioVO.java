/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author Jhon
 */
public class MunicipioVO {
    
    private int IdMcpio;
    private int IdDpto;
    private String NomMcpio;

    public int getIdMcpio() {
        return IdMcpio;
    }

    public void setIdMcpio(int IdMcpio) {
        this.IdMcpio = IdMcpio;
    }

    public int getIdDpto() {
        return IdDpto;
    }

    public void setIdDpto(int IdDpto) {
        this.IdDpto = IdDpto;
    }

    public String getNomMcpio() {
        return NomMcpio;
    }

    public void setNomMcpio(String NomMcpio) {
        this.NomMcpio = NomMcpio;
    }
    
    @Override
    public String toString() {
        return NomMcpio;
    }
    
}
