package com.rockcodelabs.networksystem.modules.commands.dto.reponse;

public record RunCommandResponse(
    boolean ok,
    String action,
    String target,
    String output,
    int exitCode
) {    
}
