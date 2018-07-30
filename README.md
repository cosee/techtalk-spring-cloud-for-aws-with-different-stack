## Spring Boot for AWS project presented in the cosee techtalk on 26.07.2018
This project is a comparison to the spring cloud project. 
It shows how to implement the basic functionality of the spring cloud project without using spring cloud packages.
For more information on our next talks please visit: https://talks.cosee.biz/

The cloudformation template `springCloudSampleStack.template` defines necessary IAM roles and creates new VPC, Subnets and Security Groups.
It also creates a new EC2 Server with your running application.

Before you can execute this application you need to first define your own SQS and copy its path to the `aws.sqs.name.order` parameter in the `application.properties`.
For the SQS creation you can use the `longevitySQS.template`.

Furthermore if you want to use RDS functionality you would need to create a RDS instance beforehand and copy it's path
to the `spring.datasource.url` parameter in the `application.properties`.
Note that currently there was no Database functionality implemented as this was just a showcase. 

To create a new Stack yourself you need to do following steps:
1. Compile the application with `mvn clean package`
2. Upload the resulting jar to S3
3. Copy the path from S3. Go to the `springCloudSampleStack.template` file located in the `cloudformation` directory of this project. 
Replace the `BUCKET_NAME/PATH/TO/JAR` of the EC2 Server bash command with your path.
4. Start up the stack with the `springCloudSampleStack.template` file either via GUI or CLI.
For the CLI you have to upload the template file to S3. Replace the \<> parameters with your own values.
Then you can execute the following command:

aws cloudformation create-stack \
--stack-name \<STACK_NAME> \
--template-url \<PATH_TO_CLOUDFORMATION_TEMPLATE_IN_S3> \
--capabilities CAPABILITY_IAM


Repositories with applications from this Talk:

https://github.com/cosee/techtalk-spring-cloud-for-aws

https://github.com/cosee/techtalk-spring-boot-for-aws

https://github.com/cosee/techtalk-spring-cloud-for-aws-with-different-stack
