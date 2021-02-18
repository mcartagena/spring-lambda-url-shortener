package com.mcartagena.functions;

import com.amazonaws.services.lambda.invoke.LambdaFunction;
import com.mcartagena.functiontypes.EmailDetails;

public interface LambdaFunctions {

    @LambdaFunction(functionName = "sendShortcodeEmail")
    Boolean sendShortcodeGeneratedEmail(EmailDetails emailDetails);
    
}
