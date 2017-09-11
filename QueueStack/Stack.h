#ifndef STACK_H
#define STACK_H
#include <iostream>
#include <string>
#include <iomanip>
using namespace std;
template <class T>
struct Node {
	T data;
	Node<T>* next;
};
template <class T>
class Stack {
public:
	Stack();   // default construct
	~Stack();  // deconstruct
	Node<T>* GetHead() const;
	void push(T op);
	void pop();
	T top();
	bool isEmpty() const;
	void destroyList();
	void Print() const;
public:
	Node<T>* head;
	int count = 0;
};
template <class T>
Stack<T>::Stack()
{
	head = NULL;
	count = 0;
}
template <class T>
Stack<T>::~Stack() {
	destroyList();
}
template <class T>
void Stack<T>::destroyList() {
	Node<T> * temp;
	while (head != NULL) {
		temp = head;
		head = head->next;
		delete temp;
	}
	count = 0;
}
template <class T>
Node<T>* Stack<T>::GetHead()const {
	return head;
}
template <class T>
bool Stack<T>::isEmpty() const {
	return (head == NULL);
}
template <class T>
T Stack<T>::top() {
	return head->data;
}
template <class T>
void Stack<T>::push(T op) {
	Node<T>* newNode = new Node<T>();
	if (head == NULL) {
		head = newNode;
		newNode->data = op;
		newNode->next = NULL;
	}
	else {
		newNode->data = op;
		newNode->next = head;
		head = newNode;
	}
	count++;
}
template <class T>
void Stack<T>::pop() {
	Node<T> *temp = new Node<T>();
	if (head != NULL) {
		temp = head;
		head = head->next;
		delete temp;
		count--;
	}
	else
		return;
}
template <class T>
void Stack<T>::Print() const {
	Node<T> *current;
	current = head;
	if (current == NULL) {
		return;
	}
	while (current != NULL) {
		if (current == head) { //print the head first
			cout << current->data;
		}
		else
			cout << setfill('0') << current->data; //add 0 infront of numbers until it have the digitspernode size
		current = current->next;
	}
	cout << endl;
}
#endif
