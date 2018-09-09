/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.Date;

/**
 *
 * @author Ana
 */
public class Tarjeta {
    private int Numero;
    private Usuario usuario;
    private Date Fecha_Exp;				
    private int Ccv;


    /**
     * @return the Numero
     */
    public int getNumero() {
        return Numero;
    }

    /**
     * @param Numero the Numero to set
     */
    public void setNumero(int Numero) {
        this.Numero = Numero;
    }

    /**
     * @return the usuario
     */
    public Usuario getUsuario() {
        return usuario;
    }

    /**
     * @param usuario the usuario to set
     */
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    /**
     * @return the Fecha_Exp
     */
    public Date getFecha_Exp() {
        return Fecha_Exp;
    }

    /**
     * @param Fecha_Exp the Fecha_Exp to set
     */
    public void setFecha_Exp(Date Fecha_Exp) {
        this.Fecha_Exp = Fecha_Exp;
    }

    /**
     * @return the Ccv
     */
    public int getCcv() {
        return Ccv;
    }

    /**
     * @param Ccv the Ccv to set
     */
    public void setCcv(int Ccv) {
        this.Ccv = Ccv;
    }
 
}
