#include <iostream>
#include <string>

void runCommand(const std::string& action, const std::string& target);

int main(int argc, char* argv[]) {
    if (argc < 3) {
        std::cerr << "Usage: runner <action> <target>\n";
        return 1;
    }

    std::string action = argv[1];
    std::string target = argv[2];

    std::cout << "Runner started\n";
    std::cout << "Action: " << action << " Target: " << target << "\n";

    runCommand(action, target);

    std::cout << "Finally main\n";
    return 0;
}
