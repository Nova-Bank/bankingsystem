package com.github.novabank.infrastructure;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents recent transactions returned to the API with Trie-based search.
 */
public class RecentTransactions {

    private final List<String> transactions;
    private final Trie trie = new Trie();

    public RecentTransactions(List<String> transactions) {
        this.transactions = transactions;
        for (String t : transactions) {
            trie.insert(t);
        }
    }

    public List<String> getTransactions() {
        return transactions;
    }

    /**
     * Search transactions by prefix using Trie.
     */
    public List<String> search(String prefix) {
        return trie.startsWith(prefix);
    }

    private static class Trie {

        private final Node root = new Node();

        private static class Node {
            Node[] children = new Node[256]; // ASCII coverage, adjust if needed
            boolean isEnd;
        }

        public void insert(String word) {
            Node curr = root;
            for (char c : word.toCharArray()) {
                if (curr.children[c] == null) {
                    curr.children[c] = new Node();
                }
                curr = curr.children[c];
            }
            curr.isEnd = true;
        }

        public List<String> startsWith(String prefix) {
            List<String> results = new ArrayList<>();
            Node curr = root;

            for (char c : prefix.toCharArray()) {
                if (curr.children[c] == null) {
                    return results; // nothing found
                }
                curr = curr.children[c];
            }

            collect(curr, new StringBuilder(prefix), results);
            return results;
        }

        private void collect(Node node, StringBuilder prefix, List<String> results) {
            if (node.isEnd) {
                results.add(prefix.toString());
            }

            for (int i = 0; i < node.children.length; i++) {
                if (node.children[i] != null) {
                    prefix.append((char) i);
                    collect(node.children[i], prefix, results);
                    prefix.setLength(prefix.length() - 1);
                }
            }
        }
    }
}
