package src;

public class WaitlistQueue {
    private QNode front, rear;

    public WaitlistQueue() {
        this.front = this.rear = null;
    }

    // Method to add a student to the queue.
    public void enqueue(Student student) {
        QNode newNode = new QNode(student);

        // If queue is empty, then new node is front and rear both
        if (this.rear == null) {
            this.front = this.rear = newNode;
            return;
        }

        // Add the new node at the end of queue and change rear
        this.rear.next = newNode;
        this.rear = newNode;
    }

    // Method to remove a student from queue.
    public Student dequeue() {
        // If queue is empty, return NULL.
        if (this.front == null) {
            return null;
        }

        // Store previous front and move front one node ahead
        QNode temp = this.front;
        this.front = this.front.next;

        // If front becomes NULL, then change rear also as NULL
        if (this.front == null) {
            this.rear = null;
        }
        return temp.student;
    }

    public boolean isEmpty() {
        return this.front == null;
    }
}
