package src;

public class StudentBST {
    private StudentNode root;

    public StudentBST() {
        this.root = null;
    }

    public void insert(Student student) {
        this.root = insertRec(this.root, student);
    }

    private StudentNode insertRec(StudentNode root, Student student) {
        if (root == null) {
            root = new StudentNode(student);
            return root;
        }

        if (student.id < root.student.id) {
            root.left = insertRec(root.left, student);
        } else if (student.id > root.student.id) {
            root.right = insertRec(root.right, student);
        }

        return root;
    }

    public Student search(int id) {
        return searchRec(this.root, id);
    }

    private Student searchRec(StudentNode root, int id) {
        if (root == null || root.student.id == id) {
            return (root != null) ? root.student : null;
        }

        if (id < root.student.id) {
            return searchRec(root.left, id);
        }

        return searchRec(root.right, id);
    }

    public void remove(int id) {
        this.root = removeRec(this.root, id);
    }

    private StudentNode removeRec(StudentNode root, int id) {
        if (root == null) {
            return root;
        }

        if (id < root.student.id) {
            root.left = removeRec(root.left, id);
        } else if (id > root.student.id) {
            root.right = removeRec(root.right, id);
        } else {
            // node to be deleted found
            if (root.left == null) {
                return root.right;
            } else if (root.right == null) {
                return root.left;
            }

            // node with two children: Get the inorder successor (smallest in the right subtree)
            root.student = minValue(root.right);
            // Delete the inorder successor
            root.right = removeRec(root.right, root.student.id);
        }

        return root;
    }

    private Student minValue(StudentNode root) {
        Student minv = root.student;
        while (root.left != null) {
            minv = root.left.student;
            root = root.left;
        }
        return minv;
    }
}
