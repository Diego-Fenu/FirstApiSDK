/**
 * null
 */
package it.amazonaws.firstapi.sdk;

import javax.annotation.Generated;

import com.amazonaws.*;
import com.amazonaws.opensdk.*;
import com.amazonaws.opensdk.model.*;
import com.amazonaws.regions.*;

import it.amazonaws.firstapi.sdk.model.*;

/**
 * Interface for accessing FirstApiSDK.
 */
@Generated("com.amazonaws:aws-java-sdk-code-generator")
public interface FirstApiSDK {

    /**
     * @param getEchoRequest
     * @return Result of the GetEcho operation returned by the service.
     * @sample FirstApiSDK.GetEcho
     * @see <a href="http://docs.aws.amazon.com/goto/WebAPI/2e2aqdipfi-2019-09-06T12:11:39Z/GetEcho" target="_top">AWS
     *      API Documentation</a>
     */
    GetEchoResult getEcho(GetEchoRequest getEchoRequest);

    /**
     * @param postEchoRequest
     * @return Result of the PostEcho operation returned by the service.
     * @sample FirstApiSDK.PostEcho
     * @see <a href="http://docs.aws.amazon.com/goto/WebAPI/2e2aqdipfi-2019-09-06T12:11:39Z/PostEcho" target="_top">AWS
     *      API Documentation</a>
     */
    PostEchoResult postEcho(PostEchoRequest postEchoRequest);

    /**
     * @return Create new instance of builder with all defaults set.
     */
    public static FirstApiSDKClientBuilder builder() {
        return new FirstApiSDKClientBuilder();
    }

    /**
     * Shuts down this client object, releasing any resources that might be held open. This is an optional method, and
     * callers are not expected to call it, but can if they want to explicitly release any open resources. Once a client
     * has been shutdown, it should not be used to make any more requests.
     */
    void shutdown();

}
