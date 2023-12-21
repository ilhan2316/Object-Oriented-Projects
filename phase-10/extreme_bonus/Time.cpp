#include "Time.h"

void Time::rationalize() {
    _totalSeconds = (_totalSeconds + 86400) % 86400;
}

Time::Time(int hour, int minute, int second) : _totalSeconds(hour * 3600 + minute * 60 + second) {
    rationalize();
}

Time::Time() : Time(0, 0, 0) {
}

Time::Time(int hour) : _totalSeconds(hour * 3600) {
    rationalize();
}

int& Time::operator[](int index) {
    if (index < 0 || index > 2) {
        throw std::out_of_range("Index out of range");
    }

    static int value;
    if (index == 0) {
        value = _totalSeconds / 3600;
    } else if (index == 1) {
        value = (_totalSeconds % 3600) / 60;
    } else {
        value = _totalSeconds % 60;
    }

    return value;
}

const int& Time::operator[](int index) const {
    if (index < 0 || index > 2) {
        throw std::out_of_range("Index out of range");
    }

    static int value;
    if (index == 0) {
        value = _totalSeconds / 3600;
    } else if (index == 1) {
        value = (_totalSeconds % 3600) / 60;
    } else {
        value = _totalSeconds % 60;
    }

    return value;
}

Time Time::operator+(const Time& other) const {
    Time result;
    result._totalSeconds = _totalSeconds + other._totalSeconds;
    result.rationalize();
    return result;
}

Time Time::operator+(int seconds) const {
    Time result;
    result._totalSeconds = _totalSeconds + seconds;
    result.rationalize();
    return result;
}

Time& Time::operator++() {
    ++_totalSeconds;
    rationalize();
    return *this;
}

Time Time::operator++(int) {
    Time temp = *this;
    ++(*this);
    return temp;
}

bool Time::operator==(const Time& other) const {
    return _totalSeconds == other._totalSeconds;
}

bool Time::operator!=(const Time& other) const {
    return !(*this == other);
}

bool Time::operator<(const Time& other) const {
    return _totalSeconds < other._totalSeconds;
}

bool Time::operator>(const Time& other) const {
    return _totalSeconds > other._totalSeconds;
}

bool Time::operator<=(const Time& other) const {
    return !(*this > other);
}

bool Time::operator>=(const Time& other) const {
    return !(*this < other);
}

std::ostream& operator<<(std::ostream& os, const Time& time) {
    int hour = time._totalSeconds / 3600;
    int minute = (time._totalSeconds % 3600) / 60;
    int second = time._totalSeconds % 60;

    return os << std::setfill('0') << std::setw(2) << hour << ":"
              << std::setw(2) << minute << ":" << std::setw(2) << second;
}

std::istream& operator>>(std::istream& is, Time& time) {
    int hour, minute, second;
    char colon;

    is >> hour >> colon >> minute >> colon >> second;
    if (is.fail() || colon != ':') {
        is.clear(std::ios::failbit);
    } else {
        time._totalSeconds = hour * 3600 + minute * 60 + second;
        time.rationalize();
    }
    return is;
}

Time operator+(int seconds, const Time& time) {
    return time + seconds;
}





