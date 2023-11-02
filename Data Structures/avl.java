
class AVL extends BST { // an example of Java inheritance
  public AVL() { root = null; }

  private int h(BSTVertex T) { return T == null ? -1 : T.height; }

  protected BSTVertex rotateLeft(BSTVertex T) {
    // T must have a right child

    BSTVertex w = T.right;
    w.parent = T.parent;
    T.parent = w;
    T.right = w.left;
    if (w.left != null) w.left.parent = T;
    w.left = T;

    T.height = Math.max(h(T.left), h(T.right)) + 1;
    w.height = Math.max(h(w.left), h(w.right)) + 1;

    return w;
  }

  protected BSTVertex rotateRight(BSTVertex T) {
    // T must have a left child

    BSTVertex w = T.left;
    w.parent = T.parent;
    T.parent = w;
    T.left = w.right;
    if (w.right != null) w.right.parent = T;
    w.right = T;

    T.height = Math.max(h(T.left), h(T.right)) + 1;
    w.height = Math.max(h(w.left), h(w.right)) + 1;

    return w;
  }

  @Override
  protected BSTVertex insert(BSTVertex T, int v) {
    if (T == null) return new BSTVertex(v);          // insertion point is found

    if (T.key < v) {                                      // search to the right
      T.right = insert(T.right, v);
      T.right.parent = T;
    }
    else {                                                 // search to the left
      T.left = insert(T.left, v);
      T.left.parent = T;
    }

    int balance = h(T.left) - h(T.right);
    if (balance == 2) { // left heavy
      int balance2 = h(T.left.left) - h(T.left.right);
      if (balance2 == 1) {
        T = rotateRight(T);
      }
      else { // -1
        T.left = rotateLeft(T.left);
        T = rotateRight(T);
      }
    }
    else if (balance == -2) { // right heavy
      int balance2 = h(T.right.left) - h(T.right.right);
      if (balance2 == -1)
        T = rotateLeft(T);
      else { // 1
        T.right = rotateRight(T.right);
        T = rotateLeft(T);
      }
    }

    T.height = Math.max(h(T.left), h(T.right)) + 1;
    return T;                                          // return the updated AVL
  }

  @Override
  protected BSTVertex delete(BSTVertex T, int v) {
    if (T == null)  return T;              // cannot find the item to be deleted

    if (T.key == v) {                          // this is the node to be deleted
      if (T.left == null && T.right == null)                   // this is a leaf
        T = null;                                      // simply erase this node
      else if (T.left == null && T.right != null) {   // only one child at right
        BSTVertex temp = T;
        T.right.parent = T.parent;
        T = T.right;                                                 // bypass T
        temp = null;
      }
      else if (T.left != null && T.right == null) {    // only one child at left
        BSTVertex temp = T;
        T.left.parent = T.parent;
        T = T.left;                                                  // bypass T
        temp = null;
      }
      else {                                 // has two children, find successor
        int successorV = successor(v);
        T.key = successorV;         // replace this key with the successor's key
        T.right = delete(T.right, successorV);      // delete the old successorV
      }
    }
    else if (T.key < v)                                   // search to the right
      T.right = delete(T.right, v);
    else                                                   // search to the left
      T.left = delete(T.left, v);

    if (T != null) {               // similar as insertion code except this line
      int balance = h(T.left) - h(T.right);
      if (balance == 2) { // left heavy
        int balance2 = h(T.left.left) - h(T.left.right);
        if (balance2 == 1) {
          T = rotateRight(T);
        }
        else { // -1
          T.left = rotateLeft(T.left);
          T = rotateRight(T);
        }
      }
      else if (balance == -2) { // right heavy
        int balance2 = h(T.right.left) - h(T.right.right);
        if (balance2 == -1)
          T = rotateLeft(T);
        else { // 1
          T.right = rotateRight(T.right);
          T = rotateLeft(T);
        }
      }

      T.height = Math.max(h(T.left), h(T.right)) + 1;
    }

    return T;                                          // return the updated BST
  }
}
