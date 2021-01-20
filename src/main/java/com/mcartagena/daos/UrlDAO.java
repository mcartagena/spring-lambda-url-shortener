package com.mcartagena.daos;

public interface UrlDAO {

	String generateShortcode();

	void storeURL(String shortCode, String url);

}
