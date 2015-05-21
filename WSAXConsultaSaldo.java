/*
Este servicio web está usando NTLM authorization (autentificación de microsoft)
 */

import java.net.Authenticator;
import javax.xml.bind.JAXBElement;
import org.tempuri.ObjectFactory;
import org.tempuri.PWCustAgingBalanceGetCustomerBalanceArrayAifFaultFaultFaultMessage;
import org.tempuri.PWCustAgingBalanceGetCustomerBalanceArrayResponse;


public class WSAXConsultaSaldo{
    
    @SuppressWarnings("all")
     public BeanBalancing getConsultationBalancing(String codeClient){
//Bean de trabajo         
        BeanBalancing objBeanBalancing = new BeanBalancing();
//Parámetro que se pasará a AX, instancia de la clase        
        org.tempuri.PWCustAgingBalanceGetCustomerBalanceArrayRequest parameters;
         parameters = new org.tempuri.PWCustAgingBalanceGetCustomerBalanceArrayRequest();
//Declaro objectFactory         
        ObjectFactory objFactory = new ObjectFactory();
        
        try {           
            JAXBElement<String> codeClientJAXB = objFactory.createPWCustAgingBalanceGetCustomerBalanceArrayRequestCustAccount(codeClient);
            
            parameters.setCustAccount(codeClientJAXB);
            
            PWCustAgingBalanceGetCustomerBalanceArrayResponse response = getCustomerBalanceArray(parameters);
            
            objBeanBalancing.setCodCliente(response.getResponse().getValue().getString().get(0));
            objBeanBalancing.setSaldo(response.getResponse().getValue().getString().get(1));
            objBeanBalancing.setDeudaVencida(response.getResponse().getValue().getString().get(2));
            objBeanBalancing.setMsgResult("OK");
            objBeanBalancing.setMsgDetails("OK");
           
        } catch (PWCustAgingBalanceGetCustomerBalanceArrayAifFaultFaultFaultMessage ex) {
            objBeanBalancing.setCodCliente("0");
            objBeanBalancing.setSaldo("0");
            objBeanBalancing.setDeudaVencida("0");            
            objBeanBalancing.setMsgResult("KO2 " + ex.getClass().getCanonicalName());
            objBeanBalancing.setMsgDetails(ex.getMessage());
            
        } catch (Exception ex) {
            System.out.println("******** excepción");
            objBeanBalancing.setCodCliente("0");
            objBeanBalancing.setSaldo("0");
            objBeanBalancing.setDeudaVencida("0");            
            objBeanBalancing.setMsgResult("KO3 " + ex.getClass().getCanonicalName());
            objBeanBalancing.setMsgDetails(ex.getMessage());

        }finally{
            if (objBeanBalancing.getMsgDetails() == null)
            {
                objBeanBalancing.setCodCliente("0");
                objBeanBalancing.setSaldo("0");
                objBeanBalancing.setDeudaVencida("0");            
                objBeanBalancing.setMsgResult("KO1 Finally");
                objBeanBalancing.setMsgDetails("No ha saltado la excepcion y se ha ido directamente al finally");
            }
            return objBeanBalancing;
        }
        
        
    }
    @SuppressWarnings("all")
    public static void main(String[] args) {
        WSAXConsultaSaldo wcs = new WSAXConsultaSaldo();
        BeanBalancing objBeanBalancing = wcs.getConsultationBalancing("000189");        
        System.out.println("Codigo de cliente: " + objBeanBalancing.getCodCliente());
        System.out.println("Saldo: " + objBeanBalancing.getSaldo());
        System.out.println("Deuda Vencida: " + objBeanBalancing.deudaVencida);
        System.out.println("ResultMsg: " + objBeanBalancing.getMsgResult());
        System.out.println("ResultMsg: " + objBeanBalancing.getMsgDetails());
    }
    @SuppressWarnings("all")
    private static PWCustAgingBalanceGetCustomerBalanceArrayResponse getCustomerBalanceArray(org.tempuri.PWCustAgingBalanceGetCustomerBalanceArrayRequest parameters) throws PWCustAgingBalanceGetCustomerBalanceArrayAifFaultFaultFaultMessage {
//Añado autentificación: Parece que autentifica primero por este usuario y si no es válido, autentifica por el directorio activo        
        MyAuthenticator myAuth = new MyAuthenticator("wsadmin","Lud1v1na");  
        Authenticator.setDefault(myAuth);  
        
        org.tempuri.RoutingService service = new org.tempuri.RoutingService();
        org.tempuri.PWCustAgingBalance port = service.getBasicHttpBindingPWCustAgingBalance();
        return port.getCustomerBalanceArray(parameters);
    }
    
}
