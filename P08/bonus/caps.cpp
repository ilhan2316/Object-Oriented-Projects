#include <iostream>
#include <vector>
#include <string>

int main(int argc, char* argv[]) {
    std::vector<std::string> caps;
    std::vector<std::string> no_caps;

    if (argc < 2) {
        std::cerr << "Usage: " << argv[0] << " <input text>" << std::endl;
        return 1;
    }

    for (int i = 1; i < argc; i++) {
        std::string word = argv[i];
        if (!word.empty() && std::isupper(word[0])) {
            caps.push_back(word);
        } else {
            no_caps.push_back(word);
        }
    }

    std::cout << "Capitalized:" << std::endl;
    for (const std::string& word : caps) {
        std::cout << word << std::endl;
    }

    std::cout << "\nLower Case:" << std::endl;
    for (const std::string& word : no_caps) {
        std::cout << word << std::endl;
    }

    return 0;
}



