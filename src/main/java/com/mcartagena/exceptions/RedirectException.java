package com.mcartagena.exceptions;

public class RedirectException extends RuntimeException{
  
  private static final long serialVersionUID = 1L;

  public RedirectException(String location) {
    super(location);
  }

}
