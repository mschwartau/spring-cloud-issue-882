package com.example;

import com.example.contracts.ContractBase;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import io.restassured.module.mockmvc.specification.MockMvcRequestSpecification;
import io.restassured.response.ResponseOptions;
import java.io.StringReader;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.junit.Test;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;

import static com.toomuchcoding.jsonassert.JsonAssertion.assertThatJson;
import static io.restassured.module.mockmvc.RestAssuredMockMvc.*;
import static org.springframework.cloud.contract.verifier.assertion.SpringCloudContractAssertions.assertThat;
import static org.springframework.cloud.contract.verifier.util.ContractVerifierUtil.*;

public class ContractVerifierTest extends ContractBase {

	@Test
	public void validate_test() throws Exception {
		// given:
			MockMvcRequestSpecification request = given()
					.header("Content-Type", "application/json")
					.header("Authorization", "ey")
					.body("{\"bezeichnung\":\"bezeichnungX\"}");

		// when:
			ResponseOptions response = given().spec(request)
					.post("/test");

		// then:
			assertThat(response.statusCode()).isEqualTo(200);
			assertThat(response.header("Content-Type")).matches("application/json.*");
		// and:
			DocumentContext parsedJson = JsonPath.parse(response.getBody().asString());
			assertThatJson(parsedJson).field("['versionNr']").isEqualTo(1);
			assertThatJson(parsedJson).field("['bezeichnung']").matches("bezeichnungX");
		// and:
			assertThat(parsedJson.read("$._links.self.href", String.class)).matches("^.*/api-test/v0/test/1/test/\\d+$");
	}

}
