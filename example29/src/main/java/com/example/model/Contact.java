package com.example.model;


import jakarta.validation.constraints.*;
import lombok.*;
import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

/*
@Data annotation is provided by Lombok library which generates getter, setter,
equals(), hashCode(), toString() methods & Constructor at compile time.
This makes our code short and clean.
* */
@Data
@Entity
@Table(name = "contact_msg")
public class Contact extends BaseEntity {

    @Id // This annotation claims that this field is a primary key in the corresponding table
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    // The @GeneratedValue annotation specifies the strategy for generating unique identifiers.
    // In this case, it uses the 'native' strategy, which means the database system's default method.
    @GenericGenerator(name = "native")
    // The @GenericGenerator annotation allows the configuration of custom identifier generators.
    // In this case, it defines a generator named 'native'.
    @Column(name = "contact_id")
    // The @Column annotation allows the customization of column details, such as name and constraints.
    // Here, it specifies that the column name in the database should be 'contact_id'.
    private int contactId;

    /*
   * @NotNull: Checks if a given field is not null but allows empty values & zero elements inside collections.
     @NotEmpty: Checks if a given field is not null and its size/length is greater than zero.
     @NotBlank: Checks if a given field is not null and trimmed length is greater than zero.
   * */
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
    @Size(min = 4, message = "Subject must be at least 4 symbols long")
    private String subject;

    @NotBlank(message = "Message must be not blank")
    @Size(min = 10, message = "Subject must be at least 10 symbols long")
    private String message;

    private String status;


}
