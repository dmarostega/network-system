package com.rockcodelabs.networksystem.modules.commands.dto.request;

import jakarta.validation.constraints.NotBlank;

public record RunCommandRequest(
    @NotBlank String action,
    @NotBlank String target
) {        
}
