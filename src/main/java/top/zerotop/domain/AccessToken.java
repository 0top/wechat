package top.zerotop.domain;

public class AccessToken {
	
	/**
	 * accessToken
	 */
	public static String accessToken;
	
	/**
	 * token有效期
	 */
    private int expiresin;
	
    public String getAccessToken() {
        return accessToken;
    }
 
    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
 
    public int getExpiresin() {
        return expiresin;
    }
 
    public void setExpiresin(int expiresin) {
        this.expiresin = expiresin;
    }

}