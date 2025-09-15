package com.messenger.client.grpc;

import com.messenger.client.ChatClient;
import com.messenger.exception.MembershipCheckException;
import org.aspectj.weaver.patterns.IToken;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("grpc")
public class GrpcChatClient implements ChatClient {

    //private final ChatServiceGrpc.ChatServiceBlockingStub stub;

    /*public GrpcChatMembershipClient(ManagedChannel channel) {
        this.stub = ChatServiceGrpc.newBlockingStub(channel);
    }*/

    @Override
    public boolean checkMembership(Long chatId, Long userId, String token) throws MembershipCheckException {
        // Implementation
        return false;
    }
}
