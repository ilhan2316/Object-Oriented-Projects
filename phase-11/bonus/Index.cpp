#include "Index.h"

void Index::add_word(const Word& word, const Location& location) {
    auto it = _index.find(word);

    if (it == _index.end()) {
        _index[word] = Locations();
    }

    _index[word].insert(location);
}

std::ostream& operator<<(std::ostream& os, const Index& index) {
    for (const auto& entry : index._index) {
        Location::next_word();

        os << entry.first << ": ";

        for (const auto& location : entry.second) {
            os << location << ", ";
        }

        os << std::endl;
    }

    return os;
}

