package com.company;

public class LinkedList implements NodeList {
    private ListItem root = null;

    public LinkedList(ListItem root) {
        this.root = root;
    }

    @Override
    public ListItem getRoot() {
        return this.root;
    }

    @Override
    public boolean addItem(ListItem newItem) {
        if(this.root == null) {
            // The list is empty, so this item becomes the head of the list
            this.root = newItem;
            return true;
        }

        ListItem currentItem = this.root;
        while (currentItem != null) {
            int comparison = (currentItem.compareTo(newItem));
            if(comparison < 0) {
                // newItem is greater, move right if possible
                if(currentItem.getNext() != null) {
                    currentItem = currentItem.getNext();
                } else {
                    // there is no next, so insert at the end of the list
                    currentItem.setNext(newItem);
                    newItem.setPrevious(currentItem);
                    return true;
                }
            } else if (comparison > 0) {
                // newItem is less, insert before
                if(currentItem.getPrevious() != null) {
                    // in our setNext/ setPrevious we are returning the next/ previous reference variable, thus we
                    // are able to chain them together by doing another setPrevious/ setNext etc.
                    currentItem.getPrevious().setNext(newItem);
                    newItem.setPrevious(currentItem.getPrevious());
                    newItem.setNext(currentItem);
                    currentItem.setPrevious(newItem);
                } else {
                    // the node with a previous being null is the root
                    newItem.setNext(this.root);
                    this.root.setPrevious(newItem);
                    this.root = newItem;
                }
                return true;
            } else {
                // newItem and currentNode are equal
                System.out.println(newItem.getData() + " is already present, not added");
                return false;
            }
        }
        return false;
    }

    @Override
    public boolean removeItem(ListItem item) {
        if(item != null) {
            System.out.println("Deleting item " + item.getData());
        }
        ListItem currentItem = this.root;
        while (currentItem != null) {
            int comparison = currentItem.compareTo(item);
            if(comparison == 0) {
                // found the item to delete
                if(currentItem == this.root) {
                    this.root = currentItem.getNext();
                } else {
                    currentItem.getPrevious().setNext(currentItem.getNext());
                    if(currentItem.getNext() != null) {
                        currentItem.getNext().setPrevious(currentItem.getPrevious());
                    }
                }
                return true;
            } else if(comparison < 0) {
                currentItem = currentItem.getNext();
            } else { // comparison > 0
                // we are at an item greater than one to be deleted, so item is not in the list
                return false;
            }
        }
        // we have reached the end of the list
        // without finding the item
        return false;
    }

    @Override
    public void traverse(ListItem root) {
        if(root == null) {
            System.out.println("The list is empty");
        } else {
            while (root != null) {
                System.out.println("Contents are " + root.getData());
                root = root.getNext();
            }
        }
    }


    //    public void addTo(LinkedList linkedList, String data) {
//        Node newNode = new Node(data);
//        newNode.setNext(null);
//
//        if(linkedList.head != null) {
//            Node last = linkedList.head;
//
//            while (last.getNext() != null) {
//                last = (Node) last.getNext();
//            }
//
//            last.setNext(newNode);
//            newNode.setPrevious(last);
//        } else {
//            linkedList.head = newNode;
//        }
//    }
//
//    public void printList(LinkedList linkedList) {
//        Node currentNode = linkedList.head;
//        System.out.println("Printing LinkedList");
//        while (currentNode != null) {
//            System.out.println("\tContents are: " + currentNode.getData() + " Contents previous is " +
//                    currentNode.getPrevious() + " Contents next is " + currentNode.getNext());
//            currentNode = (Node) currentNode.getNext();
//        }
//    }

}
