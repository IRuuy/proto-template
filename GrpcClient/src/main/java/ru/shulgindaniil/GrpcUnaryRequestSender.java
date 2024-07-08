package ru.shulgindaniil;

import ru.shulgindaniil.message.Message;
import ru.shulgindaniil.message.MessageServiceGrpc;

public class GrpcUnaryRequestSender extends GrpcClient {
    public static void main(String[] args) {
        new GrpcUnaryRequestSender().sendUnaryGrpcRequest();
    }

    private void sendUnaryGrpcRequest() {
        MessageServiceGrpc.MessageServiceBlockingStub messageServiceStub
                = MessageServiceGrpc.newBlockingStub(channel);

        Message.MessageResponse message
                = messageServiceStub.getMessageUnary(Message.MessageRequest.newBuilder().setId(0).build());

        System.out.printf("Message = [id = %d, title = %s];%n", message.getId(), message.getTitle());
    }
}
