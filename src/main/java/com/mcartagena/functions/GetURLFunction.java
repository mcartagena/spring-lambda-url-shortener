package com.mcartagena.functions;

import java.util.function.Function;

import com.mcartagena.daos.UrlDAO;
import com.mcartagena.exceptions.RedirectException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("getURL")
public class GetURLFunction implements Function<String, String> {
 
  @Autowired
  private UrlDAO urlDAO;
  
  @Override
  public String apply(String shortCode) {
    
    String url = urlDAO.getUrl(shortCode);

    throw new RedirectException(url);

  }
  
}
