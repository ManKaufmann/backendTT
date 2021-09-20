package com.example.backendtt.componenten.login;

import com.example.backendtt.security.models.UserData;
import io.fusionauth.jwt.Signer;
import io.fusionauth.jwt.Verifier;
import io.fusionauth.jwt.domain.JWT;
import io.fusionauth.jwt.hmac.HMACSigner;
import io.fusionauth.jwt.hmac.HMACVerifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Base64;

@Component
@CrossOrigin
public class Login {
    @Value("${jwt.secret}")
    private String secret;

    //while creating the token -
    //1. Define  claims of the token, like Issuer, Expiration, Subject, and the ID
    //2. Sign the JWT using the HS512 algorithm and secret key.
    //3. According to JWS Compact Serialization(https://tools.ietf.org/html/draft-ietf-jose-json-web-signature-41#section-3.1)
    //   compaction of the JWT to a URL-safe string
    public String signeJWTToken(UserData userData) {


        // Build an HMAC signer using a SHA-256 hash
        Signer signer = HMACSigner.newSHA256Signer(this.secret);

        // Build a new JWT with an issuer(iss), issued at(iat), subject(sub) and expiration(exp)
        JWT jwt = new JWT()
                .setIssuer("localhost:8085")
                .setIssuedAt(ZonedDateTime.now(ZoneOffset.UTC))
                .setSubject(userData.getUsername())
                .setExpiration(ZonedDateTime.now(ZoneOffset.UTC).plusMinutes(60));
        jwt.addClaim("pms", "ADMIN");

        // Sign and encode the JWT to a JSON string representation
        String encodedJWT = JWT.getEncoder().encode(jwt, signer);

        return encodedJWT;
    }

    public String[] decodeBasicAuth(String authorization) {
        String base64 = authorization.substring(6);
        byte[] decodedBytes = Base64.getUrlDecoder().decode(base64);
        String decodedBasicUser = new String(decodedBytes);
        String[] split = decodedBasicUser.split(":");
        return split;
    }

    public JWT decodeJwtToken(String jwtToken) {

        String encodedJWT = jwtToken.substring(7);

        // Build an HMC verifier using the same secret that was used to sign the JWT
        Verifier verifier = HMACVerifier.newVerifier(this.secret);

        // Verify and decode the encoded string JWT to a rich object
        JWT jwt = JWT.getDecoder().decode(encodedJWT, verifier);
        return jwt;


    }

    public boolean validateToken(String jwtToken, UserDetails userDetails) {
        JWT decodeJwtTokenjwt = this.decodeJwtToken(
                jwtToken
        );
        final String username = decodeJwtTokenjwt.subject;
        Boolean tokenExpired = isTokenExpired(decodeJwtTokenjwt);
        return (username.equals(userDetails.getUsername()) && !tokenExpired);


    }
    //check if the token has expired
    private Boolean isTokenExpired(JWT decodeJwtTokenjwt) {
        ZonedDateTime expirationDateFromToken = decodeJwtTokenjwt.expiration;
        boolean after = ZonedDateTime.now(ZoneOffset.UTC).isAfter( expirationDateFromToken );
        return after;
    }

}
