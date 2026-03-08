
package com.example.githubreport.service;

import com.example.githubreport.client.GithubClient;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class GithubAccessService {

 private final GithubClient githubClient;
 private final ObjectMapper mapper = new ObjectMapper();

 public GithubAccessService(GithubClient githubClient) {
  this.githubClient = githubClient;
 }

 public Map<String, List<String>> generateAccessReport(String org) throws Exception {

  Map<String, List<String>> userRepoMap = new HashMap<>();

  String reposJson = githubClient.getRepos(org);
  JsonNode repos = mapper.readTree(reposJson);

  for (JsonNode repo : repos) {

   String repoName = repo.get("name").asText();

   String collabJson = githubClient.getCollaborators(org, repoName);
   JsonNode collaborators = mapper.readTree(collabJson);

   for (JsonNode user : collaborators) {

    String username = user.get("login").asText();

    userRepoMap
      .computeIfAbsent(username, k -> new ArrayList<>())
      .add(repoName);
   }
  }

  return userRepoMap;
 }
}
