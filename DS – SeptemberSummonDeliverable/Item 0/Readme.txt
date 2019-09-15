Item 0 includes a queries documentation and Acme Conference mockups. Tests are included in item 1 with
Eclipse/Maven project.

- Requirement 4 - Paper and camera-ready paper
 Paper and camera-ready paper were considered different entities to make easier the maintenance. For example, 
if camera-ready papers needed a new attribute with a publication moment, it would be very easy to implement it. However, 
if they were the same entity it would be harder to implement it. Furthermore, camera-ready paper is related to presentation.

- Requirement 11.4 - An actor who is not authenticated must be able to search for a conference using a single keyword that must appear somewhere in its
title, its venue, or its description.
In the information requirements we could not find that you need to store the description of a conference so we followed conferences information requirement.

- Requierement 14.3 - Assign reviewers to submissions
 This procedure can be executed since view "Submissions list" with url /submission/administrator/list.do when logged as admin.
A link called "Assign reviewers" is shown in submissions without reviewers. To check it, it's recommended
to run it on submission with ticker "AAA-2345". Populate has five reviewers, two of them have an expertise in common
with the title or summary of this submission. These two reviewers will be assigned and one else whitout an expertise in common
 to guarantee that every submission is assigned to three reviewers.

- Requirement 14.5  - Process that notifies when a decision is made regarding a submission
This process is executed at the same time as the process that decides whether or not to accept submission is executed.

- Requirement 14.6  - Manage activities
 To manage activities, an admin must go to "Show conference view" of a conference that is in final mode.

- Requirement 27  - It’s expected that actors can comment on more entities in future releases of the project
 To make it easier in future releases, entities that want to be commented must extend "Commentable", and they will automatically be
related to comments.

