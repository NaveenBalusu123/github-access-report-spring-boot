
package com.example.githubreport.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class GithubClient {

 @Value("${github.token}")
 private String token;

 @Value("${github.api.url}")
 private String baseUrl;

 private final RestTemplate restTemplate = new RestTemplate();

 public String getRepos(String org) {

  String url = baseUrl + "/orgs/" + org + "/repos";

  HttpHeaders headers = new HttpHeaders();
  headers.set("Authorization", "Bearer " + token);
  headers.set("Accept", "application/vnd.github+json");

  HttpEntity<String> entity = new HttpEntity<>(headers);

  ResponseEntity<String> response =
    restTemplate.exchange(url, HttpMethod.GET, entity, String.class);

  return response.getBody();
 }

 public String getCollaborators(String org, String repo) {

  String url = baseUrl + "/repos/" + org + "/" + repo + "/collaborators";

  HttpHeaders headers = new HttpHeaders();
  headers.set("Authorization", "Bearer " + token);

  HttpEntity<String> entity = new HttpEntity<>(headers);

  ResponseEntity<String> response =
    restTemplate.exchange(url, HttpMethod.GET, entity, String.class);

  return response.getBody();
 }
}
