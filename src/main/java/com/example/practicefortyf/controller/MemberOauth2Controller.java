package com.example.practicefortyf.controller;

import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/login/oauth2/code")
public class MemberOauth2Controller {

    @GetMapping("/kakao")
    public String kakaoOauthRedirect(@RequestParam String code) {
        RestTemplate rt = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        LinkedMultiValueMap<Object, Object> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "authorization_code");
        params.add("client_id", "0498495b3bd867a1d1e6c70ada63dd39");
        params.add("redirect_uri", "http://localhost:8080/login/oauth2/code/kakao");
        params.add("code", code);

        HttpEntity<LinkedMultiValueMap<Object, Object>> tokenRequest = new HttpEntity<>(params, headers);

        ResponseEntity<String> tokenResponse = rt.exchange(
                "https://kauth.kakao.com/oauth/token",
                HttpMethod.POST,
                tokenRequest,
                String.class
        );

        JSONObject tokenJsonObject = new JSONObject(tokenResponse.getBody());
        String accessToken = tokenJsonObject.getString("access_token");
        String refreshToken = tokenJsonObject.getString("refresh_token");

        headers.add("Authorization", "Bearer " + accessToken);
        HttpEntity<Object> userInfoRequest = new HttpEntity<>(headers);
        ResponseEntity<String> userInfoResponse = rt.exchange(
                "https://kapi.kakao.com//v2/user/me",
                HttpMethod.POST,
                userInfoRequest,
                String.class
        );

        JSONObject userInfoJsonObject = new JSONObject(userInfoResponse.getBody());
        JSONObject profile = (JSONObject) ((JSONObject) userInfoJsonObject.get("kakao_account")).get("profile");
        String nickname = profile.getString("nickname");

        return nickname;
    }

    @GetMapping("/naver")
    public Object naverOauthRedirect(@RequestParam String code, @RequestParam String state) {
        RestTemplate rt = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        LinkedMultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "authorization_code");
        params.add("client_id", "3QbJSuUX3a7bIi9RDukT");
        params.add("client_secret", "kIJadJ3_IQ");
        params.add("code", code);
        params.add("state", state);

        HttpEntity<LinkedMultiValueMap<String, String>> tokenRequest = new HttpEntity<>(params, headers);

        ResponseEntity<String> tokenResponse = rt.exchange(
                "https://nid.naver.com/oauth2.0/token",
                HttpMethod.POST,
                tokenRequest,
                String.class
        );

        JSONObject tokenJsonObject = new JSONObject(tokenResponse.getBody());
        String accessToken = tokenJsonObject.getString("access_token");
        String refreshToken = tokenJsonObject.getString("refresh_token");
        String tokenType = tokenJsonObject.getString("token_type");

        headers.add("Authorization", tokenType + " " + accessToken);
        HttpEntity<Object> userInfoRequest = new HttpEntity<>(headers);

        ResponseEntity<String> userInfoResponse = rt.exchange(
                "https://openapi.naver.com/v1/nid/me",
                HttpMethod.GET,
                userInfoRequest,
                String.class
        );

        JSONObject rawUserInfoJsonObject = new JSONObject(userInfoResponse.getBody());
        JSONObject userInfoJsonObject = (JSONObject) rawUserInfoJsonObject.get("response");
        String birthday = userInfoJsonObject.getString("birthday");
        String name = userInfoJsonObject.getString("name");
        String email = userInfoJsonObject.getString("email");

        return "birthday: " + birthday + " name: " + name + " email : " + email;
    }

    @GetMapping("/google")
    public Object googleOauthRedirect(@RequestParam String code, @RequestParam String scope) {
        RestTemplate rt = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-type", "application/x-www-form-urlencoded");

        LinkedMultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "authorization_code");
        params.add("client_id", "973469585834-r6qefacm23dk18sokil1jl81hthns586.apps.googleusercontent.com");
        params.add("client_secret", "DkhetvEVybeNapeen0fceZ7X");
        params.add("code", code);
        params.add("redirect_uri", "http://localhost:8080/login/oauth2/code/google");

        HttpEntity<LinkedMultiValueMap<String, String>> tokenRequest = new HttpEntity<>(params, headers);

        ResponseEntity<String> tokenResponse = rt.exchange(
                "https://oauth2.googleapis.com/token",
                HttpMethod.POST,
                tokenRequest,
                String.class
        );

        JSONObject tokenJsonObject = new JSONObject(tokenResponse.getBody());
        String accessToken = tokenJsonObject.getString("access_token");
        //문서에는 refresh_token 준다고 나와있는데 이게 방금전까지는 없었다가 scope를 달리하니깐 생김 ;; 또 없어짐;;
        String idToken = tokenJsonObject.getString("id_token");
//        String refreshToken = tokenJsonObject.getString("refresh_token");
        String tokenType = tokenJsonObject.getString("token_type");

        headers.add("Authorization", tokenType + " " + accessToken);
        HttpEntity<Object> userInfoRequest = new HttpEntity<>(headers);

        ResponseEntity<String> userInfoResponse = rt.exchange(
                "https://www.googleapis.com/oauth2/v2/userinfo",
                HttpMethod.GET,
                userInfoRequest,
                String.class
        );

        JSONObject userInfoJsonObject = new JSONObject(userInfoResponse.getBody());
        /*
        {"name":"강승윤","id":"105397262882358643457","verified_email":true,"given_name":"승윤","locale":"ko","family_name":"강","email":"kangsy763@gmail.com","picture":"https://lh3.googleusercontent.com/a/AATXAJxka1JmPM8P_7mkthOQoBEqBtBEWOoohjj-ZBrp=s96-c"}
         */
        System.out.println(userInfoJsonObject);
        String name = userInfoJsonObject.getString("name");
        String email = userInfoJsonObject.getString("email");

        return "email: " + email + " name: " + name;
    }

}
