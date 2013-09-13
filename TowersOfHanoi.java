
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

public class TowersOfHanoi {
    
    private static int numDisks;
    private static String searchMethod;
    private static Queue<Node> bfsQueue = new LinkedList<Node>();
    private static Stack<Node> dfsStack = new Stack<Node>();
    private static List<Node> listOfStates = new ArrayList<Node>();
    private static Node root;
    private static Node goalState;
    private static Node current;
    
    /**
     * Initializes the start state and sets the goal state; Begins the process
     * with appropriate search method.
     *
     * @param args
     */
    public static void main(String[] args) {
        
        getUserInput();
        initialize(numDisks);
        if (searchMethod.equals("b")) {
            bfs();
        }
        if (searchMethod.equals("d")) {
            dfs();
        }
    }
    
    /**
     * Sets the values for number of rings and search method.
     */
    public static void getUserInput() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            System.out.println("Enter the number of disks");
            numDisks = Integer.parseInt(br.readLine());
            
            System.out.println("Enter b or d");
            searchMethod = br.readLine();
            br.close();
        } catch (Exception e) {
        }
    }
    
    /**
     * Sets up the goal state and the initial state of the program.
     *
     * @param nd is the node to be initialized
     */
    public static void initialize(int nd) {
        List<Integer> tower1 = new ArrayList<Integer>();
        List<Integer> tower2 = new ArrayList<Integer>();
        List<Integer> tower3 = new ArrayList<Integer>();
        
        for (int i = nd; i > 0; i--) {
            tower1.add(i);
        }
        
        goalState = new Node(tower3, tower2, tower1);
        root = new Node(tower1, tower2, tower3);
        
        if (searchMethod.equals("b")) {
            bfsQueue.add(root);
        } else {
            dfsStack.push(root);
        }
    }
    
    /**
     * This method will perform Breadth First Search on the towers. It will
     * generate the successors on a need basis. i.e- it will not generate the
     * whole search space, but a level at a time until the goal state is
     * discovered.
     */
    public static void bfs() {
        
        while (true) {
            if (bfsQueue.isEmpty()) {
                
                System.out.println("No solution found");
                return;
            } else {
                current = bfsQueue.remove();
                if (current.equals(goalState)) {
                    printState(current);
                    break;
                } else {
                    printState(current);
                    successors(current);
                    listOfStates.add(current);
                    
                }
            }
        }
        
    }
    
    /**
     * This method performs a Depth First Search on the towers and generates the
     * necessary successors for each node.
     */
    public static void dfs() {
        while (true) {
            if (dfsStack.isEmpty()) {
                System.out.println("No solution found");
                return;
            }
            current = dfsStack.pop();
            if (current.equals(goalState)) {
                printState(current);
                break;
            } else {
                printState(current);
                successors(current);
                listOfStates.add(current);
            }
        }
    }
    
    /**
     * Generates the successors for any node passed in.
     *
     * @param ns
     */
    public static void successors(Node ns) {
        
        int top1 = ns.topOfTowerN(1);
        int top2 = ns.topOfTowerN(2);
        int top3 = ns.topOfTowerN(3);
        
        Node n1 = new Node(ns);
        Node n2 = new Node(ns);
        Node n3 = new Node(ns);
        Node n4 = new Node(ns);
        Node n5 = new Node(ns);
        Node n6 = new Node(ns);
        
        //each towers successors are generated(if there are any)
        if (top1 > 0) {
            tower1Successors(n1, n2, top1, top2, top3);
            
        }
        if (top2 > 0) {
            tower2Successors(n3, n4, top1, top2, top3);
            
        }
        
        if (top3 > 0) {
            tower3Successors(n5, n6, top1, top2, top3);
            
        }
    }
    
    /*
     * There are three scenarios:
     * 1. the top disk on tower 1 can be placed on tower 2 or 3 (creating two successors)
     * 2. the top disk on tower 1 can be placed only on tower 2 (creaing one successor)
     * 3. the top disk on tower 1 can be placed only on tower 3 (creating one successor)
     * Obviously, it may not be able to be placed on any, and no "successors" will be created
     */
    public static void tower1Successors(Node n1, Node n2, int top1, int top2, int top3) {
        
        if ((top1 < top2 && top1 < top3)
            || ((top2 == 0) && (top3 == 0))
            || ((top2 == 0) && (top1 < top3))
            || ((top1 < top2) && (top3 == 0))) {
            
            boolean duplicate = false;
            //First successor
            n1.getTower2().add(n1.getTower1().remove(n1.getTower1().size() - 1));
            
            for (Node n : listOfStates) {
                if (n.equals(n1)) {
                    duplicate = true;
                }
            }
            if (duplicate == false) {
                listOfStates.add(n1);
                if (searchMethod.equals("b")) {
                    bfsQueue.add(n1);
                } else {
                    dfsStack.push(n1);
                }
            }
            
            duplicate = false;
            //Second successor
            n2.getTower3().add(n2.getTower1().remove(n2.getTower1().size() - 1));
            for (Node n : listOfStates) {
                if (n.equals(n2)) {
                    duplicate = true;
                }
            }
            if (duplicate == false) {
                listOfStates.add(n2);
                if (searchMethod.equals("b")) {
                    bfsQueue.add(n2);
                } else {
                    dfsStack.push(n2);
                }
            }
            
            
        } else if ((top1 < top2) || (top2 == 0)) {
            boolean duplicate = false;
            //Successor
            n1.getTower2().add(n1.getTower1().remove(n1.getTower1().size() - 1));
            for (Node n : listOfStates) {
                if (n.equals(n1)) {
                    duplicate = true;
                }
            }
            if (duplicate == false) {
                listOfStates.add(n1);
                if (searchMethod.equals("b")) {
                    bfsQueue.add(n1);
                } else {
                    dfsStack.push(n1);
                }
            }
            
        } else if ((top1 < top3) || (top3 == 0)) {
            boolean duplicate = false;
            //Successor
            n1.getTower3().add(n1.getTower1().remove(n1.getTower1().size() - 1));
            for (Node n : listOfStates) {
                if (n.equals(n1)) {
                    duplicate = true;
                }
            }
            if (duplicate == false) {
                listOfStates.add(n1);
                if (searchMethod.equals("b")) {
                    bfsQueue.add(n1);
                } else {
                    dfsStack.push(n1);
                }
            }
        }
        
    }
    
    public static void tower2Successors(Node n3, Node n4, int top1, int top2, int top3) {
        if ((top2 < top1 && top2 < top3)
            || (top1 == 0) && (top3 == 0)
            || (top1 == 0) && (top2 < top3)
            || (top2 < top1) && (top3 == 0)) {
            
            boolean duplicate = false;
            //First Successor
            n3.getTower1().add(n3.getTower2().remove(n3.getTower2().size() - 1));
            for (Node n : listOfStates) {
                if (n.equals(n3)) {
                    duplicate = true;
                }
            }
            if (duplicate == false) {
                listOfStates.add(n3);
                if (searchMethod.equals("b")) {
                    bfsQueue.add(n3);
                } else {
                    dfsStack.push(n3);
                }
            }
            
            duplicate = false;
            
            //Second Successor
            n4.getTower3().add(n4.getTower2().remove(n4.getTower2().size() - 1));
            for (Node n : listOfStates) {
                if (n.equals(n4)) {
                    duplicate = true;
                }
            }
            if (duplicate == false) {
                listOfStates.add(n4);
                if (searchMethod.equals("b")) {
                    bfsQueue.add(n4);
                } else {
                    dfsStack.push(n4);
                }
            }
            
        } else if ((top2 < top1) || (top1 == 0)) {
            boolean duplicate = false;
            //Successor
            n3.getTower1().add(n3.getTower2().remove(n3.getTower2().size() - 1));
            for (Node n : listOfStates) {
                if (n.equals(n3)) {
                    duplicate = true;
                }
            }
            if (duplicate == false) {
                listOfStates.add(n3);
                if (searchMethod.equals("b")) {
                    bfsQueue.add(n3);
                } else {
                    dfsStack.push(n3);
                }
            }
            
        } else if ((top2 < top3) || (top3 == 0)) {
            boolean duplicate = false;
            //Successor
            n3.getTower3().add(n3.getTower2().remove(n3.getTower2().size() - 1));
            for (Node n : listOfStates) {
                if (n.equals(n3)) {
                    duplicate = true;
                }
            }
            if (duplicate == false) {
                listOfStates.add(n3);
                if (searchMethod.equals("b")) {
                    bfsQueue.add(n3);
                } else {
                    dfsStack.push(n3);
                }
            }
            
        }
    }
    
    public static void tower3Successors(Node n5, Node n6, int top1, int top2, int top3) {
        if ((top3 < top1) && (top3 < top2)
            || (top1 == 0) && (top2 == 0)
            || (top1 == 0) && (top3 < top2)
            || (top3 < top1) && (top2 == 0)) {
            
            boolean duplicate = false;
            //First Successor
            n5.getTower1().add(n5.getTower3().remove(n5.getTower3().size() - 1));
            
            for (Node n : listOfStates) {
                if (n.equals(n5)) {
                    duplicate = true;
                }
            }
            if (duplicate == false) {
                listOfStates.add(n5);
                if (searchMethod.equals("b")) {
                    bfsQueue.add(n5);
                } else {
                    dfsStack.push(n5);
                }
            }
            
            duplicate = false;
            //Second Successor
            n6.getTower2().add(n6.getTower3().remove(n6.getTower3().size() - 1));
            
            for (Node n : listOfStates) {
                if (n.equals(n6)) {
                    duplicate = true;
                }
            }
            if (duplicate == false) {
                listOfStates.add(n6);
                if (searchMethod.equals("b")) {
                    bfsQueue.add(n6);
                } else {
                    dfsStack.push(n6);
                }
            }
            
            
        } else if ((top3 < top1) || (top1 == 0)) {
            boolean duplicate = false;
            //Successor
            n5.getTower1().add(n5.getTower3().remove(n5.getTower3().size() - 1));
            
            for (Node n : listOfStates) {
                if (n.equals(n5)) {
                    duplicate = true;
                }
            }
            if (duplicate == false) {
                listOfStates.add(n5);
                if (searchMethod.equals("b")) {
                    bfsQueue.add(n5);
                } else {
                    dfsStack.push(n5);
                }
            }
            
        } else if ((top3 < top2) || (top2 == 0)) {
            boolean duplicate = false;
            //Successor
            n5.getTower2().add(n5.getTower3().remove(n5.getTower3().size() - 1));
            for (Node n : listOfStates) {
                if (n.equals(n5)) {
                    duplicate = true;
                }
            }
            if (duplicate == false) {
                listOfStates.add(n5);
                if (searchMethod.equals("b")) {
                    bfsQueue.add(n5);
                } else {
                    dfsStack.push(n5);
                }
            }
            
        }
    }
    
    /**
     * Prints the intermediate steps of the search until the goal state is reached.
     *
     * @param n is the node whose state will be printed
     */
    public static void printState(Node n) {
        
        if (n.equals(goalState)) {
            System.out.println("Solution Found!");
            System.out.println(n.getTower1() + " " + n.getTower2() + " " + n.getTower3());
        } else {
            System.out.println(n.getTower1() + " " + n.getTower2() + " " + n.getTower3());
        }
    }
}
