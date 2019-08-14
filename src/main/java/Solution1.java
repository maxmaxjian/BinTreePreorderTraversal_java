import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Solution1 implements Solution {
    @Override
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        TreeNode curr = root;
        while (curr != null) {
            result.add(curr.val);
            curr = getPreorderNext(root, curr);
        }
        return result;
    }

    private TreeNode getPreorderNext(TreeNode root, TreeNode curr) {
        TreeNode next;
        if (curr.left != null) {
            next = curr.left;
        } else if (curr.right != null) {
            next = curr.right;
        } else {
            TreeNode parent = getParent(root, curr);
            if (curr == parent.left) {
                while (parent != null && curr == parent.left && parent.right == null) {
                    curr = parent;
                    parent = getParent(root, curr);
                }
            }
        }
        return next;
    }

    private TreeNode getParent(TreeNode root, TreeNode curr) {
        if (curr == root) {
            return null;
        } else {
            Stack<TreeNode> stack = new Stack<>();
            stack.push(root);
            while (!stack.isEmpty()) {
                TreeNode node = stack.pop();
                if (node.left == curr || node.right == curr) {
                    return node;
                } else {
                    if (node.left != null) {
                        stack.push(node.left);
                    }
                    if (node.right != null) {
                        stack.push(node.right);
                    }
                }
            }
            return null;
        }
    }
}
