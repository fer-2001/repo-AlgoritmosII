one sig BinaryTree {
  root: lone Node
}

sig Node {
  left, right: lone Node,
  elem: Int 
}

sig node0 extends Node {}
sig node1 extends Node {}
sig node2 extends Node {}

// All nodes are in the tree.
fact Reachable {
  Node = BinaryTree.root.*(left + right)
}

fact Acyclic {
  all n : Node {
    // There are no directed cycles, i.e., a node is not reachable
    // from itself along one or more traversals of left or right.
    n !in n.^(left + right)
    // A node cannot have more than one parent.
    lone n.~(left + right)
    // A node cannot have another node as both its left child and
    // right child.
    no n.left & n.right
  } 
}

pred elemEqual(){
	all b , b' : Node | b.elem = b'.elem
}

pred elemOrd(){
	one b:BinaryTree, n1,n2,n3:Node| b.root = n2 and n2.left = n1 and n2.right = n3 
	and n1.elem=0 and n2.elem = 1 and n3.elem=2
}

pred elemOrdName(){
	one b:BinaryTree| b.root = node1 and node1.left= node0 and node1.right = node2
	and node0.elem = -2 and node1.elem = 0 and node2.elem=4
}



run elemEqual for 3 but exactly 3 Node
run elemOrd for 3 but exactly 3 Node
run elemOrdName for 3 but exactly 3 Node
run {} for 3 but exactly 3 Node
