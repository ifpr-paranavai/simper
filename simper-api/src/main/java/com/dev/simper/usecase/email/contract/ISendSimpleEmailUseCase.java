package com.dev.simper.usecase.email.contract;

public interface ISendSimpleEmailUseCase {
    public void execute(String to, String subject, String text);
}
