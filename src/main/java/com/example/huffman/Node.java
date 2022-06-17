package com.example.huffman;




public class Node implements Comparable<Node> {

    private final int frequency;
    private Node leftNode;
    private Node rightNode;

    public Node(Node leftNode, Node rightNode) {
        this.leftNode = leftNode;
        this.rightNode = rightNode;
        this.frequency = leftNode.getFrequency() + rightNode.getFrequency();
    }

    public Node(int frequency) {
        this.frequency=frequency;
    }

    @Override
    public int compareTo(Node node) {
        return Integer.compare(frequency, node.getFrequency());
    }

    public int getFrequency()
    {
        return this.frequency;
    }

    public Node getLeftNode() {
        return this.leftNode;
    }

    public Node getRightNode() {
        return this.rightNode;
    }
}
