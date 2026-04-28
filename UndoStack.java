package src;

class UndoNode {
    String action;
    UndoNode next;

    public UndoNode(String action) {
        this.action = action;
        this.next = null;
    }
}

public class UndoStack {
    private UndoNode top;

    public UndoStack() {
        this.top = null;
    }

    public void push(String action) {
        UndoNode newNode = new UndoNode(action);
        newNode.next = top;
        top = newNode;
    }

    public String pop() {
        if (isEmpty()) {
            return null;
        }
        String action = top.action;
        top = top.next;
        return action;
    }

    public boolean isEmpty() {
        return top == null;
    }
}
