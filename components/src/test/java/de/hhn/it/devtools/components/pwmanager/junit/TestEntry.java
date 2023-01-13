package de.hhn.it.devtools.components.pwmanager.junit;

import de.hhn.it.devtools.apis.pwmanager.Entry;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TestEntry {

  private int entryId;
  private String url;
  private String username;
  private String email;
  private String password;
  Entry entry;

  @BeforeEach
  void setup() {
    entryId = 1;
    url = "testurl";
    username = "testuser";
    email = "test@web.de";
    password = "test";
    entry = new Entry(entryId,url,username,email,password);
  }

  @Test
  void testGetVariables() {
    assertEquals(1,entry.getEntryId());
    assertEquals(url,entry.getUrl());
    assertEquals(username,entry.getUsername());
    assertEquals(email,entry.getEmail());
    assertEquals(password,entry.getPassword());
  }

  @Test
  void testSetVariables() {
   entry.setUrl("test2url");
   entry.setUsername("test2user");
   entry.setEmail("test@gmail.com");
   entry.setPassword("test2");

    assertEquals("test2url",entry.getUrl());
    assertEquals("test2user",entry.getUsername());
    assertEquals("test@gmail.com",entry.getEmail());
    assertEquals("test2",entry.getPassword());
  }
}
