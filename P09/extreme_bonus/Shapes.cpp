#include "Shape.h"
#include "Rectangle.h"
#include "Circle.h"
#include <iostream>
#include <vector>
#include <cstdlib>
#include <ctime>
#include <iomanip>
#include <fstream>

void createPPMRectangleImage(const std::string& filename, double width, double height) {
    int intWidth = static_cast<int>(width);
    int intHeight = static_cast<int>(height);

    std::ofstream ppmFile(filename);

    ppmFile << "P3\n";
    ppmFile << intWidth << " " << intHeight << "\n";
    ppmFile << "255\n";

    for (int y = 0; y < intHeight; y++) {
        for (int x = 0; x < intWidth; x++) {
            if (y == 0 || y == intHeight - 1 || x == 0 || x == intWidth - 1) {
                ppmFile << "255 0 0\n";
            } else {
                ppmFile << "255 255 255\n";
            }
        }
    }

    ppmFile.close();
}

int main() {
srand(time(NULL));

double length, width, radius;

length = static_cast<double>(rand() % 15 + 1);
width = static_cast<double>(rand() % 15 + 1);
Rectangle* rectangle = new Rectangle(length, width);

radius = static_cast<double>(rand() % 5 + 1);
Circle* circle = new Circle(radius);

std::vector<Shape*> shapes;
shapes.push_back(rectangle);
shapes.push_back(circle);

for (const Shape* shape : shapes) {
std::cout << std::fixed << std::setprecision(6);
std::cout << shape->to_string() << std::endl;
}

createPPMRectangleImage("rectangle.ppm", length, width);
system("open rectangle.ppm");


return 0;
}














