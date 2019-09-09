/**
 * null
 */
package it.amazonaws.firstapi.sdk;

import com.amazonaws.annotation.NotThreadSafe;
import com.amazonaws.client.AwsSyncClientParams;
import com.amazonaws.opensdk.protect.client.SdkSyncClientBuilder;
import com.amazonaws.opensdk.internal.config.ApiGatewayClientConfigurationFactory;
import com.amazonaws.util.RuntimeHttpUtils;
import com.amazonaws.Protocol;

import java.net.URI;
import javax.annotation.Generated;

/**
 * Fluent builder for {@link it.amazonaws.firstapi.sdk.FirstApiSDK}.
 * 
 * @see it.amazonaws.firstapi.sdk.FirstApiSDK#builder
 **/
@NotThreadSafe
@Generated("com.amazonaws:aws-java-sdk-code-generator")
public final class FirstApiSDKClientBuilder extends SdkSyncClientBuilder<FirstApiSDKClientBuilder, FirstApiSDK> {

    private static final URI DEFAULT_ENDPOINT = RuntimeHttpUtils.toUri("2e2aqdipfi.execute-api.us-east-2.amazonaws.com", Protocol.HTTPS);
    private static final String DEFAULT_REGION = "us-east-2";

    /**
     * Package private constructor - builder should be created via {@link FirstApiSDK#builder()}
     */
    FirstApiSDKClientBuilder() {
        super(new ApiGatewayClientConfigurationFactory());
    }

    /**
     * Construct a synchronous implementation of FirstApiSDK using the current builder configuration.
     *
     * @param params
     *        Current builder configuration represented as a parameter object.
     * @return Fully configured implementation of FirstApiSDK.
     */
    @Override
    protected FirstApiSDK build(AwsSyncClientParams params) {
        return new FirstApiSDKClient(params);
    }

    @Override
    protected URI defaultEndpoint() {
        return DEFAULT_ENDPOINT;
    }

    @Override
    protected String defaultRegion() {
        return DEFAULT_REGION;
    }

}
