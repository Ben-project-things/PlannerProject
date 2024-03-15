Overview:

Quick start:
To get started, the user would create a PlannerSystem with no inputs, or supply it an existing list
of users if need be. The client then can use any of the methods available in the PlannerModel to
interact with the PlannerSystem.

Key components:
There are four main components:
controller.PlannerController:  Which will be implemented when the controller is needed.

model.PlannerModel: The model in which the PlannerSystem operates on.
    uploadSchedule:
        allows a client to upload an XML of a user's schedule to the database
    saveSchedule:
        saves a user's schedule as an XML file
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

Key components:
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