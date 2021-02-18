package com.mcartagena;

import org.springframework.cloud.function.adapter.aws.SpringBootRequestHandler;
import com.mcartagena.functiontypes.EmailDetails;

public class SpringEmailLambdaHandler extends SpringBootRequestHandler<EmailDetails, Boolean>{
    
}
