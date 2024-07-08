package ru.shulgindaniil;

import io.grpc.stub.StreamObserver;
import ru.shulgindaniil.message.Message;

public class MessageResponseStreamObserver implements StreamObserver<Message.MessageResponse> {
    @Override
    public void onNext(Message.MessageResponse messageResponse) {
        System.out.printf("Message = [id = %d, title = %s];%n",
                messageResponse.getId(), messageResponse.getTitle());
    }

    @Override
    public void onError(Throwable throwable) {
        System.out.printf("Error: %s;", throwable.getMessage());
    }

    @Override
    public void onCompleted() {

    }
}
