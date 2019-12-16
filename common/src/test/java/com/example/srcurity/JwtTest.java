package com.example.srcurity;

import com.example.security.JwtConfig;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.apache.commons.codec.binary.Base64;
import org.junit.Before;
import org.junit.Test;

import java.sql.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.util.Lists.newArrayList;

public class JwtTest {

    private JwtConfig jwtConfig;

    @Before
    public void setup() throws Exception {
        final String secret = "M7FV1HX+OtaPhZkieAiZ87yBhEV8b4Lp8BtLHCjr5Y/4RapDZGcY+NniDE0F3hYsKG8eKZ2jkG5dUzqH624Jv/BPUH1fEPqejXzH9zyH7CGvfMJSYFDQ/ta79hhlvxUIfVKctJIEOtcbzleuEOYkImi9w6w2OGH71+PgQg7+O9yk9hO6q0jhHyV6mT2akfJoEKki1qCKzhRIBb/FnQvwQiLmc/eOO8TFyvTJU0jtSKgPcSEJ0+s7qsmzKqEHUedXemYDzrkfM2kmcUEiWeiF1jcFD3nyrmciu+SmN9/e+lIHiQg1N8CLOVISBu6GBRw65P8ZqSH1Fkkrp6I9h0j19Q==";
        this.jwtConfig = new JwtConfig(
                "/auth/**",
                "Authorization",
                "Bearer ",
                24 * 60 * 60,
                secret
        );
        this.jwtConfig.afterPropertiesSet();
    }

    @Test
    public void test() {
        final String username = "admin";
        final List<String> authorities = newArrayList("ROLE_ADMIN");
        final long now = (System.currentTimeMillis() / 1000) * 1000;

        final String token = Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date(now))
                .setExpiration(new Date(now + jwtConfig.getExpiration() * 1000))  // in milliseconds
                .claim("authorities", authorities)
                .signWith(jwtConfig.getSecretKey())
                .compact();

        final Claims claims = Jwts.parser()
                .setSigningKey(jwtConfig.getSecretKey())
                .parseClaimsJws(token)
                .getBody();

        assertThat(claims.getSubject()).isEqualTo(username);
        assertThat(claims.getIssuedAt()).isEqualTo(new Date(now));
        assertThat(claims.getExpiration()).isEqualTo(new Date(now + jwtConfig.getExpiration() * 1000));
    }

    @Test
    public void test2() {
        final Random random = ThreadLocalRandom.current();
        final byte[] r = new byte[256]; //Means 2048 bit
        random.nextBytes(r);
        final String s = Base64.encodeBase64String(r);
        System.out.println(s);
        final byte[] result = Base64.decodeBase64(s);
        assertThat(result).isEqualTo(r);
    }

}
