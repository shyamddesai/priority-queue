# Priority Queue for Emergency Room
This project implements a priority queue using a heap data structure for managing patients in an emergency room (ER). Each patient is assigned a priority value based on the urgency of their condition, allowing the ER to handle the most critical cases first. This priority queue is implemented in Java and utilizes an ArrayList and a HashMap for efficient organization and retrieval.

## Features
1. **Patient Management**: Each patient is represented as an instance of the `Patient` class with two fields:
   - `name`: The name of the patient.
   - `priority`: The urgency level of the patient's condition.

2. **Heap-Based Priority Queue**: The ER priority queue is implemented as a min-heap, where the patient with the highest priority (lowest numeric value) is served first. The queue maintains an ArrayList of `Patient` objects for heap operations.

3. **Efficient Retrieval**: The `ERPriorityQueue` class includes a HashMap called `nameToIndex` for quick access. This map stores the index of each patient in the heap based on their name, making it easy to locate and update patients.

---

## Project Structure
- `Patient.java`: Defines the `Patient` class with `name` and `priority` fields.
- `ERPriorityQueue.java`: Defines the `ERPriorityQueue` class, implementing the priority queue functionality.
- `Main.java`: Contains example code to demonstrate the usage of the ER priority queue.

## Classes and Structure
### `Patient` Class
The `Patient` class encapsulates the details of each patient:
- **Fields**:
  - `name`: The patient's name.
  - `priority`: An integer representing the urgency of the patient’s condition.

### `ERPriorityQueue` Class
The `ERPriorityQueue` class manages the heap and provides methods for common priority queue operations:
- **Fields**:
  - `patients`: An ArrayList storing `Patient` objects, which serves as the heap.
  - `nameToIndex`: A HashMap mapping patient names to their indices in the `patients` ArrayList, facilitating efficient lookups and updates.

- **Key Methods**:
  - `addPatient(name, priority)`: Adds a new patient with a specified priority to the queue.
  - `getNextPatient()`: Retrieves and removes the patient with the highest priority from the queue.
  - `changePriority(name, newPriority)`: Updates the priority of an existing patient.
  - `removePatient(name)`: Removes a specific patient from the queue by name.
  - `peek()`: Returns the patient with the highest priority without removing them.
  - `isEmpty()`: Checks if the queue is empty.

## Example Usage
```java
ERPriorityQueue queue = new ERPriorityQueue();

// Adding patients
queue.addPatient("Alice", 5);
queue.addPatient("Bob", 2);
queue.addPatient("Charlie", 7);

// Retrieving the highest priority patient
Patient next = queue.getNextPatient(); // Bob is served first due to priority 2

// Changing priority
queue.changePriority("Alice", 1); // Alice's priority is updated to 1, making her next in line

// Removing a patient
queue.removePatient("Charlie"); // Charlie is removed from the queue
```

## Data Structures
- **ArrayList**: Stores `Patient` objects, representing the heap structure.
- **HashMap**: Maps patient names to their indices in the ArrayList, enabling efficient patient retrieval and priority updates.

---

## Setup
1. **Compile the Java Files**:
   Compile all Java files in the directory.
   ```bash
   javac *.java
   ```

2. **Run the Program**:
   Run the main program to see the priority queue in action.
   ```bash
   java Main
   ```
