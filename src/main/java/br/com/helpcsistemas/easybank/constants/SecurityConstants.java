package br.com.helpcsistemas.easybank.constants;

public class SecurityConstants {
    public static final String JWT_KEY = "A6SyAL7wqw8b6bhTEtUAGdF6NsqauK9H";
    public static final String JWT_TOKEN = "JWT Token";
    // 8 hours -> 30000000 ms
    public static final int JWT_EXPIRATION_TIME = 30000000;
    public static final String CLAIM_USERNAME = "username";
    public static final String CLAIM_AUTHORITIES = "authorities";
}
