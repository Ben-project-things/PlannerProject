Overview:
The goal of this codebase is to implement a system which allows a client to interact with a planner
system. It is assumed that the user is familiar with what xml files are and how they are formatted.

Quick start:
To get started, the user would create a PlannerSystem with no inputs, or supply it an existing list
of users if need be. The client then can use any of the methods available in the PlannerModel to
interact with the PlannerSystem.

Key components:
There are four main components:
controller.PlannerController: Which will be implemented when the controller is needed.

model.PlannerModel: The model in which the PlannerSystem operates on.
    uploadSchedule:
        allows a client to upload an xml of a user's schedule to the database
    saveSchedule:
        saves a user's schedule as an xml file
    displayUser:
        which will display the user's schedule as a textual rendering
    addEvent:
        allows the client to add an event to a given user's schedule
    modifyEvent:
        allows the client to modify the event, updating it for all invitees of the event
    removeEvent:
        allows the client to remove an event from a given user's schedule
    autoSchedule:
        which when implemented, will automatically schedule an event for a user
    eventAtTime:
        which allows the client to check whether a given user has an event at a given day and time

schedule.ScheduleModel: The model which outlines what a schedule can do.
    addEvent:
        allows the client to add an event to this schedule
    modifyEvent:
        allows the client to modify an event, updating it for all invitees of the event
    removeEvent:
        allows the client to remove an event from this schedule

view.PlannerView: Represents the eventually implementation of views for the Planner System
    makeVisible:
        will make this view visible to the user.
    refresh:
        will cause the panel to update according to what model it has.

Key sub-components:
The main nouns in this system are Users which represent a name and a schedule, an enum of the days
of the week, and an event, which has the fields representing the different attributes of an event.

Source Organization:
In terms of design choices for this project, we split the implementation of the schedule separate
from the overall planner system since schedules need to be able to perform certain functions on
there own while the Planner System should be able to perform other actions, but call the schedule
implementation for adding, modifying, and deleting events. The User is what ultimately holds a
schedule and the planner system database represents a list of users, each with their own schedule.
The Events are put within the schedule package as this is where they are used and it represents
where the actual data of the event is stored. Same with Users and the enum of Days as they all
relate to a schedule.

Other Notes:
For methods like modifyEvent, I used a scanner for right now since its the easiest way to implement
user interactions with the system without having implemented a GUI yet. The controller interface and
implementation are not implemented and completed yet since this assignment does not require it.
There are other empty stubs like for the method autoSchedule since the assignment doesn't require
implementation of it yet as stated in section 4.


Changes for part 2:
I added one method into the ReadOnlyPlannerModel system which was one that
List<Event> getAllEvents(User user); I added this because in the checklist it says for our model
planner it needs to see what events are in a schedule. Before this, I had a way to check events in a
schedule, but no direct way from the actual planner system itself. I also added a new field to the
PlannerSystem which is the selectedUser, all this field represents is the current user that is
selected. For this, I added another method as an observation which is to see who the selectedUser
is at the time (useful for the view on who to display). I also separated two methods in the planner
system of retrieving allEvents for a specific user and allEvents in the planner system itself. I
also overrode the toString() in the User class so that when I used it in a JComboBox when it
displayed the list of users, they would display the names of the user.

In terms of new additions for this part of the project, I made sure to use interfaces for most of
the concrete classes that I was to implement. I have my PlannerModelView representing the main
functions of the main view panel that displays the schedule. In this, I have the XMLs, and basically
a refresh and a makeVisible method. In the actual implementation, I made it in the MainFrame and
built the components I needed for this main part (the buttons and dropdown on the bottom and the
file selection at the top). I then have a different class (the SchedulePanel) actually deal with
drawing the schedule layout and the possible events listed. In that class it has one field of the
schedule that it should currently be displaying, so it can dynamically update the schedule when
changed. The next things I had were the EventView and EventPanelView interfaces which were to deal
with the create event button. The event view just represents the frame itself and didn't really have
any major methods besides makeVisible and refresh. In this class though is where the frame is set up
and the panel. The actual panel contents is taken care of in EventPanel, which has methods like
clearing the form for when dealing with modify and create (the modify and create share similar
properties). The modify works almost like the create event, just has a flag that detects when
someone is modifying an event, in which the user can then click create event once they've selected
the event to modify. This class has error dialogs show up as well which is a method in the interface
.

Changes for part 3:
There were a few things I changed before starting part 3. I fixed some things relating to toString
methods and printing the correct details of an event by adding a method which you could call on an
event to print its exact details. I also added a new method in the EventPanel to close the parent
frame to allow actions like creating, modifying, and removing events to actual close the eventFrame
when the action is completed. I then added the missing functionality of clicking on an event on the
SchedulePanel. To do this, I modified some existing code structure, making the SchedulePanel have a
ReadOnlyPlannerModel as well so I could use it to pass to a new constructor I made for an EventFrame
which would take in the selected event and load up the existing details of those events. I removed
the empty constructor in SchedulePanel since now I have the SchedulePanel have the model and operate
on that instead of just passing in the currentSchedule being displayed which I also have as a field
.

Moving onto implementing the controller, I just refactored the interface itself to be called
Features and added the actions that the controller should be able to handle. I also updated my view
to work better with my controller, adding a few methods to the PlannerView interface itself since I
would need some method functionality for the controller (I had some private method things in the
view which I could make part of the interface).

I updated a lot of how my program functioned. I simplified the modify and remove buttons with the
addition of the mouse adaptor, only letting the user use these buttons if an event is selected
beforehand on the mainFrame. I also tried to simplify a lot of the interfaces and methods that I was
not using and or didn't need like in the EventPanelView removing the actual create, modify, and
remove methods since it was now handled by the controller.

Lastly, working with the new strategies, I made a new interface for any strategy to implement called
SchedulingStrategy. The sole autoSchedule method in this interface takes in the options that are to
be selected from new ScheduleFrame. The new ScheduleFrame is pretty similar to the EventFrame and
panel, I just combined the setting up of both of them into the ScheduleFrame. When working with the
new strategies, I added a new method to the Days enum which would just allow me to get the next day
from the previous one (so that when you gave duration that rolled over to the next day, I could have
a method to figure out what day that was. Implementing the strategies, I had the first