package co.com.nequi.dynamodb.entity;


import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;

@DynamoDbBean
public class TemplateDestRQ {

    private String body;

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    @Override
    public String toString() {
        return "TemplateDestRQ{" +
                "body='" + body + '\'' +
                '}';
    }
}
