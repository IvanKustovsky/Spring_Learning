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
@SqlResultSetMappings({
        @SqlResultSetMapping(name = "SqlResultSetMapping.count", columns = @ColumnResult(name = "cnt"))
})
@NamedQueries({
        @NamedQuery(name = "Contact.findOpenMessages",
        query = "SELECT c FROM Contact c WHERE c.status = :status"),
        @NamedQuery(name = "Contact.updateMessageStatus",
                query = "UPDATE Contact c SET c.status = ?1 WHERE c.contactId = ?2"),
})
@NamedNativeQueries({  // sorting will not work
        @NamedNativeQuery(name = "Contact.findOpenMessagesNative",
                query = "SELECT * FROM contact_msg c WHERE c.status = :status"
                ,resultClass = Contact.class),
        @NamedNativeQuery(name = "Contact.findOpenMessagesNative.count",
                query = "select count(*) as cnt from contact_msg c where c.status = :status",
                resultSetMapping = "SqlResultSetMapping.count"),
        /*Spring Data JPA doesn't support dynamic sorting for native queries.
        Doing that would require Spring Data to analyze the provided statement and generate
        the ORDER BY clause in the database-specific dialect. This would be a very complex operation
        and is currently not supported by Spring Data JPA.*/
        @NamedNativeQuery(name = "Contact.updateMessageStatusNative",
                query = "UPDATE contact_msg c SET c.status = ?1 WHERE c.contact_id = ?2")
})
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
    @Column(name = "mobile_num")
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
