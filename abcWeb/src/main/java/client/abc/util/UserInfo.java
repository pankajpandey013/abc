
package client.abc.util;

public class UserInfo {

    protected boolean authenticated;
   
    protected String userId;
    protected String userName;

    /**
     * Gets the value of the authenticated property.
     * 
     */
    public boolean isAuthenticated() {
        return authenticated;
    }

    /**
     * Sets the value of the authenticated property.
     * 
     */
    public void setAuthenticated(boolean value) {
        this.authenticated = value;
    }

  
   
   

  
    public String getUserId() {
        return userId;
    }

   
    public void setUserId(String value) {
        this.userId = value;
    }

   
    public String getUserName() {
        return userName;
    }

   
    public void setUserName(String value) {
        this.userName = value;
    }

}
