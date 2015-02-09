To build the website:
mvn install

To run the website:
maven -jar target/SteamRankingsWebsite-1.0-SNAPSHOT.jar

Keep in mind that if any changes are made to the API code that's stored in the backend but runs on the website (e.g. Profiles.java) the changes won't run unless that code is pushed to master since the website build is linked to Jenkins.  This isn't ideal and we don't want dev code on master, so we'll come up with something better soon.
[![Build Status](http://mikemontreal.ignorelist.com:58722/buildStatus/icon?job=SteamRankingsWebsite)](http://mikemontreal.ignorelist.com:58722/job/SteamRankingsWebsite/)