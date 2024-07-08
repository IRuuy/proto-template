package ru.shulgindaniil;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;
import ru.shulgindaniil.message.Message;
import ru.shulgindaniil.message.MessageServiceGrpc;

import java.util.Iterator;

public class GrpcServerStreamSender extends GrpcClient {
    public static void main(String[] args) throws InterruptedException {
        GrpcServerStreamSender grpcServerStreamSender = new GrpcServerStreamSender();

        grpcServerStreamSender.send();
        grpcServerStreamSender.sendAsync();
        Thread.sleep(1000);
    }

    private void sendAsync() {
        MessageServiceGrpc.MessageServiceStub messageServiceStub =
                MessageServiceGrpc.newStub(channel);

        Message.MessageRequest messageRequest
                = Message.MessageRequest.newBuilder().setId(1).build();

        messageServiceStub.getMessageServerStream(messageRequest, new MessageResponseStreamObserver());
    }

    private void send() {
        ManagedChannel channel = ManagedChannelBuilder
                .forAddress("localhost", 8080)
                .usePlaintext()
                .build();

        MessageServiceGrpc.MessageServiceBlockingStub messageServiceStub
                = MessageServiceGrpc.newBlockingStub(channel);

        Iterator<Message.MessageResponse> messageServerStream =
                messageServiceStub.getMessageServerStream(Message.MessageRequest.newBuilder().setId(1).build());

        messageServerStream.forEachRemaining((messageResponse) -> {
            System.out.printf("Message = [id = %d, title = %s];%n", messageResponse.getId(), messageResponse.getTitle());
        });
    }
}
