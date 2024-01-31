package com.example.model;


import lombok.Data;

@Data
public class Holiday extends BaseEntity {

    /*Removed final key word due to rowMapper, because we need to create
    * empty constructor of this pojo class, thus fields can't be final
    * */
    private String day;
    private String reason;
    private Type type;

    public enum Type{
        FESTIVAL, FEDERAL
    }


}
