package br.com.helpcsistemas.easybank.constants;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class Constants {

    public static final String ROLE_USER = "USER";
    public static final String ROLE_ADMIN = "ADMIN";
    public static final String HEADER_AUTHORIZATION = "Authorization";
    public static final String APP_NAME = "Easy Bank";
    public static final BCryptPasswordEncoder.BCryptVersion B_CRYPT_VERSION = BCryptPasswordEncoder.BCryptVersion.$2Y;
    public static final int B_CRIPT_STRENGTH = 12;
    public static final String ALLOWED_ORIGIN_URL = "http://localhost:4200";
    public static final String ENDPOINT_CONTACT = "/contact";
    public static final String ENDPOINT_MY_ACCOUNT = "/myAccount";
    public static final String ENDPOINT_MY_BALANCE = "/myBalance";
    public static final String ENDPOINT_MY_LOANS = "/myLoans";
    public static final String ENDPOINT_MY_CARDS = "/myCards";
    public static final String ENDPOINT_USER = "/user";
    public static final String ENDPOINT_NOTICES = "/notices";
    public static final String ENDPOINT_REGISTER = "/register";
}
