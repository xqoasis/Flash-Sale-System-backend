package com.skillup.application.order.mesageQueue;

public interface MyRepo {
    public void sendMessage(String topic, String body);
}
