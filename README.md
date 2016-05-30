# masudio.com

compile (from git root folder): mvn clean package
-> http://stackoverflow.com/questions/574594/how-can-i-create-an-executable-jar-with-dependencies-using-maven

deploy (from git root folder): cp -i ~/.ssh/aws-ec2-keypair.pem target/masudio-site-target-pkg-1.0-SNAPSHOT.jar ec2-user@<instance public DNS>:/home/ec2-user/lib/

to run (from /home/ec2-user folder on server): sudo java -cp lib/slf4j-log4j12-1.7.10.jar:lib/masudio-site-target-pkg-1.0-SNAPSHOT.jar Launcher

##Creating a new ec2 instance
use AMI
add an IAM role to ec2 instance (only allowed at instance creation time in the instance details section at review page)
associate the usual keypair with the host
ssh to the host (if there are security issues, try deleting the IP address AND host DNS entries in ~/.ssh/known_hosts and try again
> sudo mkdir /env
> sudo chown ec2-user:ec2-user /env
> mkdir /env/masudio.com
> mkdir /env/masudio.com/logs
> mkdir /env/masudio.com/lib
> {download slf4j-log4j correct version}
see http://docs.aws.amazon.com/AmazonCloudWatch/latest/DeveloperGuide/QuickStartEC2Instance.html to install awslogs, copy the awslogs conf from repo resources/ and then use the wiki to find command to run to start it up
> {copy awscli.conf and awslogs.conf}
> sudo yum install java-1.8.0
> sudo yum remove java-1.7.0-openjdk
Enter 'y' to say to do it...twice
> sudo /usr/sbin/alternatives --config java
Enter '2' enter
> sudo service awslogs start
run it
check logs are showing up
check logs are being pulled
> sudo chkconfig awslogs on

## TODO:
Add a logpuller and log archiving (continued)

Add & configure SLF4J attributes support

Add host memory metrics

Automate instance creation steps

Look into chef and ec2 containers (cheaper, faster?)