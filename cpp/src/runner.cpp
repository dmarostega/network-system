#include <cstdlib>
#include <string>
#include <iostream>

void runCommand(const std::string& action, const std::string& target) {
    std::string cmd;

#ifdef _WIN32
    if (action == "ping") {
        cmd = "ping -n 4 " + target;
    }
#else
    if (action == "ping") {
        cmd = "ping -c 4 " + target;
    }
#endif
    else {
        std::cerr << "Unknown action\n";
        return;
    }

    std::system(cmd.c_str());
}
