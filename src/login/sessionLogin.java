/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package login;

/**
 *
 * @author mozict
 */
public class sessionLogin {
    private static String username;
    private static String id;
    private static String no_tlp;
    private static String almt;
    
    public static void setUserLogin(String username){
        sessionLogin.username=username;
    }
    public static String getUserLogin(){
        return username;
    }
    public static void setIDLogin(String id){
        sessionLogin.id=id;
    }
    public static String getIDLogin(){
        return id;
    }
     public static void setNoTlpLogin(String no_tlp){
        sessionLogin.no_tlp=no_tlp;
    }
    public static String getNoTlpLogin(){
        return no_tlp;
    }
     public static void setAlmtLogin(String almt ){
        sessionLogin.almt=almt;
    }
    public static String getAlmtLogin(){
        return almt;
    }
}
