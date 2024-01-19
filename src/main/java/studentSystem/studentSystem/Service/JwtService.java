package studentSystem.studentSystem.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import studentSystem.studentSystem.model.Student;

import java.util.Date;

@Service
public class JwtService {

    @Value("${jwt.algorithm.key}")
    private String algorithmKey;

    @Value("${jwt.issuer}")
    private String issuer;

    @Value("${jwt.expiry.in.seconds}")
    private int expiryInSeconds;

    Algorithm algorithm;

    private static final String USERNAME = "USERNAME";
    private static final String EMAIL_KEY = "email";

    @PostConstruct
    public void postconstruct(){
        algorithm = Algorithm.HMAC256(algorithmKey);
    }

    public String CreateJWT(Student student) {
        return JWT.create()
                .withClaim(USERNAME, student.getUsername())
                .withIssuer(issuer)
                .withExpiresAt(new Date(System.currentTimeMillis() + 1000 * expiryInSeconds))
                .sign(algorithm);
    }

    public String getUserName(String token) {
        return JWT.decode(token).getClaim(USERNAME).asString();
    }

//    public String getUserName(String token) throws JWTVerificationException {
//
//        DecodedJWT jwt = JWT.require(algorithm).withIssuer(issuer).build().verify(token);
//        return jwt.getClaim(USERNAME).asString();
//    }

}
