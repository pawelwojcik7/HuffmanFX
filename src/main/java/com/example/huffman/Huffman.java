package com.example.huffman;


import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

import static java.util.Objects.requireNonNull;

public class Huffman {

    private Node root;
    private final String text;
    private Map<Character, Integer> charFrequencies;
    private final Map<Character, String> huffmanCodes;
    public TextManipulator tm;

    public Huffman(TextManipulator textManipulator) {
        this.text = textManipulator.slowo;
        this.tm = textManipulator;
        fillCharFrequenciesMap();
        huffmanCodes = new HashMap<>();
    }

    private void fillCharFrequenciesMap() {
        charFrequencies = new HashMap<>();
        for (char character : text.toCharArray()) {
            Integer integer = charFrequencies.get(character);
            charFrequencies.put(character, integer != null ? integer + 1 : 1);
        }
    }


    public String encode() {
        Queue<Node> queue = new PriorityQueue<>();
        charFrequencies.forEach((character, frequency) ->
                queue.add(new Leaf(character, frequency))
        );
        while (queue.size() > 1) {
            queue.add(new Node(queue.poll(), requireNonNull(queue.poll())));
        }
        generateHuffmanCodes(root = queue.poll(), "");
        return getEncodedText();
    }

    private void generateHuffmanCodes(Node node, String code) {
        if (node instanceof Leaf) {
            huffmanCodes.put(((Leaf) node).getCharacter(), code);
            for (int i = 0; i < tm.uniqueList.size(); i++) {

                String s =String.valueOf(((Leaf) node).getCharacter());
                if (tm.tab[i][1].equals(s)) {
                    tm.tab[i][4] = String.valueOf(code);

                }
            }
            return;
        }
        generateHuffmanCodes(node.getLeftNode(), code.concat("0"));
        generateHuffmanCodes(node.getRightNode(), code.concat("1"));
    }

    private String getEncodedText() {
        StringBuilder sb = new StringBuilder();
        for (char character : text.toCharArray()) {
            sb.append(huffmanCodes.get(character));
        }
        return sb.toString();
    }


    public String decode(String encodedText) {
        StringBuilder sb = new StringBuilder();
        Node current = root;
        for (char character : encodedText.toCharArray()) {
            current = character == '0' ? current.getLeftNode() : current.getRightNode();
            if (current instanceof Leaf) {
                sb.append(((Leaf) current).getCharacter());
                current = root;
            }
        }
        return sb.toString();
    }


    public void printCodes() {
        huffmanCodes.forEach((character, code) ->
                System.out.println(character + ": " + code)
        );
    }

}