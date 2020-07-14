package interview.spring.cloud.awssqs;

import com.amazonaws.services.sqs.model.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@SpringBootApplication
@RestController
public class AwsSqsApplication {

	public static void main(String[] args) {
		SpringApplication.run(AwsSqsApplication.class, args);
	}
	private static Logger logger = LoggerFactory.getLogger(AwsSqsApplication.class);
	@Autowired
	QueueMessagingTemplate queueMessagingTemplate;

	@Value("${cloud.aws.end-point.uri}")
	private String end_point;
	@GetMapping(value="/sqs")
	public String sendSQS(@RequestParam("message") String message){
		String response = "success send msg '"+message+"' !";
		queueMessagingTemplate.send(this.end_point, MessageBuilder.withPayload(message).build());
		return response;
	}
	@SqsListener("demo-standard-sqs")
	public void printMsgFromSQS(String message){
		logger.info("Message get from SQS:"+message);
	}
}
