package tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Trie {
    static class TrieNode {
        Map<Character, TrieNode> children;
        boolean endOfString;

        public TrieNode() {
            children = new HashMap<>();
            endOfString = false;
        }
    }
    private TrieNode root;
    public Trie() {
        root = new TrieNode();
    }
    public void insert(String word) {
        TrieNode current = root;
        for (int i=0; i<word.length(); i++) {
            char ch = word.charAt(i);
            TrieNode node = current.children.get(ch);
            if (node == null) {
                node = new TrieNode();
                current.children.put(ch, node);
            }
            current = node;
        }
        current.endOfString = true;
    }
    public boolean search(String word) {
        TrieNode currentNode = root;
        for (int i =0; i<word.length(); i++) {
            char ch = word.charAt(i);
            TrieNode node = currentNode.children.get(ch);
            if (node == null) {
                return false;
            }
            currentNode = node;
        }
        return currentNode.endOfString;
    }
    public boolean delete(String word) {
        if (!search(word)) {
            return false;
        }
        delete(root, word, 0);
        return true;
    }

    private boolean delete(TrieNode node, String word, int index) {
        if (index == word.length()) {
            node.endOfString = false;
            return node.children.isEmpty();
        }

        char ch = word.charAt(index);
        TrieNode childNode = node.children.get(ch);
        boolean shouldDeleteChild = delete(childNode, word, index + 1);
        if (shouldDeleteChild) {
            node.children.remove(ch);
            return node.children.isEmpty() && !node.endOfString;
        }
        return false;
    }
    public List<String> getAllWords() {
        List<String> words = new ArrayList<>();
        StringBuilder currentWord = new StringBuilder();
        getAllWords(root, currentWord, words);
        return words;
    }

    private void getAllWords(TrieNode current, StringBuilder currentWord, List<String> words) {
        if (current.endOfString) {
            words.add(currentWord.toString());
        }

        for (char ch : current.children.keySet()) {
            TrieNode childNode = current.children.get(ch);
            currentWord.append(ch);
            getAllWords(childNode, currentWord, words);
            currentWord.deleteCharAt(currentWord.length() - 1);
        }
    }

    public List<String> findWordsWithPrefix(String prefix, int limit) {
        List<String> words = new ArrayList<>();
        TrieNode node = root;
        // Перейти до останнього вузла префікса
        for (char c : prefix.toCharArray()) {
            if (node.children.containsKey(c)) {
                node = node.children.get(c);
            } else {
                return words; // Префікс не знайдено
            }
        }
        findAllWordsWithPrefix(node, prefix, words);
        return words.subList(0, Math.min(limit, words.size()));
    }
    private void findAllWordsWithPrefix(TrieNode node, String currentPrefix, List<String> words) {
        if (node.endOfString) {
            words.add(currentPrefix);
        }
        for (char c : node.children.keySet()) {
            findAllWordsWithPrefix(node.children.get(c), currentPrefix + c, words);
        }
    }
}
