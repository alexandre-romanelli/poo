package br.edu.ifes.caxu.si.petshop.util;

/**
 *
 * @author alexr
 */
public class UtiIOperacaoFeita {
    private boolean operacaoFeita;
    
    public UtiIOperacaoFeita() {
        operacaoFeita = false;
    }
    
    public void setValue(boolean operacaoFeita) {
        this.operacaoFeita = operacaoFeita;
    }
    
    public boolean getOperacaoFeita() {
        return operacaoFeita;
    }
}
