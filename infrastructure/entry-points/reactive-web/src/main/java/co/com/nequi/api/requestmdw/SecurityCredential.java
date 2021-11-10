package co.com.nequi.api.requestmdw;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.ToString;

@ToString
public class SecurityCredential {
    private String userName;
    private String userToken;

    @JsonProperty("userName")
    public String getUserName() { return userName; }
    @JsonProperty("userName")
    public void setUserName(String value) { this.userName = value; }

    @JsonProperty("userToken")
    public String getUserToken() { return userToken; }
    @JsonProperty("userToken")
    public void setUserToken(String value) { this.userToken = value; }
}
