#include "Circle.h"

Circle::Circle(double radius) : radius(radius) {}

std::string Circle::name() const {
    return "Circle of radius " + std::to_string(radius);
}

double Circle::area() const {
    return 3.14159265359 * radius * radius;
}