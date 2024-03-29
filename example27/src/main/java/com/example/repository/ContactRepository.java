package com.example.repository;

import com.example.model.Contact;
import com.example.rowMappers.ContactRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

/*
@Repository stereotype annotation is used to add a bean of this class
type to the Spring context and indicate that given Bean is used to perform
DB related operations
* */
@Repository
public class ContactRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ContactRepository(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    public int saveContactMsg(Contact contact) {
        String sql = "INSERT INTO CONTACT_MSG (NAME,MOBILE_NUM,EMAIL,SUBJECT,MESSAGE,STATUS," +
                "CREATED_AT,CREATED_BY) VALUES (?,?,?,?,?,?,?,?)";
        return jdbcTemplate.update(sql,contact.getName(),contact.getMobileNum(),contact.getEmail(),
                contact.getSubject(),contact.getMessage(),contact.getStatus(),contact.getCreatedAt(),
                contact.getCreatedBy());
    }

    public List<Contact> findMessagesWithStatus(String status){
        String sql = "SELECT * FROM CONTACT_MSG WHERE STATUS=?";
        return jdbcTemplate.query(sql, new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps) throws SQLException {
                ps.setString(1,status);
            }
        }, new ContactRowMapper());

    }

    public int updateMessageStatus(int contactId, String newStatus, String updatedBy) {
        String sql = "UPDATE CONTACT_MSG SET STATUS = ?, UPDATED_BY = ?, UPDATED_AT = ? WHERE CONTACT_ID = ?";
        return jdbcTemplate.update(sql, new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps) throws SQLException {
                ps.setString(1,newStatus);
                ps.setString(2,updatedBy);
                ps.setTimestamp(3, Timestamp.valueOf(LocalDateTime.now()));
                ps.setInt(4,contactId);
            }
        });
    }
}
