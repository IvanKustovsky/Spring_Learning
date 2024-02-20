package com.example.controller;

import com.example.model.Contact;
import com.example.model.Response;
import com.example.proxy.ContactProxy;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ContactController {

    @Autowired
    private ContactProxy contactProxy;

    @Autowired
    private RestTemplate restTemplate; // Is not recommended for using

    @GetMapping("/getMessages")
    public List<Contact> getMessages(@RequestParam("status") String status) {
        return contactProxy.findMessagesByStatus(status);
    }

    @PostMapping("/saveMsg")
    public ResponseEntity<Response> saveMsg(@RequestBody Contact contact){
        String uri = "http://localhost:8080/api/contact/saveMsg"; // In Prod should be stored inside properties or constant file
        HttpHeaders headers = new HttpHeaders();
        headers.add("invocationFrom","RestTemplate");
        HttpEntity<Contact> httpEntity = new HttpEntity<>(contact, headers);
        ResponseEntity<Response> responseEntity = restTemplate.exchange(uri, HttpMethod.POST,
                httpEntity,Response.class);
        return responseEntity;
    }


}
