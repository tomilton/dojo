package co.com.nequi.model.requestmdw;

public class SecurityCredential {
    private String userName;
    private String userToken;

    public String getUserName() { return userName; }
    public void setUserName(String value) { this.userName = value; }

    public String getUserToken() { return userToken; }
    public void setUserToken(String value) { this.userToken = value; }
}
