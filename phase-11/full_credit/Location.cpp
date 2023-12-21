#include "Location.h"

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
    os << loc.filename << " line " << loc.line;
    return os;
}
