package com.rockcodelabs.networksystem.modules.commands.events;

public record CommandExecutedEvent (String action, String target, int exitCode) {}
