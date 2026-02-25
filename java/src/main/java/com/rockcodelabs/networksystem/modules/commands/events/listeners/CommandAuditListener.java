package com.rockcodelabs.networksystem.modules.commands.events.listeners;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.rockcodelabs.networksystem.modules.commands.events.CommandExecutedEvent;

@Component
public class CommandAuditListener {
    
    private static final Logger log = LoggerFactory.getLogger(CommandAuditListener.class);

    @EventListener
    public void onExecuted(CommandExecutedEvent e) {
        log.info("Command executed: action={}, target={}, exitCode={}", e.action(), e.target(), e.exitCode());
    }

}
