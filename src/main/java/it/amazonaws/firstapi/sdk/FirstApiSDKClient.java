/**
 * null
 */
package it.amazonaws.firstapi.sdk;

import java.net.*;
import java.util.*;

import javax.annotation.Generated;

import org.apache.commons.logging.*;

import com.amazonaws.*;
import com.amazonaws.opensdk.*;
import com.amazonaws.opensdk.model.*;
import com.amazonaws.opensdk.protect.model.transform.*;
import com.amazonaws.auth.*;
import com.amazonaws.handlers.*;
import com.amazonaws.http.*;
import com.amazonaws.internal.*;
import com.amazonaws.metrics.*;
import com.amazonaws.regions.*;
import com.amazonaws.transform.*;
import com.amazonaws.util.*;
import com.amazonaws.protocol.json.*;

import com.amazonaws.annotation.ThreadSafe;
import com.amazonaws.client.AwsSyncClientParams;

import com.amazonaws.client.ClientHandler;
import com.amazonaws.client.ClientHandlerParams;
import com.amazonaws.client.ClientExecutionParams;
import com.amazonaws.opensdk.protect.client.SdkClientHandler;
import com.amazonaws.SdkBaseException;

import it.amazonaws.firstapi.sdk.model.*;
import it.amazonaws.firstapi.sdk.model.transform.*;

/**
 * Client for accessing FirstApiSDK. All service calls made using this client are blocking, and will not return until
 * the service call completes.
 * <p>
 * 
 */
@ThreadSafe
@Generated("com.amazonaws:aws-java-sdk-code-generator")
class FirstApiSDKClient implements FirstApiSDK {

    private final ClientHandler clientHandler;

    private static final com.amazonaws.opensdk.protect.protocol.ApiGatewayProtocolFactoryImpl protocolFactory 
    		= new com.amazonaws.opensdk.protect.protocol.ApiGatewayProtocolFactoryImpl(
    				new JsonClientMetadata()
    				.withProtocolVersion("1.1")
    				.withSupportsCbor(false)
    				.withSupportsIon(false)
    				.withContentTypeOverride("application/json")
                    .withBaseServiceExceptionClass(it.amazonaws.firstapi.sdk.model.FirstApiSDKException.class));

    /**
     * Constructs a new client to invoke service methods on FirstApiSDK using the specified parameters.
     *
     * <p>
     * All service calls made using this new client object are blocking, and will not return until the service call
     * completes.
     *
     * @param clientParams
     *        Object providing client parameters.
     */
    FirstApiSDKClient(AwsSyncClientParams clientParams) {
        this.clientHandler = new SdkClientHandler(new ClientHandlerParams().withClientParams(clientParams));
    }

    /**
     * @param getEchoRequest
     * @return Result of the GetEcho operation returned by the service.
     * @sample FirstApiSDK.GetEcho
     * @see <a href="http://docs.aws.amazon.com/goto/WebAPI/2e2aqdipfi-2019-09-06T12:11:39Z/GetEcho" target="_top">AWS
     *      API Documentation</a>
     */
    @Override
    public GetEchoResult getEcho(GetEchoRequest getEchoRequest) {
        HttpResponseHandler<GetEchoResult> responseHandler = protocolFactory.createResponseHandler(new JsonOperationMetadata().withPayloadJson(true)
                .withHasStreamingSuccessResponse(false), new GetEchoResultJsonUnmarshaller());

        HttpResponseHandler<SdkBaseException> errorResponseHandler = createErrorResponseHandler();

        return clientHandler.execute(new ClientExecutionParams<GetEchoRequest, GetEchoResult>()
                .withMarshaller(new GetEchoRequestProtocolMarshaller(protocolFactory)).withResponseHandler(responseHandler)
                .withErrorResponseHandler(errorResponseHandler).withInput(getEchoRequest));
    }

    /**
     * @param postEchoRequest
     * @return Result of the PostEcho operation returned by the service.
     * @sample FirstApiSDK.PostEcho
     * @see <a href="http://docs.aws.amazon.com/goto/WebAPI/2e2aqdipfi-2019-09-06T12:11:39Z/PostEcho" target="_top">AWS
     *      API Documentation</a>
     */
    @Override
    public PostEchoResult postEcho(PostEchoRequest postEchoRequest) {
        HttpResponseHandler<PostEchoResult> responseHandler = protocolFactory.createResponseHandler(new JsonOperationMetadata().withPayloadJson(true)
                .withHasStreamingSuccessResponse(false), new PostEchoResultJsonUnmarshaller());

        HttpResponseHandler<SdkBaseException> errorResponseHandler = createErrorResponseHandler();

        return clientHandler.execute(new ClientExecutionParams<PostEchoRequest, PostEchoResult>()
                .withMarshaller(new PostEchoRequestProtocolMarshaller(protocolFactory)).withResponseHandler(responseHandler)
                .withErrorResponseHandler(errorResponseHandler).withInput(postEchoRequest));
    }

    /**
     * Create the error response handler for the operation.
     * 
     * @param errorShapeMetadata
     *        Error metadata for the given operation
     * @return Configured error response handler to pass to HTTP layer
     */
    private HttpResponseHandler<SdkBaseException> createErrorResponseHandler(JsonErrorShapeMetadata... errorShapeMetadata) {
        return protocolFactory.createErrorResponseHandler(new JsonErrorResponseMetadata().withErrorShapes(Arrays.asList(errorShapeMetadata)));
    }

    @Override
    public void shutdown() {
        clientHandler.shutdown();
    }

}
