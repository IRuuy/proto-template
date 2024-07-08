package ru.shulgindaniil;

import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;

public class GrpcServer {
    public static void main(String[] args) throws IOException, InterruptedException {
        Server grpcServer = ServerBuilder.forPort(8080)
                .addService(new MessageServiceImpl())
                .build();
        grpcServer.start();
        grpcServer.awaitTermination();
    }
}
