-  CU 14.3 - Assign reviewers to submissions

 This procedure can be executed since view "Submissions list" with url /submission/administrator/list.do when logged as admin.
A link called "Assign reviewers" is shown in submissions without reviewers. To check it, it's recommended
to run it on submission with ticker "AAA-2345". Populate has five reviewers, two of them have an expertise in common
with the title or summary of this conference. These two reviewers will be assigned and one else whitout an expertise in common
 to guarantee that every submission is assigned to three reviewers.

- CU 31.1 - Process that computes score

 PopulateDatabase.xml includes some conferences that have words in common with some camera-ready papers. When executed,
author1 (with cameraReadyPaper1) will have an score of 1 because his camera-ready paper contains "intelligence" and this is 
a buzzword.


- CU 14.6  - Manage activities
 To manage activities, an admin must go to "Show conference view" of a conference that isn't in final mode.