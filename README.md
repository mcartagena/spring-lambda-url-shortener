# spring-lambda-url-shortener
I'll develop the surplice URL shortener using Spring Cloud functions. In this repository I will create a Spring based project with the necessary Spring Cloud dependencies an AWS dependences there all I'll need in this project. Will then set up and configure the database so that I can store the short URL and full URL mappings. The mill builds Spring Cloud functions that create short URLs from the URL. Next I'll build Spring Cloud function that takes that short code and provides the full URL. Will deploy these Spring Cloud functions into AWS, configure the API Gateway to redirect the browser to the real site. I will incorporate the simple email serverless from AWS into our serverless application to send email notifications when people create their short codes, and then finally, I will build the Lambda service to send those email notifications. 

I'll follow the next architecture:

![Application Architecture](/project-spring-cloud.png "Architecture")
