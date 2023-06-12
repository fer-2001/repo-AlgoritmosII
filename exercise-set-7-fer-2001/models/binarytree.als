one sig BinaryTree {
  root: lone Node
}

sig Node {
  left, right: lone Node,
  elem: Int 
}

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

pred equals{
	all n,m:Node | n.elem = m.elem 
}

run equals for 3 but exactly 3 Node


pred creciente{
	all n: Node, m: n.^left | n.elem < m.elem
	all n: Node, m: n.^right | n.elem < m.elem
}

pred creciente2{
	one b:BinaryTree, n1,n2,n3 : Node | b.root = n2 and n2.left = n1 and n2.right = n3 and n1.elem = 1
	and n2.elem = 2 and n3.elem = 7
}

sig node0 extends Node {}
sig node1 extends Node {}
sig node2 extends Node {}

pred creciente3{
	one b:BinaryTree| b.root = node1 and node1.left = node0 and node1.right = node2 and node0.elem = 1
	and node1.elem = 2 and node2.elem = 7
}

run creciente3
run creciente2 for 3 but exactly 3 Node

run creciente for 3 but exactly 3 Node



run {} for 3 but exactly 3 Node
