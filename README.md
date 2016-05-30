# masudio.com

compile (from git root folder): mvn clean package
-> http://stackoverflow.com/questions/574594/how-can-i-create-an-executable-jar-with-dependencies-using-maven

deploy (from git root folder): cp -i ~/.ssh/aws-ec2-keypair.pem target/masudio-site-target-pkg-1.0-SNAPSHOT.jar ec2-user@ec2-52-202-217-85.compute-1.amazonaws.com:/home/ec2-user/lib/

to run (from /home/ec2-user folder on server): sudo java -cp lib/slf4j-log4j12-1.7.10.jar:lib/masudio-site-target-pkg-1.0-SNAPSHOT.jar Launcher
