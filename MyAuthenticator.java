/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.net.Authenticator;
import java.net.PasswordAuthentication;

public class MyAuthenticator extends Authenticator{
   
    private String user;
    private String password;

    public MyAuthenticator(String user, String password) {
        this.user = user;
        this.password = password;
    }

    
    
    @Override
    protected PasswordAuthentication getPasswordAuthentication() {
        PasswordAuthentication auth = new PasswordAuthentication(user, password.toCharArray());
        
        return auth;
    }
    
    
    
}
