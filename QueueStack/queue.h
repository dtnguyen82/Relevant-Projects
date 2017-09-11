#ifndef QUEUE_H
#define QUEUE_H
#include <iostream>
#include <string>
#include <iomanip>
#include <assert.h>
using namespace std;
template <class T>
struct node {
	T data;
	node<T>* next;
};
template <class T>
class Queue {
public:
	Queue();   // default construct
	~Queue();  // deconstruct
	bool isEmpty() const;
	bool isFull() const;
	void initialize();
	T front() const;
	T back() const;
	void push(const T& element);
	void pop();
	void destroyList();
public:
	node<T> *queueFront;
	node<T> *queueRear;
};
template <class T>
Queue<T>::Queue()
{
	queueFront = NULL;
	queueRear = NULL;
}
template <class T>
Queue<T>::~Queue() {
	destroyList();
}
template <class T>
void Queue<T>::initialize() {
	node<T> * temp;
	while (queueFront != NULL) {
		temp = queueFront;
		queueFront = queueFront->next;
		delete temp;
	}
	queueRear = NULL;
}

template <class T>
void Queue<T>::destroyList() {
	node<T> * temp;
	while (queueFront != NULL) {
		temp = queueFront;
		queueFront = queueFront->next;
		delete temp;
	}
	queueRear = NULL;
}

template <class T>
bool Queue<T>::isEmpty() const {
	return (queueFront == NULL);
}
template <class T>
bool Queue<T>::isFull() const {
	return false;
}
template <class T>
T Queue<T>::front() const{
	assert(queueFront != NULL);
	return queueFront->data;
}
template <class T>
T Queue<T>::back() const {
	assert(queueRear != NULL);
	return queueRear->data;
}
template <class T>
void Queue<T>::push(const T& op) {
	node<T> *newNode = new node<T>;
	newNode->data = op;
	newNode->next = NULL;
	if (queueFront == NULL) {
		queueFront = newNode;
		queueRear = newNode;
	}
	else {
		queueRear->next = newNode;
		queueRear = queueRear->next;
	}
}
template <class T>
void Queue<T>::pop() {
	node<T> *temp;
	if (!isEmpty()) {
		temp = queueFront;
		queueFront = queueFront->next;
		delete temp;
		if (queueFront == NULL) {
			queueRear = NULL;
		}
	}
	else
		return;
}

#endif