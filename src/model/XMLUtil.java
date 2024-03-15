package model;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import schedule.Event;
import schedule.User;
import schedule.Days;

/**
 * Class used to parse XML to User objects and to convert User objects into XML files.
 */
public class XMLUtil {

  /**
   * Method used to parse from XML formatting to the User format.
   *
   * @param xmlFile is the xml file to parse
   * @return the user object generated from this XML file
   * @throws Exception if there is an error parsing building or opening the file
   */
  public static User parseUserFromXML(File xmlFile) throws Exception {
    try {
      DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
      Document doc = builder.parse(xmlFile);
      doc.getDocumentElement().normalize();

      Element scheduleElement = doc.getDocumentElement();
      String userName = scheduleElement.getAttribute("id");
      User user = new User(userName);

      NodeList eventList = scheduleElement.getElementsByTagName("event");
      for (int i = 0; i < eventList.getLength(); i++) {
        Node eventNode = eventList.item(i);
        if (eventNode.getNodeType() == Node.ELEMENT_NODE) {
          Element eventElement = (Element) eventNode;
          Element timeElement = (Element) eventElement.getElementsByTagName("time").item(0);

          Event event = new Event.Builder().
                  name(eventElement.getElementsByTagName("name").item(0).getTextContent()).
                  location(eventElement.getElementsByTagName("place").item(0).getTextContent()).
                  isOnline(Boolean.parseBoolean(eventElement.getElementsByTagName(
                          "online").item(0).getTextContent())).
                  startDay(Days.valueOf(timeElement.getElementsByTagName(
                          "start-day").item(0).getTextContent().toUpperCase())).
                  startTime(Integer.parseInt(timeElement.getElementsByTagName(
                          "start").item(0).getTextContent())).
                  endDay(Days.valueOf(timeElement.getElementsByTagName(
                          "end-day").item(0).getTextContent().toUpperCase())).
                  endTime(Integer.parseInt(timeElement.getElementsByTagName(
                          "end").item(0).getTextContent())).build();

          user.getSchedule().addEvent(event);
        }
      }
      return user;
    } catch (ParserConfigurationException ex) {
      throw new IllegalStateException("Error in creating the builder");
    } catch (IOException ioEx) {
      throw new IllegalStateException("Error in opening the file");
    } catch (SAXException saxEx) {
      throw new IllegalStateException("Error in parsing the file");
    }
  }

  /**
   * Converts the user's schedule into a personal xml file.
   *
   * @param user is the user to convert to an xml file
   */
  public static void convertUserToXML(User user) {
    try {
      Writer file = new FileWriter(user.getName() + ".xml");
      file.write("<?xml version=\"1.0\"?>\n");
      file.write("<schedule id=\"" + user.getName() + "\">\n");
      for (Event e : user.getSchedule().getEventsInSchedule()) {
        file.write("\t<event>\n");
        file.write("\t\t<name>" + e.getName() + "</name>\n");
        file.write("\t\t<time>\n");
        file.write("\t\t\t<start-day>" + e.getStartDay() + "</start-day>\n");
        file.write("\t\t\t<start>" + e.getStartTime() + "</start>\n");
        file.write("\t\t\t<end-day>" + e.getEndDay() + "</end-day>\n");
        file.write("\t\t\t<end>" + e.getEndTime() + "</end>\n");
        file.write("\t\t</time>\n");
        file.write("\t\t<location>\n");
        file.write("\t\t\t<online>" + e.getIsOnline() + "</online>\n");
        file.write("\t\t\t<place>" + e.getLocation() + "</place>\n");
        file.write("\t\t</location>\n");
        file.write("\t\t<users>\n");
        for (User invitee : e.getInvitees()) {
          file.write("\t\t\t<uid>" + invitee.getName() + "</uid>\n");
        }
        file.write("\t\t</users>\n");
        file.write("\t</event>\n");
      }
      file.write("</schedule>");
      file.close();
    } catch (IOException ex) {
      throw new RuntimeException(ex.getMessage());
    }
  }
}
