package com.example.rest;


import com.example.constants.ContactConstants;
import com.example.model.Contact;
import com.example.model.Response;
import com.example.repository.ContactRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Slf4j
@RestController // @RestController = @Controller + @ResponseBody. It's a standard.
@RequiredArgsConstructor
// @CrossOrigin annotation allows the cross communication between the multiple servers by disabling the CORS protection.
@CrossOrigin(origins = "*") // * - accepts the traffic from all kind of origins regardless of their host name and port number.
// Is NOT recommended to use * in Production
@RequestMapping(path = "/api/contact", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
public class ContactRestController {

    private final ContactRepository contactRepository;

    /*@ResponseBody annotation is an indication to Spring MVC framework:
     * Please do not expect any view information as a return statement from this method.
     * Instead, the DispatcherServlet will get a data.
     * Please pass on the same data to the Tomcat servlet container and
     * Tomcat Servlet container will take care of responding to the application
     * whoever is invoking my RestServices with the same data that I'm returning inside this method*/
    @GetMapping("/getMessagesByStatus")
    //@ResponseBody
    public List<Contact> getMessagesByStatus(@RequestParam("status") String status) {
        return contactRepository.findByStatus(status);
    }

    @GetMapping("/getAllMessagesByStatus")
    public List<Contact> getAllMessagesByStatus(@RequestBody Contact contact) {
        if (contact != null && contact.getStatus() != null) {
            return contactRepository.findByStatus(contact.getStatus());
        } else {
            return List.of();
        }
    }

    @PostMapping("/saveMsg")
    public ResponseEntity<Response> saveMsg(@RequestHeader("invocationFrom") String invocationFrom,
                                            @Valid @RequestBody Contact contact) {
        log.info(String.format("Header invocationFrom = %s", invocationFrom));
        contactRepository.save(contact);
        Response response = new Response();
        response.setStatusCode("200");
        response.setStatusMsg("Message saved successfully");
        return ResponseEntity.
                status(HttpStatus.CREATED)
                .header("IsMsgSaved", "true")
                .body(response);
    }

    @DeleteMapping("/deleteMsg")
    public ResponseEntity<Response> deleteMsg(RequestEntity<Contact> requestEntity) {
        HttpHeaders httpHeaders = requestEntity.getHeaders();
        httpHeaders.forEach((key, value) -> log.info(String.format("Header '%s' = '%s'", key,
                String.join("|", value))));
        Contact contact = requestEntity.getBody();
        contactRepository.deleteById(Objects.requireNonNull(contact).getContactId());
        Response response = new Response();
        response.setStatusCode("200");
        response.setStatusMsg("Message successfully deleted");
        return ResponseEntity.
                status(HttpStatus.OK)
                .body(response);
    }

    @PatchMapping ("/closeMsg") // for updating data
    public ResponseEntity<Response> closeMsg(@RequestBody Contact contactReq) {
        Response response = new Response();
        Optional<Contact> contact = contactRepository.findById(contactReq.getContactId());
        if(contact.isPresent()){
            contact.get().setStatus(ContactConstants.CLOSE);
            contactRepository.save(contact.get());
        } else {
            response.setStatusCode("400");
            response.setStatusMsg("Invalid Contact ID received");
            return ResponseEntity.
                    status(HttpStatus.BAD_REQUEST)
                    .body(response);
        }
        response.setStatusCode("200");
        response.setStatusMsg("Message successfully closed");
        return ResponseEntity.
                status(HttpStatus.BAD_REQUEST)
                .body(response);
    }

}
