package com.rockcodelabs.networksystem.modules.commands.application;

import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeUnit;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import com.rockcodelabs.networksystem.modules.commands.domain.exception.CommandExecutionException;
import com.rockcodelabs.networksystem.modules.commands.dto.reponse.PingResponse;
import com.rockcodelabs.networksystem.modules.commands.dto.reponse.RunCommandResponse;
import com.rockcodelabs.networksystem.modules.commands.dto.request.RunCommandRequest;
import com.rockcodelabs.networksystem.modules.commands.events.CommandExecutedEvent;

@Service
public class CommandService {

    private final ApplicationEventPublisher events;

    public CommandService(ApplicationEventPublisher events) {
        this.events = events;
    }

    public PingResponse ping() {
        return new PingResponse(true, "salve");
    }

    public RunCommandResponse run(RunCommandRequest req) {

        String cmd = switch (req.action().toLowerCase()) {
            case "ping" -> "ping -n 4 " + req.target();
            default -> throw new CommandExecutionException("Ação não suportada! " + req.action());
        };

        ExecResult result = exec(cmd);

        RunCommandResponse response = new RunCommandResponse(
            result.exitCode == 0,
            req.action(),
            req.target(),
            result.output,
            result.exitCode
        );

        events.publishEvent(new CommandExecutedEvent(req.action(), req.target(), result.exitCode));

        return response;
    }

    private ExecResult exec(String command) {
        try {
                                                                            // "chcp 65001>nul & " para forçar cmd sair UTF8
            ProcessBuilder pb = new ProcessBuilder("cmd.exe", "/c",  "chcp 65001>nul & " + command);
            pb.redirectErrorStream(true);

            Process p = pb.start();

            boolean finished = p.waitFor(15, TimeUnit.SECONDS);
            if(!finished) {
                p.destroyForcibly();
                throw new CommandExecutionException("Timeout executando comando");
            }

            byte[] bytes = p.getInputStream().readAllBytes();
            String out = new String(bytes, StandardCharsets.UTF_8);

            return new ExecResult(p.exitValue(), out);
                        
        }catch(CommandExecutionException e) {
            throw e;
        }catch(Exception e) {
            throw new CommandExecutionException("Falha executando comando", e);
        }
    }

    private static class ExecResult {
        final int exitCode;
        final String output;

        ExecResult(int exitCode, String output) {
            this.exitCode = exitCode;
            this.output = output;
        }
    }
}
