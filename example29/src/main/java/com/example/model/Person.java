package com.example.model;

import com.example.annotation.FieldsValueMatch;
import com.example.annotation.PasswordValidator;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

@Data
@Entity
@FieldsValueMatch.List({
        @FieldsValueMatch(
                field = "pwd",
                fieldMatch = "confirmPwd",
                message = "Passwords do not match!"
        ),
        @FieldsValueMatch(
                field = "email",
                fieldMatch = "confirmEmail",
                message = "Email addresses do not match!"
        )
})
public class Person extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native")
    private int personId;
    @NotBlank(message = "Name must be not blank")
    @Size(min = 3, message = "Name must be at least 3 symbols long")
    private String name;

    @NotBlank(message = "Mobile number must be not blank")
    @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits")
    private String mobileNumber;

    @NotBlank(message = "Email must be not blank")
    @Email(message = "Please provide a valid email address")
    private String email;

    @NotBlank(message = "Confirm Email must be not blank")
    @Email(message = "Please provide a confirm valid email address")
    @Transient  // Tells to Spring Data JPA to ignore this field for all type of Spring Data JPA related operations.
    // Like INSERT, UPDATE, DELETE, SELECT
    private String confirmEmail;

    @NotBlank(message = "Password must be not blank")
    @Size(min = 5, message = "Password must be at least 5 symbols long")
    @PasswordValidator // Custom password validation annotation
    private String pwd;

    @NotBlank(message = "Confirm Password must be not blank")
    @Size(min = 5, message = "Confirm Password must be at least 5 symbols long")
    @Transient  // Tells to Spring Data JPA to ignore this field for all type of Spring Data JPA related operations.
    // Like INSERT, UPDATE, DELETE, SELECT
    private String confirmPwd;
}