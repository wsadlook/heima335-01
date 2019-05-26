package test;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.Test;

import java.util.Date;

public class Demo {

    @Test
    public void test01(){
        JwtBuilder jwtBuilder = Jwts.builder()
                .setId("1234")
                .claim("username", "zhangsi")
                .claim("roles", "admin")
                .setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS256, "itcast");
        String compact = jwtBuilder.compact();
        System.out.println(compact);

    }
    @Test
    public void tess02(){
        String jwt = "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxMjM0NTYiLCJpYXQiOjE1NTUzMjk5ODgsInVzZXJuYW1lIjoiemhhbmdzYW4iLCJyb2xlcyI6ImFkbWluIn0.FE7BGE29QwkIlWzJeLAUd4Le2BeQ5h7RbD60LtLXAas";

        Claims claim = Jwts.parser().setSigningKey("itcast").parseClaimsJws(jwt).getBody();
        System.out.println(claim.getIssuedAt());
        System.out.println(claim.getId());
        System.out.println(claim.get("username"));
        System.out.println(claim.get("roles"));
    }
}
