-  CU 11.4 - An actor who is not authenticated must be able to search for a conference using a single keyword that must appear somewhere in its
title, its venue, or its description.
In the information requirements we could not find that you need to store the description of a conference so we thought it was a mistake and we didn't implement that.

-  CU 14.3 - Assign reviewers to submissions

 This procedure can be executed since view "Submissions list" with url /submission/administrator/list.do when logged as admin.
A link called "Assign reviewers" is shown in submissions without reviewers. To check it, it's recommended
to run it on submission with ticker "AAA-2345". Populate has five reviewers, two of them have an expertise in common
with the title or summary of this conference. These two reviewers will be assigned and one else whitout an expertise in common
 to guarantee that every submission is assigned to three reviewers.

- CU 14.5  - Process that notifies when a decision is made regarding a submission
This process is executed at the same time as the process that decides whether or not to accept submission is executed

- CU 14.6  - Manage activities
 To manage activities, an admin must go to "Show conference view" of a conference that is in final mode.

- CU 31.1  - Process that computes score

 PopulateDatabase.xml includes some conferences that have words in common with some camera-ready papers. When executed,
author1 (with cameraReadyPaper1) will have an score of 1 because his camera-ready paper contains "intelligence" and this is 
a buzzword.


