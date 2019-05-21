package at.willhaben.workshop.controller;

import com.auth0.jwk.Jwk;
import com.auth0.jwk.JwkException;
import com.auth0.jwk.JwkProvider;
import com.auth0.jwk.JwkProviderBuilder;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.Verification;

import java.net.MalformedURLException;
import java.net.URL;
import java.security.interfaces.RSAPublicKey;

public class JWTVerify {

    public static void main(String args[]) throws JwkException, MalformedURLException {
        DecodedJWT decode1 = JWT.decode("eyJraWQiOiJXblZwXC8yK0JKMWNJRHhZaHlXc0Q2T3JNUzFNS2hwVFNHZGRcL0tuMXMyVGs9IiwiYWxnIjoiUlMyNTYifQ.eyJhdF9oYXNoIjoiU1ktYmQxVGRPNDVxSnJoYk5kUzBLdyIsInN1YiI6Ijk2ZDRjODI3LTk0NzAtNGNlNi1hZjcyLWI4MzRkZWEwMDM1YyIsImF1ZCI6IjNjYW41M3RoM3RsbXNuaGNuMWJ1amkzMGk4IiwiZXZlbnRfaWQiOiI5ZTE0YWQ0MS00YjUxLTExZTktYjI0Yi0xMzQ1YjVkYzQwM2IiLCJ0b2tlbl91c2UiOiJpZCIsImF1dGhfdGltZSI6MTU1MzExNDk1MywiaXNzIjoiaHR0cHM6XC9cL2NvZ25pdG8taWRwLmV1LWNlbnRyYWwtMS5hbWF6b25hd3MuY29tXC9ldS1jZW50cmFsLTFfZjVlWHNTSmZzIiwiY29nbml0bzp1c2VybmFtZSI6ImJhem9va2EiLCJleHAiOjE1NTMxMTg1NTMsImlhdCI6MTU1MzExNDk1M30.I5yU2Grl_UvklNdeU2sRGqleFEGaVwulEh-rqHN1wVoNgNUMp6Lbt4_kOdeSVeRXNQTEEp69sDKHjrhOUYlDIQJoC1_g-_aga6EJT214RgAUj7uOaPkl4cStFgJ8NHjNknyvKo3v2sQOUo9AIiR0GBnFzsWxNu4C2Q8Hftnc030GcImopJPyUH6SQq-JmAJY5LGxToI5NL3rQKLuj1Tvuk6Fguz829GvVDKDKzv1PStomgUU2oO1A_B5A_olM82QcOK2cUZrYjUAmtGrnjMgXSUEpOmBPpH8sYSU7HnKPHKYoX_0qGfOFx6ZA2_fe2EG4dSnyASZbUuOSebzKY-3YQ");
        DecodedJWT decode2 = JWT.decode("eyJraWQiOiJXblZwXC8yK0JKMWNJRHhZaHlXc0Q2T3JNUzFNS2hwVFNHZGRcL0tuMXMyVGs9IiwiYWxnIjoiUlMyNTYifQ.eyJhdF9oYXNoIjoiU1ktYmQxVGRPNDVxSnJoYk5kUzBLdyIsInN1YiI6Ijk2ZDRjODI3LTk0NzAtNGNlNi1hZjcyLWI4MzRkZWEwMDM1YyIsImF1ZCI6IjNjYW41M3RoM3RsbXNuaGNuMWJ1amkzMGk4IiwiZXZlbnRfaWQiOiI5ZTE0YWQ0MS00YjUxLTExZTktYjI0Yi0xMzQ1YjVkYzQwM2IiLCJ0b2tlbl91c2UiOiJpZCIsImF1dGhfdGltZSI6MTU1MzExNDk1MywiaXNzIjoiaHR0cHM6XC9cL2NvZ25pdG8taWRwLmV1LWNlbnRyYWwtMS5hbWF6b25hd3MuY29tXC9ldS1jZW50cmFsLTFfZjVlWHNTSmZzIiwiY29nbml0bzp1c2VybmFtZSI6ImJhemluZ2EiLCJleHAiOjE1NTMxMTg1NTMsImlhdCI6MTU1MzExNDk1M30.I5yU2Grl_UvklNdeU2sRGqleFEGaVwulEh-rqHN1wVoNgNUMp6Lbt4_kOdeSVeRXNQTEEp69sDKHjrhOUYlDIQJoC1_g-_aga6EJT214RgAUj7uOaPkl4cStFgJ8NHjNknyvKo3v2sQOUo9AIiR0GBnFzsWxNu4C2Q8Hftnc030GcImopJPyUH6SQq-JmAJY5LGxToI5NL3rQKLuj1Tvuk6Fguz829GvVDKDKzv1PStomgUU2oO1A_B5A_olM82QcOK2cUZrYjUAmtGrnjMgXSUEpOmBPpH8sYSU7HnKPHKYoX_0qGfOFx6ZA2_fe2EG4dSnyASZbUuOSebzKY-3YQ");
        JwkProvider p = new JwkProviderBuilder(new URL("https://cognito-idp.eu-central-1.amazonaws.com/eu-central-1_f5eXsSJfs/.well-known/jwks.json")).build();
        verify(decode1, p);
        verify(decode2, p);

    }

    private static void verify(DecodedJWT token, JwkProvider p) {
        try {
            Jwk jwk = p.get(token.getKeyId());
            Algorithm algorithm = Algorithm.RSA256((RSAPublicKey) jwk.getPublicKey(), null);
            algorithm.verify(token);
            System.out.println("valid "+ token.getClaim("cognito:username").asString());
        } catch (Exception e) {
            System.out.println("invalid "+ token.getClaim("cognito:username").asString());
            e.printStackTrace();
        }
    }
}
