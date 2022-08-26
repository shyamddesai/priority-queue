# priority-queue

This is a heap implementation of a priority queue for an emergency room of a hospital.

Patients who come to the ER are assessed (triage). We associate the urgency of the patient’s problem with a single value: the priority. We write a priority queue class that allows us to organize (name, prioririty) pairs using nodes which are stored in a heap. Each node in the heap is an instance of a Patient class which has two fields (name, priority).
The heap implementation will use an ArrayList of these Patients. 

The ERPriorityQueue class has two fields: an ArrayList of Patient objects called patients and a HashMap nameToIndex. The latter is defined as: the key is a string and the value is the index of that string in the ArrayList. This allows us to find a patient (given their name) in the priority queue.
The ERPriorityQueue class has many methods. Some of the methods
