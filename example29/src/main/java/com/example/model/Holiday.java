package com.example.model;


import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity // This annotation specifies that this class is an entity, representing a table in the database.
@Table(name = "holidays") // This annotation is used to provide additional information about the mapping between
// the entity class and the corresponding database table, in this case, specifying the table name.
public class Holiday extends BaseEntity {

    @Id // This annotation claims that this field is a primary key in the corresponding table
    private String day;
    private String reason;
    @Enumerated(EnumType.STRING) // The following annotation is used to specify that the 'type' field represents an Enum,
    private Type type; // and the Enum values should be stored as strings in the database.

    public enum Type{
        FESTIVAL, FEDERAL
    }


}
