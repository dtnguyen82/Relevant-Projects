#pragma once
#include <iostream>
#include <stack>
#include <string>

using namespace std;

class Tower
{
public:
	Tower();

	/*
	void move(stack<int>& currentPeg, stack<int>& nextPeg, int& counter);
	void hanoiStart(stack<int>& start, stack<int>& aux1, stack<int>& aux2, stack<int>& aux3, stack<int>& aux4, stack<int>& dest, int& counter);
	int H1(stack<int>& start, stack<int>& aux1, stack<int>& aux2, stack<int>& aux3, stack<int>& aux4, stack<int>& dest, int& counter);
	*/
	void H1(int source, int dest, int& counter);
	//hello
	void hanoiStart();

	void move(int currentPeg, int nextPeg, int& counter);

	void display();

	void H2(int source, int dest, int & counter);
private:

	stack<int> peg[5];
	int r;

	int pegCount = 4;

	int counter;
	int n;

}; 
