/**
 * null
 */
package it.amazonaws.firstapi.sdk.model.transform;

import javax.annotation.Generated;

import com.amazonaws.SdkClientException;
import it.amazonaws.firstapi.sdk.model.*;

import com.amazonaws.protocol.*;
import com.amazonaws.annotation.SdkInternalApi;

/**
 * GetEchoRequestMarshaller
 */
@Generated("com.amazonaws:aws-java-sdk-code-generator")
@SdkInternalApi
public class GetEchoRequestMarshaller {

    private static final GetEchoRequestMarshaller instance = new GetEchoRequestMarshaller();

    public static GetEchoRequestMarshaller getInstance() {
        return instance;
    }

    /**
     * Marshall the given parameter object.
     */
    public void marshall(GetEchoRequest getEchoRequest, ProtocolMarshaller protocolMarshaller) {

        if (getEchoRequest == null) {
            throw new SdkClientException("Invalid argument passed to marshall(...)");
        }

        try {
        } catch (Exception e) {
            throw new SdkClientException("Unable to marshall request to JSON: " + e.getMessage(), e);
        }
    }

}
