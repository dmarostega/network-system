#include <sstream>
#include <cstdlib>
#include <iostream>
#include <cstdio>  // Para FILE*, popen/pclose

// Compatibilidade multiplataforma
#ifdef _WIN32
    #define popen _popen
    #define pclose _pclose
#endif

void runCommand(const std::string& action, const std::string& target) {
    std::stringstream output;
    std::string cmd;

    // Monta o comando de ping dependendo do SO
#ifdef _WIN32
    if (action == "ping") {
        // Caminho completo do ping para evitar problemas WOW64
        cmd = "C:\\Windows\\System32\\ping.exe -n 4 " + target;
    }
#else
    if (action == "ping") {
        cmd = "ping -c 4 " + target;
    }
#endif
    else {
        std::cout << "Unknown action\n";
        return;
    }

    std::cout << "Command to execute: " << cmd << std::endl;

    // Abre o pipe para capturar a saída do ping
    FILE* pipe = popen(cmd.c_str(), "r");
    if (!pipe) {
        std::cout << "Failed to run command\n";
        return;
    }

    char buffer[256];
    while (fgets(buffer, sizeof(buffer), pipe) != nullptr) {
        std::cout << buffer;  // Tudo vai para stdout
    }

    pclose(pipe);
}
