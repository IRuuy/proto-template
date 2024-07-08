package ru.shulgindaniil;

import io.grpc.stub.StreamObserver;
import ru.shulgindaniil.message.Message;
import ru.shulgindaniil.message.MessageServiceGrpc;

public class GrpcClientStreamSender extends GrpcClient {
    public static void main(String[] args) throws InterruptedException {
        new GrpcClientStreamSender().send();
    }

    private void send() throws InterruptedException {
        MessageServiceGrpc.MessageServiceStub messageServiceStub
                = MessageServiceGrpc.newStub(channel);

        StreamObserver<Message.MessageRequest> messageClientStream =
                messageServiceStub.getMessageClientStream(new MessageResponseStreamObserver());

        for(int i = 0; i < 5; i++) {
            messageClientStream.onNext(Message.MessageRequest.newBuilder().setId(i).build());
        }

        messageClientStream.onCompleted();
        Thread.sleep(1000);
    }
}
