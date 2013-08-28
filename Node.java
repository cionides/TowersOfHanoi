
import java.util.ArrayList;
import java.util.List;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author cionides
 */
public class Node {

    //int top1= 0, top2 = 0, top3 = 0;
    private List<Integer> tower1 = new ArrayList<Integer>();
    private List<Integer> tower2 = new ArrayList<Integer>();
    private List<Integer> tower3 = new ArrayList<Integer>();

    public Node(List<Integer> t1, List<Integer> t2, List<Integer> t3) {
        setT1(t1);
        setT2(t2);
        setT3(t3);
    }

    public Node(Node n) {
        tower1 = n.tower1;
        tower2 = n.tower2;
        tower3 = n.tower3;
    }

    private void setT1(List<Integer> t1) {
        tower1 = t1;
    }

    private void setT2(List<Integer> t2) {
        tower2 = t2;
    }

    private void setT3(List<Integer> t3) {
        tower3 = t3;
    }

    public List<Integer> getTower1() {
        return this.tower1;
    }

    public List<Integer> getTower2() {
        return this.tower2;
    }

    public List<Integer> getTower3() {
        return this.tower3;
    }

  
    public boolean equals(Node n) {
        if (this.tower1.equals(n.tower1) && this.tower2.equals(n.tower2) && this.tower3.equals(n.tower3)) {
            return true;
        } else {
            return false;
        }
    }
}
