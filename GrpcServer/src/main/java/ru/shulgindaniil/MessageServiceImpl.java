package ru.shulgindaniil;

import io.grpc.stub.StreamObserver;
import ru.shulgindaniil.message.Message;
import ru.shulgindaniil.message.MessageServiceGrpc;

public class MessageServiceImpl extends MessageServiceGrpc.MessageServiceImplBase {
    @Override
    public void getMessageUnary(Message.MessageRequest request, StreamObserver<Message.MessageResponse> responseObserver) {
        responseObserver.onNext(Message.MessageResponse.newBuilder()
                .setId(request.getId())
                .setTitle("Stub message")
                .build());
        responseObserver.onCompleted();
    }

    @Override
    public StreamObserver<Message.MessageRequest> getMessageClientStream(StreamObserver<Message.MessageResponse> responseObserver) {
        return new MessageRequestStreamObserver(responseObserver);
    }

    @Override
    public void getMessageServerStream(Message.MessageRequest request, StreamObserver<Message.MessageResponse> responseObserver) {
        for(int i = 0; i < 5; i++){
            responseObserver.onNext(Message.MessageResponse.newBuilder()
                    .setId(request.getId())
                    .setTitle(String.format("Stub message part - %d", i))
                    .build()
            );
        }
        responseObserver.onCompleted();
    }
}
