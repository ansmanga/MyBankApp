package com.sapient.app.model;



public class JWTResponse {
	
	public String getJwtToken() {
		return jwtToken;
	}
	public void setJwtToken(String jwtToken) {
		this.jwtToken = jwtToken;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public JWTResponse(String jwtToken, String username) {
		super();
		this.jwtToken = jwtToken;
		this.username = username;
	}
	private String jwtToken;
	private String username;
	
	public static class Builder {
        private String jwtToken;
        private String username;

        public Builder jwtToken(String jwtToken) {
            this.jwtToken = jwtToken;
            return this;
        }

        public Builder username(String username) {
            this.username = username;
            return this;
        }

        public JWTResponse build() {
            return new JWTResponse(jwtToken, username);
        }
    }

    // Static method to obtain a builder instance
    public static Builder builder() {
        return new Builder();
    }
	
	
}
