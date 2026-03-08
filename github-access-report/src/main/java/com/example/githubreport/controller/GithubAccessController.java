
package com.example.githubreport.controller;

import com.example.githubreport.service.GithubAccessService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/github")
public class GithubAccessController {

 private final GithubAccessService service;

 public GithubAccessController(GithubAccessService service) {
  this.service = service;
 }

 @GetMapping("/access-report")
 public Map<String, List<String>> getAccessReport(@RequestParam String org) throws Exception {
  return service.generateAccessReport(org);
 }
}
