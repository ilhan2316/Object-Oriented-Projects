#include "Location.h"

std::string Location::last_filename = "";

Location::Location(const std::string& _filename, int _line) : filename(_filename), line(_line) {}

bool Location::operator==(const Location& other) const {
    return (filename == other.filename) && (line == other.line);
}

bool Location::operator!=(const Location& other) const {
    return !(*this == other);
}

bool Location::operator<(const Location& other) const {
    return (filename < other.filename) || ((filename == other.filename) && (line < other.line));
}

bool Location::operator<=(const Location& other) const {
    return (*this < other) || (*this == other);
}

bool Location::operator>(const Location& other) const {
    return !(*this <= other);
}

bool Location::operator>=(const Location& other) const {
    return !(*this < other);
}

std::ostream& operator<<(std::ostream& os, const Location& loc) {
    if (loc.filename != Location::last_filename) {
        os << loc.filename << " line ";
    }

    os << loc.line;

    Location::last_filename = loc.filename;

    return os;
}

void Location::next_word() {
    last_filename = "";
}

