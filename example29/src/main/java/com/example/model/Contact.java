package com.example.model;


import jakarta.validation.constraints.*;
import lombok.*;

/*
@Data annotation is provided by Lombok library which generates getter, setter,
equals(), hashCode(), toString() methods & Constructor at compile time.
This makes our code short and clean.
* */
@Data
public class Contact extends BaseEntity {
    /*
    * @NotNull: Checks if a given field is not null but allows empty values & zero elements inside collections.
      @NotEmpty: Checks if a given field is not null and its size/length is greater than zero.
      @NotBlank: Checks if a given field is not null and trimmed length is greater than zero.
    * */

    private int contactId;
    @NotBlank(message = "Name must be not blank")
    @Size(min = 3, message = "Name must be at least 3 symbols long")
    private String name;

    @NotBlank(message = "Mobile number must be not blank")
    @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits")
    private String mobileNum;

    @NotBlank(message = "Email must be not blank")
    @Email(message = "Provide a valid email address")
    private String email;

    @NotBlank(message = "Subject must be not blank")
    @Size (min = 4, message = "Subject must be at least 4 symbols long")
    private String subject;

    @NotBlank(message = "Message must be not blank")
    @Size (min = 10, message = "Subject must be at least 10 symbols long")
    private String message;

    private String status;


}
