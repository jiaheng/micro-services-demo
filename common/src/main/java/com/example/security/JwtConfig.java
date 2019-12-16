package com.example.security;

import io.jsonwebtoken.security.Keys;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;

import java.security.Key;

public class JwtConfig implements InitializingBean {

    @Value("${security.jwt.uri:/auth}")
    private String uri;

    @Value("${security.jwt.header:Authorization}")
    private String header;

    @Value("${security.jwt.prefix:Bearer }")
    private String prefix;

    @Value("${security.jwt.expiration:#{24*60*60}}")
    private int expiration;

    @Value("${security.jwt.secret:" +
            "M7FV1HX+OtaPhZkieAiZ87yBhEV8b4Lp8BtLHCjr5Y/4RapDZGcY+NniDE0F3hYsKG8eKZ2jkG5dUzqH624Jv/BPUH1fEPqejXzH9zyH7CGvfMJSYFDQ/ta79hhlvxUIfVKctJIEOtcbzleuEOYkImi9w6w2OGH71+PgQg7+O9yk9hO6q0jhHyV6mT2akfJoEKki1qCKzhRIBb/FnQvwQiLmc/eOO8TFyvTJU0jtSKgPcSEJ0+s7qsmzKqEHUedXemYDzrkfM2kmcUEiWeiF1jcFD3nyrmciu+SmN9/e+lIHiQg1N8CLOVISBu6GBRw65P8ZqSH1Fkkrp6I9h0j19Q==" +
            "}")
    private String base64Secret;

    private Key secretKey;


    public JwtConfig() {
    }

    public JwtConfig(final String uri,
                     final String header,
                     final String prefix,
                     final int expiration,
                     final String base64Secret) {
        this.uri = uri;
        this.header = header;
        this.prefix = prefix;
        this.expiration = expiration;
        this.base64Secret = base64Secret;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        this.secretKey = Keys.hmacShaKeyFor(Base64.decodeBase64(base64Secret));
    }

    public String getUri() {
        return uri;
    }

    public String getHeader() {
        return header;
    }

    public String getPrefix() {
        return prefix;
    }

    public int getExpiration() {
        return expiration;
    }

    public Key getSecretKey() {
        return secretKey;
    }

}