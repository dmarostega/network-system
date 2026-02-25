package com.rockcodelabs.networksystem.modules.commands.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rockcodelabs.networksystem.modules.commands.application.CommandService;
import com.rockcodelabs.networksystem.modules.commands.dto.reponse.PingResponse;
import com.rockcodelabs.networksystem.modules.commands.dto.reponse.RunCommandResponse;
import com.rockcodelabs.networksystem.modules.commands.dto.request.RunCommandRequest;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api")
public class CommandController {
    private final CommandService service;

    public CommandController(CommandService service) {
        this.service = service;
    }

    @GetMapping("/ping")
    public PingResponse ping() {
        return service.ping();
    }

    @PostMapping("/commands/run")
    public RunCommandResponse run(@Valid @RequestBody RunCommandRequest req) {
        //TODO: process POST request
        
        return service.run(req);
    }
    
}
