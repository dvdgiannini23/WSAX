/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

public class BeanBalancing {
    	String codCliente="";
	String saldo = "";
	String deudaVencida = "";
        String msgResult;
        String msgDetails;

    public String getCodCliente() {
        return codCliente;
    }

    public void setCodCliente(String codCliente) {
        this.codCliente = codCliente.trim();
    }

    public String getSaldo() {
        return saldo;
    }

    public void setSaldo(String saldo) {
        this.saldo = saldo.trim();
    }

    public String getDeudaVencida() {
        return deudaVencida;
    }

    public void setDeudaVencida(String deudaVencida) {
        this.deudaVencida = deudaVencida.trim();
    }

    public String getMsgResult() {
        return msgResult;
    }

    public void setMsgResult(String msgResult) {
        this.msgResult = msgResult;
    }

    public String getMsgDetails() {
        return msgDetails;
    }

    public void setMsgDetails(String msgDetails) {
        this.msgDetails = msgDetails;
    }
        
        
}
