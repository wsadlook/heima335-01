package test;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.Test;

import java.util.Date;

public class JjwtTest {

    @Test
    public void test1(){
        long time = System.currentTimeMillis();
        Date date = new Date(time + 60000);
        JwtBuilder jwtBuilder = Jwts.builder()
                .setId("123456")
                .setIssuedAt(new Date())
                .claim("username","zhangsan")
                .claim("roles","admin")
                .setExpiration(date)
                .signWith(SignatureAlgorithm.HS256, "itcast");
        String jwt = jwtBuilder.compact();
        System.out.println(jwt);

    }

    @Test
    public void test2(){
//        String jwt = "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxMjM0NTYiLCJpYXQiOjE1NTUzMjk2NTh9.qRjlm5LsFCgG-4C_3YEiwzMqYkYDiBxduc0w14IMKQg";
//        String jwt = "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxMjM0NTYiLCJpYXQiOjE1NTUzMjk5ODgsInVzZXJuYW1lIjoiemhhbmdzYW4iLCJyb2xlcyI6ImFkbWluIn0.FE7BGE29QwkIlWzJeLAUd4Le2BeQ5h7RbD60LtLXAas";
        String jwt ="eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxMjM0NTYiLCJpYXQiOjE1NTUzMzA2OTksInVzZXJuYW1lIjoiemhhbmdzYW4iLCJyb2xlcyI6ImFkbWluIiwiZXhwIjoxNTU1MzMwNzU4fQ.AXuoNo5RMEDav4NL89LuxrO7dS5bu8gclpR-IagYCjo";
        Claims claim = Jwts.parser().setSigningKey("itcast").parseClaimsJws(jwt).getBody();

        System.out.println(claim.getId());
        System.out.println(claim.getIssuedAt());

        System.out.println(claim.get("username"));
        System.out.println(claim.get("roles"));
    }
}
