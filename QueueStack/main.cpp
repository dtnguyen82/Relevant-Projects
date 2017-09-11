#include <iostream>
#include <string>
#include "queue.h"
#include "Stack.h"
#include <list>
using namespace std;
string addition(Queue<int>* num1, Stack<int>* num2) {
	string result;
	list<int> number1;
	list<int> number2;
	while ((!num1->isEmpty()) && (!num2->isEmpty())) { 
		number1.push_back(num1->front()); //Store the numbers into lists
		number2.push_back(num2->top());
		num1->pop();
		num2->pop();
	}//Using lists to do calculation instead of converting to int to prevent
	//errors since integer cannot handle a large number
	while (!num1->isEmpty()) { //Empty out the remaining numbers
		number1.push_back(num1->front());
		num1->pop();
	}
	while (!num2->isEmpty()) {
		number2.push_back(num2->top());
		num2->pop();
	}
	int carryover = 0;
	int holder;
	while ((number1.size() != 0) || (!number2.size() != 0)) {
		holder = number1.back() + number2.back() + carryover; //Basic math with carryovers
		carryover = holder / 10;
		holder = holder % 10;
		result += to_string(holder); //Convert to string and add to result
		number1.pop_back();
		number2.pop_back(); //Keep popping each time to traverse through the list
		if (number1.empty())
			break;
		if (number2.empty())
			break;
	}
	string temp;
	while (number1.size() != 0) { //The purpose of the last 2 while loop is for the case
		temp += to_string(number1.front()); //If one number has more digits than the other, 
		number1.pop_front(); //Add the remaining numbers into the result if they weren't use to add
	}
	while (number2.size() != 0) {
		temp += to_string(number2.front());
		number2.pop_front();
	}
	
	temp += result;
	result = temp;
	if (carryover != 0) { //Left over 
		temp = to_string(carryover);
		temp += result;
		result = temp;
	}
	return result;
}
void main() {
	string queuenumber;
	string stacknumber;
	cout << "Enter queue numbers: "; //Get the input from user
	cin >> queuenumber;
	cout << "Enter stack numbers: ";
	cin >> stacknumber;
	Queue<int> *num1 = new Queue<int>;
	Stack<int> *num2 = new Stack<int>;
	for (int i = 0; i < queuenumber.length(); i++) { //Store the input into queue
		int temp = (queuenumber[i]) - '0';
		num1->push(temp);
	}
	for (int i = 0; i < stacknumber.length(); i++) { //Store the input into stack
		int temp = (stacknumber[i]) - '0';
		num2->push(temp);
	}
	string result = addition(num1, num2);
	cout << result;
	system("pause"); //If you're using linux, please remove this
}