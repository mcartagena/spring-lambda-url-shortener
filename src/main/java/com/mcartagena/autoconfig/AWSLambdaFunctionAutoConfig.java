package com.mcartagena.autoconfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mcartagena.functions.LambdaFunctions;
import com.amazonaws.services.lambda.invoke.LambdaInvokerFactory;
import com.amazonaws.services.lambda.AWSLambdaClientBuilder;

@Configuration
public class AWSLambdaFunctionAutoConfig {

    @Bean
    public LambdaFunctions lambdaFunctions(){
        return LambdaInvokerFactory.builder()
                .lambdaClient(AWSLambdaClientBuilder.defaultClient())
                .build(LambdaFunctions.class);
    }
    
}
