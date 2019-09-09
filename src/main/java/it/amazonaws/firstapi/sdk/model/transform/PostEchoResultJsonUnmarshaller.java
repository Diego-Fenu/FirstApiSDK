/**
 * null
 */
package it.amazonaws.firstapi.sdk.model.transform;

import java.math.*;

import javax.annotation.Generated;

import it.amazonaws.firstapi.sdk.model.*;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers.*;
import com.amazonaws.transform.*;

import com.fasterxml.jackson.core.JsonToken;
import static com.fasterxml.jackson.core.JsonToken.*;

/**
 * PostEchoResult JSON Unmarshaller
 */
@Generated("com.amazonaws:aws-java-sdk-code-generator")
public class PostEchoResultJsonUnmarshaller implements Unmarshaller<PostEchoResult, JsonUnmarshallerContext> {

    public PostEchoResult unmarshall(JsonUnmarshallerContext context) throws Exception {
        PostEchoResult postEchoResult = new PostEchoResult();

        int originalDepth = context.getCurrentDepth();
        String currentParentElement = context.getCurrentParentElement();
        int targetDepth = originalDepth + 1;

        JsonToken token = context.getCurrentToken();
        if (token == null)
            token = context.nextToken();
        if (token == VALUE_NULL) {
            return postEchoResult;
        }

        while (true) {
            if (token == null)
                break;

            postEchoResult.setEmpty(EmptyJsonUnmarshaller.getInstance().unmarshall(context));
            token = context.nextToken();
        }

        return postEchoResult;
    }

    private static PostEchoResultJsonUnmarshaller instance;

    public static PostEchoResultJsonUnmarshaller getInstance() {
        if (instance == null)
            instance = new PostEchoResultJsonUnmarshaller();
        return instance;
    }
}
