/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author Ana
 */
public class Carrito {

    private int Id;
    private Usuario usuario;
    private boolean Checkout;
    /**
     * @return the Id
     */
    public int getId() {
        return Id;
    }

    /**
     * @param Id the Id to set
     */
    public void setId(int Id) {
        this.Id = Id;
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
     * @return the Checkout
     */
    public boolean isCheckout() {
        return Checkout;
    }

    /**
     * @param Checkout the Checkout to set
     */
    public void setCheckout(boolean Checkout) {
        this.Checkout = Checkout;
    }
   

}
