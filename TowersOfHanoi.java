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
    private static List<Node> visited = new ArrayList<Node>();
    private static Node root;
    private static Node goalState;
    private static Node current;

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

    public static void checkVisited(Node c) {
        for (Node n : visited) {
            if (c.equals(n)) {
                if (searchMethod.equals("b")) {
                    current = bfsQueue.remove();
                    checkVisited(current);
                } else {
                    current = dfsStack.pop();
                    checkVisited(current);
                }
            }
        }
    }

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

    public static void bfs() {

        while (true) {
            if (bfsQueue.isEmpty()) {
                System.out.println("No solution found");
                return;
            }

            current = bfsQueue.remove();
            checkVisited(current);


            if (current.equals(goalState)) {
                printState(current);
                break;
            } else {

                printState(current);
                visited.add(current);
                successors(current);
            }
        }
    }

    public static void dfs() {
        while (true) {
            if (dfsStack.isEmpty()) {
                System.out.println("No solution found");
                return;
            }

            current = dfsStack.pop();
            checkVisited(current);
            if (current.equals(goalState)) {
                printState(current);
                break;
            } else {

                printState(current);
                visited.add(current);
                successors(current);
            }
        }
    }

    public static void successors(Node ns) {

        List<Integer> t1_a1 = new ArrayList<Integer>();
        List<Integer> t2_a1 = new ArrayList<Integer>();
        List<Integer> t3_a1 = new ArrayList<Integer>();
        t1_a1.addAll(ns.getTower1());
        t2_a1.addAll(ns.getTower2());
        t3_a1.addAll(ns.getTower3());

        List<Integer> t1_b1 = new ArrayList<Integer>();
        List<Integer> t2_b1 = new ArrayList<Integer>();
        List<Integer> t3_b1 = new ArrayList<Integer>();
        t1_b1.addAll(ns.getTower1());
        t2_b1.addAll(ns.getTower2());
        t3_b1.addAll(ns.getTower3());

        List<Integer> t1_a2 = new ArrayList<Integer>();
        List<Integer> t2_a2 = new ArrayList<Integer>();
        List<Integer> t3_a2 = new ArrayList<Integer>();
        t1_a2.addAll(ns.getTower1());
        t2_a2.addAll(ns.getTower2());
        t3_a2.addAll(ns.getTower3());

        List<Integer> t1_b2 = new ArrayList<Integer>();
        List<Integer> t2_b2 = new ArrayList<Integer>();
        List<Integer> t3_b2 = new ArrayList<Integer>();
        t1_b2.addAll(ns.getTower1());
        t2_b2.addAll(ns.getTower2());
        t3_b2.addAll(ns.getTower3());

        List<Integer> t1_a3 = new ArrayList<Integer>();
        List<Integer> t2_a3 = new ArrayList<Integer>();
        List<Integer> t3_a3 = new ArrayList<Integer>();
        t1_a3.addAll(ns.getTower1());
        t2_a3.addAll(ns.getTower2());
        t3_a3.addAll(ns.getTower3());

        List<Integer> t1_b3 = new ArrayList<Integer>();
        List<Integer> t2_b3 = new ArrayList<Integer>();
        List<Integer> t3_b3 = new ArrayList<Integer>();
        t1_b3.addAll(ns.getTower1());
        t2_b3.addAll(ns.getTower2());
        t3_b3.addAll(ns.getTower3());

        Node n1_a = new Node(t1_a1, t2_a1, t3_a1);
        Node n1_b = new Node(t1_b1, t2_b1, t3_b1);
        Node n2_a = new Node(t1_a2, t2_a2, t3_a2);
        Node n2_b = new Node(t1_b2, t2_b2, t3_b2);
        Node n3_a = new Node(t1_a3, t2_a3, t3_a3);
        Node n3_b = new Node(t1_b3, t2_b3, t3_b3);


        int top1 = 0, top2 = 0, top3 = 0;
        if (!ns.getTower1().isEmpty()) {
            top1 = ns.getTower1().remove(ns.getTower1().size() - 1);
            ns.getTower1().add(top1);
        }
        if (!ns.getTower2().isEmpty()) {
            top2 = ns.getTower2().remove(ns.getTower2().size() - 1);
            ns.getTower2().add(top2);
        }
        if (!ns.getTower3().isEmpty()) {
            top3 = ns.getTower3().remove(ns.getTower3().size() - 1);
            ns.getTower3().add(top3);
        }


        if (top1 > 0) {
            tower1Successors(n1_a, n1_b, top1, top2, top3);
        }
        if (top2 > 0) {
            tower2Successors(n2_a, n2_b, top1, top2, top3);
        }

        if (top3 > 0) {
            tower3Successors(n3_a, n3_b, top1, top2, top3);
        }
    }

    public static void tower1Successors(Node n1_a, Node n1_b, int top1, int top2, int top3) {
        if ((top1 > 0 && top1 < top2 && top1 < top3) || (top1 > 0 && ((top2 == 0) && (top3 == 0)))
                || (top1 > 0 && ((top2 == 0) && (top1 < top3)))
                || (top1 > 0 && ((top1 < top2) && (top3 == 0)))) {

            //First successor
            n1_a.getTower2().add(n1_a.getTower1().remove(n1_a.getTower1().size() - 1));
            
            if (searchMethod.equals("b")) {
                bfsQueue.add(n1_a);
            } else {
                dfsStack.push(n1_a);
            }
            //Second successor
            n1_b.getTower3().add(n1_b.getTower1().remove(n1_b.getTower1().size() - 1));
            
            if (searchMethod.equals("b")) {
                bfsQueue.add(n1_b);
            } else {
                dfsStack.push(n1_b);
            }

        } else if ((top1 > 0 && (top1 < top2)) || (top1 > 0 && (top2 == 0))) {
            //Successor
            n1_a.getTower2().add(n1_a.getTower1().remove(n1_a.getTower1().size() - 1));
           
            if (searchMethod.equals("b")) {
                bfsQueue.add(n1_a);
            } else {
                dfsStack.push(n1_a);
            }

        } else if ((top1 > 0 && (top1 < top3)) || (top1 > 0 && (top3 == 0))) {
            //Successor
            n1_a.getTower3().add(n1_a.getTower1().remove(n1_a.getTower1().size() - 1));
            
            if (searchMethod.equals("b")) {
                bfsQueue.add(n1_a);
            } else {
                dfsStack.push(n1_a);
            }
        }

    }

    public static void tower2Successors(Node n2_a, Node n2_b, int top1, int top2, int top3) {
        if ((top2 > 0 && (top2 < top1) && (top2 < top3)) || (top2 > 0 && (top1 == 0) && (top3 == 0))
                || (top2 > 0 && (top1 == 0) && (top2 < top3))
                || (top2 > 0 && (top2 < top1) && (top3 == 0))) {

            //First Successor
            n2_a.getTower1().add(n2_a.getTower2().remove(n2_a.getTower2().size() - 1));

            if (searchMethod.equals("b")) {
                bfsQueue.add(n2_a);
            } else {
                dfsStack.push(n2_a);
            }

            //Second Successor
            n2_b.getTower3().add(n2_b.getTower2().remove(n2_b.getTower2().size() - 1));

            if (searchMethod.equals("b")) {
                bfsQueue.add(n2_b);
            } else {
                dfsStack.push(n2_b);
            }

        } else if ((top2 > 0 && (top2 < top1)) || (top1 > 0 && (top1 == 0))) {
            //Successor
            n2_a.getTower1().add(n2_a.getTower2().remove(n2_a.getTower2().size() - 1));

            if (searchMethod.equals("b")) {
                bfsQueue.add(n2_a);
            } else {
                dfsStack.push(n2_a);
            }

        } else if ((top2 > 0 && (top2 < top3)) || (top2 > 0 && (top3 == 0))) {
            //Successor
            n2_a.getTower3().add(n2_a.getTower2().remove(n2_a.getTower2().size() - 1));

            if (searchMethod.equals("b")) {
                bfsQueue.add(n2_a);
            } else {
                dfsStack.push(n2_a);
            }
        }
    }

    public static void tower3Successors(Node n3_a, Node n3_b, int top1, int top2, int top3) {
        if ((top3 > 0 && (top3 < top1) && (top3 < top2)) || (top3 > 0 && (top1 == 0) && (top2 == 0))
                || (top3 > 0 && (top1 == 0) && (top3 < top2))
                || (top3 > 0 && (top3 < top1) && (top2 == 0))) {

            //First Successor
            n3_a.getTower1().add(n3_a.getTower3().remove(n3_a.getTower3().size() - 1));

            if (searchMethod.equals("b")) {
                bfsQueue.add(n3_a);
            } else {
                dfsStack.push(n3_a);
            }
            //Second Successor
            n3_b.getTower2().add(n3_b.getTower3().remove(n3_b.getTower3().size() - 1));

            if (searchMethod.equals("b")) {
                bfsQueue.add(n3_b);
            } else {
                dfsStack.push(n3_b);
            }

        } else if ((top3 > 0 && (top3 < top1)) || (top3 > 0 && (top1 == 0))) {
            //Successor
            n3_a.getTower1().add(n3_a.getTower3().remove(n3_a.getTower3().size() - 1));

            if (searchMethod.equals("b")) {
                bfsQueue.add(n3_a);
            } else {
                dfsStack.push(n3_a);
            }
        } else if ((top3 > 0 && (top3 < top2)) || (top3 > 0 && (top2 == 0))) {
            //Successor
            n3_a.getTower2().add(n3_a.getTower3().remove(n3_a.getTower3().size() - 1));

            if (searchMethod.equals("b")) {
                bfsQueue.add(n3_a);
            } else {
                dfsStack.push(n3_a);
            }
        }
    }

    public static void printState(Node n) {

        if (n.equals(goalState)) {
            System.out.println("Solution Found!");
            System.out.println(n.getTower1() + " " + n.getTower2() + " " + n.getTower3());
        } else {
            System.out.println(n.getTower1() + " " + n.getTower2() + " " + n.getTower3());
        }
    }
}
