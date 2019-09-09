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
 * GetEchoResult JSON Unmarshaller
 */
@Generated("com.amazonaws:aws-java-sdk-code-generator")
public class GetEchoResultJsonUnmarshaller implements Unmarshaller<GetEchoResult, JsonUnmarshallerContext> {

    public GetEchoResult unmarshall(JsonUnmarshallerContext context) throws Exception {
        GetEchoResult getEchoResult = new GetEchoResult();

        int originalDepth = context.getCurrentDepth();
        String currentParentElement = context.getCurrentParentElement();
        int targetDepth = originalDepth + 1;

        JsonToken token = context.getCurrentToken();
        if (token == null)
            token = context.nextToken();
        if (token == VALUE_NULL) {
            return getEchoResult;
        }

        while (true) {
            if (token == null)
                break;

            getEchoResult.setEmpty(EmptyJsonUnmarshaller.getInstance().unmarshall(context));
            token = context.nextToken();
        }

        return getEchoResult;
    }

    private static GetEchoResultJsonUnmarshaller instance;

    public static GetEchoResultJsonUnmarshaller getInstance() {
        if (instance == null)
            instance = new GetEchoResultJsonUnmarshaller();
        return instance;
    }
}
