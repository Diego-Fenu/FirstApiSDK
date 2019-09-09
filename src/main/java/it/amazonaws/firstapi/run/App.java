package it.amazonaws.firstapi.run;

import com.amazonaws.opensdk.config.ConnectionConfiguration;
import com.amazonaws.opensdk.config.TimeoutConfiguration;

import it.amazonaws.firstapi.sdk.FirstApiSDK;
import it.amazonaws.firstapi.sdk.model.GetEchoRequest;
import it.amazonaws.firstapi.sdk.model.GetEchoResult;
import it.amazonaws.firstapi.sdk.model.PostEchoRequest;
import it.amazonaws.firstapi.sdk.model.PostEchoResult;

public class App {

	FirstApiSDK firstApiSDK;

	public App() {
		initSdk();
	}

	// The configuration settings are for illustration purposes and may not be a
	// recommended best practice.
	private void initSdk() {
		firstApiSDK = FirstApiSDK.builder()
				.connectionConfiguration(
						new ConnectionConfiguration()
						.maxConnections(100)
						.connectionMaxIdleMillis(1000))
				.timeoutConfiguration(
						new TimeoutConfiguration()
						.httpRequestTimeout(3000)
						.totalExecutionTimeout(10000)
						.socketTimeout(2000))
				.build();

	}

	// Calling shutdown is not necessary unless you want to exert explicit control
	// of this resource.
	public void shutdown() {
		firstApiSDK.shutdown();
	}
	/*
	 * // GetEchoResult getEcho(GetEchoRequest getEchoRequest) public Output
	 * getResultWithPathParameters(String x, String y, String operator) { operator =
	 * operator.equals("+") ? "add" : operator; operator = operator.equals("/") ?
	 * "div" : operator;
	 * 
	 * GetEchoResult echoResult = firstApiSDK.getEcho(new
	 * GetEchoRequest().a(x).b(y).op(operator)); return
	 * echoResult.getResult().getOutput(); }
	 * 
	 * public Output getResultWithQueryParameters(String a, String b, String op) {
	 * GetApiRootResult rootResult = firstApiSDK.getApiRoot(new
	 * GetEchoRequest().a(a).b(b).op(op)); return
	 * rootResult.getResult().getOutput(); }
	 * 
	 * public Output geResultByPostInputBody(Double x, Double y, String o) {
	 * 
	 * (new PostEch
	 * 
	 * PostApiRootResult postResult = firstApiSDK .postApiRoot(new
	 * PostEchoRequest().input(new Input().a(x).b(y).op(o))); return
	 * postResult.getResult().getOutput(); }
	 */

	public static void main(String[] args) {
		System.out.println("Simple calc");
		// to begin
		App calc = new App();

		//postEchoRequest.sdkRequestConfig();

		//GetEchoRequest getEchoRequest = new GetEchoRequest();
		//GetEchoResult getEchoResult = calc.firstApiSDK.getEcho(getEchoRequest);
		FirstApiSDK client = FirstApiSDK.builder()
				.connectionConfiguration(
						new ConnectionConfiguration()
						.maxConnections(100)
						.connectionMaxIdleMillis(1000))
				.timeoutConfiguration(
						new TimeoutConfiguration()
						.httpRequestTimeout(3000)
						.totalExecutionTimeout(10000)
						.socketTimeout(2000))
				.build();
		
		
		GetEchoResult result = client.getEcho(new GetEchoRequest());

		
		PostEchoRequest postEchoRequest = new PostEchoRequest();
		PostEchoResult postEchoResult = calc.firstApiSDK.postEcho(postEchoRequest);
		postEchoResult.toString();

		/*
		 * // call the SimpleCalc API Output res = calc.getResultWithPathParameters("1",
		 * "2", "-"); System.out.printf("GET /1/2/-: %s\n", res.getC());
		 * 
		 * // Use the type query parameter res = calc.getResultWithQueryParameters("1",
		 * "2", "+"); System.out.printf("GET /?a=1&b=2&op=+: %s\n", res.getC());
		 * 
		 * // Call POST with an Input body. res = calc.geResultByPostInputBody(1.0, 2.0,
		 * "*"); System.out.printf("PUT /\n\n{\"a\":1, \"b\":2,\"op\":\"*\"}\n %s\n",
		 * res.getC());
		 */
	}
}