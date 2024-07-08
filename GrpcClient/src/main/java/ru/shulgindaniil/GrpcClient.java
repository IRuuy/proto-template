package ru.shulgindaniil;


import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public abstract class GrpcClient {
    protected final ManagedChannel channel;

    public GrpcClient() {
        this.channel = ManagedChannelBuilder
                .forAddress("localhost", 8080)
                .usePlaintext()
                .build();
    }
}
