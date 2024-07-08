package ru.shulgindaniil;

import io.grpc.stub.StreamObserver;
import ru.shulgindaniil.message.Message;

public class MessageRequestStreamObserver implements StreamObserver<Message.MessageRequest> {
    private final StreamObserver<Message.MessageResponse> responseObserver;

    public MessageRequestStreamObserver(StreamObserver<Message.MessageResponse> responseObserver) {
        this.responseObserver = responseObserver;
    }

    @Override
    public void onNext(Message.MessageRequest messageRequest) {
        System.out.printf("Message request with id = %d; %n", messageRequest.getId());
    }

    @Override
    public void onError(Throwable throwable) {
        System.out.printf("Error: %s; %n", throwable.getMessage());
    }

    @Override
    public void onCompleted() {
        responseObserver.onNext(Message.MessageResponse.newBuilder().setId(1).setTitle("Response Title").build());
        responseObserver.onCompleted();
    }
}
