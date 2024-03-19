package ru.netology.moneytransferservice;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Testcontainers;
import ru.netology.moneytransferservice.model.Amount;
import ru.netology.moneytransferservice.model.request.TransferRequest;
import ru.netology.moneytransferservice.model.response.TransferAndConfirmResponse;

import java.util.Objects;

import static ru.netology.moneytransferservice.TestData.OPERATION_ID;
import static ru.netology.moneytransferservice.TestData.TRANSFER_REQUEST_1;

@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class MoneyTransferServiceApplicationTests {

	private static final String HOST_WITHOUT_PORT = "http://localhost:";
	private static final String TRANSFER = "/transfer";
	private static final int PORT = 5500;

	@Autowired
	TestRestTemplate testRestTemplate;

	private static final GenericContainer<?> container = new GenericContainer<>("transfer")
			.withExposedPorts(PORT);

	@BeforeAll
	public static void startContainer() {
		container.start();
	}

	@Test
	void contextLoads() {
		ResponseEntity<TransferAndConfirmResponse> forTransfer = testRestTemplate
				.postForEntity(HOST_WITHOUT_PORT + container.getMappedPort(PORT) + TRANSFER, TRANSFER_REQUEST_1,
						TransferAndConfirmResponse.class);

		Assertions.assertEquals(Objects.requireNonNull(forTransfer.getBody()).getOperationId(), OPERATION_ID);
	}

	@Test
	void httpStatusOkRequestTransferTest(){
		ResponseEntity<String> response = testRestTemplate.postForEntity(HOST_WITHOUT_PORT + container.getMappedPort(PORT) +
				TRANSFER, TRANSFER_REQUEST_1, String.class);

		HttpStatus expected = HttpStatus.OK;

		HttpStatus actual = (HttpStatus) response.getStatusCode();

		Assertions.assertEquals(expected, actual);
	}

	@Test
	void testHttpStatusBadRequestTransfer() {
		TransferRequest badTransferRequest = new TransferRequest("1111000011110000", "11/27",
				"100", "2222000022220000", new Amount(1_000_000, "RUR"));

		ResponseEntity<String> response = testRestTemplate.postForEntity(HOST_WITHOUT_PORT + container.getMappedPort(PORT) +
				TRANSFER, badTransferRequest, String.class);

		HttpStatus expected = HttpStatus.BAD_REQUEST;

		HttpStatus actual = (HttpStatus) response.getStatusCode();

		Assertions.assertEquals(expected, actual);
	}
}
