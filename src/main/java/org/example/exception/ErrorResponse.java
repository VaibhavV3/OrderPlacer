package org.example.exception;


public class ErrorResponse {
    private int status;
    private String message;

    public ErrorResponse(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public void setStatus(int status){
    	this.status = status;
    }

    public int getStatus() {
    	return this.status;
    }

    public void setMessage(String message){
    	this.message = message;
    }

    public String getMessage(){
    	return this.message;
    }
    
}
