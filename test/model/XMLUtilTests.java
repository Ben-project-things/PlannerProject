package model;

import org.junit.Test;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import schedule.Days;
import schedule.Event;
import schedule.User;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * A test for the functionality of conversion between XMLs and Users.
 */
public class XMLUtilTests {

  @Test
  public void testConversionsBetweenXMLandUsers() throws Exception {
    User user = new User("TestUser");
    Event event1 = new Event.Builder().name("Homework")
            .location("Churchill").isOnline(true).startDay(Days.MONDAY).startTime(1200).
            endDay(Days.THURSDAY).endTime(2100).host(new User("Ben")).
            build();
    user.getSchedule().addEvent(event1);
    XMLUtil.convertUserToXML(user);

    File file = new File("TestUser.xml");
    assertTrue(file.exists());
    List<String> lines = Files.readAllLines(Paths.get("TestUser.xml"));
    String content = String.join("\n", lines);

    //Checking if the file has some given XML tags
    assertTrue(content.contains("<name>Homework</name>"));
    assertTrue(content.contains("<start-day>MONDAY</start-day>"));

    //Converting the test XML back into a user and seeing if it converts back to a user object
    User user2 = XMLUtil.parseUserFromXML(file);
    assertEquals("TestUser", user2.getName());
    assertEquals(1, user.getSchedule().getEventsInSchedule().size());
    assertEquals("Homework", user.getSchedule().getEventsInSchedule().get(0).getName());
    assertEquals(Days.MONDAY, user.getSchedule().getEventsInSchedule().get(0).getStartDay());
    assertEquals(1200, user.getSchedule().getEventsInSchedule().get(0).getStartTime());

    file.delete();
  }
}
