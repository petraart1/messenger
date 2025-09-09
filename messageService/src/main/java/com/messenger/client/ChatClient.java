package com.messenger.client;

import com.messenger.exception.MembershipCheckException;

public interface ChatClient {
    boolean checkMembership(Long chatId, Long userId) throws MembershipCheckException;
}
