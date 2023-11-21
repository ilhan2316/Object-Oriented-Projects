#ifndef LOCATION_H
#define LOCATION_H

#include <iostream>
#include <string>

class Location {
private:
    std::string filename;
    int line;

public:
    Location(const std::string& _filename, int _line);

    bool operator==(const Location& other) const;
    bool operator!=(const Location& other) const;
    bool operator<(const Location& other) const;
    bool operator<=(const Location& other) const;
    bool operator>(const Location& other) const;
    bool operator>=(const Location& other) const;

    friend std::ostream& operator<<(std::ostream& os, const Location& loc);
};

#endif 
