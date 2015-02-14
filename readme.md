To build the website:

`mvn package`

To run the website:

`java -jar target/SteamRankingsWebsite-1.1-SNAPSHOT.jar`

You can also run/debug the website from Eclipse, but it has a tendency to do weird things so I can't write anything about it.  It should work fine, though, and did last time I tested it.  You may want to run `mvn eclipse:eclipse` if Eclipse gets confused as to the website structure.

The `master` branch for both the service and the website is built by Jenkins, and therefore should be stable.  By default, Maven will look to Jenkins when building the website for the API this website depends on.  If, however, `mvn install` is run for the service (see the service readme) the API will be installed onto your local computer.  This means that when the website is built, the API will be pulled from your computer rather than the Jenkins server.  Jenkins may be used instead though when the latest push from master is more recent than the last time `mvn install` was run for the service on your computer, but I can't seem to find out one way or another if this is true.  

If weird things do happen, though, run

`mvn dependency:purge-local-repository`

to delete any local copies and force Maven to pull from the clean Jenkins version.  You could also run `mvn install` on the service to make your copy newer again.

If your latest API code doesn't get pulled into the website as it should, run

`mvn clean package`

to force Maven to get the latest copy.

[![Build Status](http://mikemontreal.ignorelist.com:58722/buildStatus/icon?job=SteamRankingsWebsite)](http://mikemontreal.ignorelist.com:58722/job/SteamRankingsWebsite/)