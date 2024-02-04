package com.example;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.example.constants.ContactConstants;
import com.example.model.Contact;
import com.example.repository.ContactRepository;
import com.example.service.ContactService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class EasySchoolApplicationTests {

	@Mock
	private ContactRepository contactRepository;

	@InjectMocks
	private ContactService contactService;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.initMocks(this);
	}


	@Test
	void findMessagesWithOpenStatus_ShouldReturnListWithOpenStatus() {
		// Arrange
		Mockito.when(contactRepository.findContactByStatus(ContactConstants.OPEN))
				.thenReturn(Arrays.asList(new Contact(), new Contact()));

		// Act
		List<Contact> contacts = contactService.findMessagesWithOpenStatus();

		// Assert
		assertEquals(2, contacts.size());
		for (Contact contact : contacts) {
			assertEquals(ContactConstants.OPEN, contact.getStatus());
		}
	}

	@Test
	void updateMessageStatus_ShouldUpdateStatus() {
		// Arrange
		int contactId = 1;
		String updatedBy = "user";

		Contact existingContact = new Contact();
		existingContact.setContactId(contactId);
		existingContact.setStatus(ContactConstants.OPEN);

		Mockito.when(contactRepository.findById(contactId)).thenReturn(Optional.of(existingContact));
		Mockito.when(contactRepository.save(Mockito.any(Contact.class))).thenReturn(existingContact);

		// Act
		boolean result = contactService.updateMessageStatus(contactId);

		// Assert
		assertTrue(result);
		assertEquals(ContactConstants.CLOSE, existingContact.getStatus());
		assertEquals(updatedBy, existingContact.getUpdatedBy());
		assertNotNull(existingContact.getUpdatedAt());
	}
}
