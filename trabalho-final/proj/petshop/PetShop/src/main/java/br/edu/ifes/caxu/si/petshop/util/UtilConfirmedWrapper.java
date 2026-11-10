package br.edu.ifes.caxu.si.petshop.util;

/**
 *
 * @author alexr
 */
public class UtilConfirmedWrapper {
    private boolean confirmed;
    
    public UtilConfirmedWrapper() {
        confirmed = false;
    }
    
    public void setValue(boolean confirmed) {
        this.confirmed = confirmed;
    }
    
    public boolean getConfirmed() {
        return confirmed;
    }
}
