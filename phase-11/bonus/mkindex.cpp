#include <iostream>
#include <fstream>
#include <sstream>
#include <cctype>
#include <locale>

#include "Index.h" 

void remove_punctuation(std::string& word) {
    while (!word.empty() && !std::isalnum(word.front())) {
        word.erase(0, 1);
    }

    while (!word.empty() && !std::isalnum(word.back())) {
        word.pop_back();
    }
}

void to_lowercase(std::string& word) {
    std::locale loc;
    for (auto& c : word) {
        c = std::tolower(c, loc);
    }
}

void add_words_to_index(const std::string& filename, Index& index) {
    std::ifstream file(filename);

    if (!file.is_open()) {
        std::cerr << "Error opening file: " << filename << std::endl;
        return;
    }

    std::string line;
    int line_number = 1;

    while (std::getline(file, line)) {
        std::istringstream iss(line);
        std::string word;

        while (iss >> word) {
            remove_punctuation(word);

            
            to_lowercase(word);

           
            Location location(filename, line_number);

            
            index.add_word(word, location);
        }

        line_number++;
    }

    file.close();
}

int main(int argc, char* argv[]) {
    if (argc < 2) {
        std::cerr << "Usage: " << argv[0] << " <filename1> <filename2> ..." << std::endl;
        return 1;
    }

    Index index;

    for (int i = 1; i < argc; ++i) {
        add_words_to_index(argv[i], index);
    }

    std::cout << "Index" << std::endl;
    std::cout << "=====" << std::endl;
    std::cout << index << std::endl;

    return 0;
}