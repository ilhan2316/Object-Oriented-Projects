#include "Time.h"

void Time::rationalize() {
    int totalSeconds = _hour * 3600 + _minute * 60 + _second;
    totalSeconds = (totalSeconds + 86400) % 86400; 
    _hour = totalSeconds / 3600;
    _minute = (totalSeconds % 3600) / 60;
    _second = totalSeconds % 60;
}

Time::Time(int hour, int minute, int second) : _hour(hour), _minute(minute), _second(second) {
    rationalize();
}

Time::Time() : _hour(0), _minute(0), _second(0) {}

Time Time::operator+(const Time& other) const {
    Time result(_hour + other._hour, _minute + other._minute, _second + other._second);
    result.rationalize();
    return result;
}

Time& Time::operator++() {
    ++_second;
    rationalize();
    return *this;
}

Time Time::operator++(int) {
    Time temp = *this;
    ++(*this);
    return temp;
}

bool Time::operator==(const Time& other) const {
    return (_hour == other._hour) && (_minute == other._minute) && (_second == other._second);
}

bool Time::operator!=(const Time& other) const {
    return !(*this == other);
}

bool Time::operator<(const Time& other) const {
    if (_hour != other._hour)
        return _hour < other._hour;
    if (_minute != other._minute)
        return _minute < other._minute;
    return _second < other._second;
}

bool Time::operator>(const Time& other) const {
    return other < *this;
}

bool Time::operator<=(const Time& other) const {
    return !(*this > other);
}

bool Time::operator>=(const Time& other) const {
    return !(*this < other);
}


std::ostream& operator<<(std::ostream& os, const Time& time) {
    return os << std::setfill('0') << std::setw(2) << time._hour << ":"
              << std::setw(2) << time._minute << ":" << std::setw(2) << time._second;
}


std::istream& operator>>(std::istream& is, Time& time) {
    char colon;
    is >> time._hour >> colon >> time._minute >> colon >> time._second;
    if (is.fail() || colon != ':') {
        is.clear(std::ios::failbit);
    } else {
        time.rationalize();
    }
    return is;
}
