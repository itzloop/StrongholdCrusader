package game.comunication;


public class Request
{
    private RequestType requestType;
    private String body;

    public Request(RequestType requestType, String body) {
        this.requestType = requestType;
        this.body = body;
    }

    public RequestType getRequestType() {
        return requestType;
    }

    public String getBody() {
        return body;
    }

    @Override
    public String toString() {
        return requestType +" " + body;
    }
}
