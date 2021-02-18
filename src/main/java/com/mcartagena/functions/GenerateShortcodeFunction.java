package com.mcartagena.functions;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.function.Function;

import com.mcartagena.daos.UrlDAO;
import com.mcartagena.exceptions.InvalidURLFormatException;
import com.mcartagena.functiontypes.EmailDetails;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("generateShortcode")
public class GenerateShortcodeFunction implements Function<String, String> {

  @Autowired
  private LambdaFunctions lambdaFunctions;

  @Autowired
  private UrlDAO urlDAO;

  @Override
  public String apply(String url) {
    try {
      new URL(url);
    } catch (MalformedURLException e) {
      e.printStackTrace();
      throw new InvalidURLFormatException();
    }

    String shortCode = urlDAO.generateShortcode();
    urlDAO.storeURL(shortCode, url);

    EmailDetails emailDetails = new EmailDetails("marcelocartagenaorellana@gmail.com",
                                                "marcelocartagenaorellana@gmail.com",
                                                "Congratulations, you have created a shortURL for " + url,
                                                shortCode,
                                                url);

    lambdaFunctions.sendShortcodeGeneratedEmail(emailDetails);

    return shortCode;
	}

}
