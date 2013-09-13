
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author cionides
 */
public class Node {
    
    private List<Integer> tower1 = new ArrayList<Integer>();
    private List<Integer> tower2 = new ArrayList<Integer>();
    private List<Integer> tower3 = new ArrayList<Integer>();
    public boolean visited;
    
    //CTOR
    public Node(List<Integer> t1, List<Integer> t2, List<Integer> t3) {
        setTower1(t1);
        setTower2(t2);
        setTower3(t3);
        visited = false;
    }
    
    //COPY CTOR
    public Node(Node n) {
        setTower1(n.getTower1());
        setTower2(n.getTower2());
        setTower3(n.getTower3());
    }
    
    private void setTower1(List<Integer> t1) {
        tower1.addAll(t1);
        
    }
    
    private void setTower2(List<Integer> t2) {
        tower2.addAll(t2);
        
    }
    
    private void setTower3(List<Integer> t3) {
        tower3.addAll(t3);
        
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
    
    public int topOfTowerN(int tNum) {
        int top = 0;
        List<Integer> tempTower = new ArrayList<Integer>();
        
        if (tNum == 1) {
            tempTower.addAll(this.tower1);
            if (tempTower.size() >= 1) {
                top = tempTower.remove(tempTower.size() - 1);
            }
        }
        if (tNum == 2) {
            tempTower.addAll(this.tower2);
            if (tempTower.size() >= 1) {
                top = tempTower.remove(tempTower.size() - 1);
            }
        }
        if (tNum == 3) {
            tempTower.addAll(this.tower3);
            if (tempTower.size() >= 1) {
                top = tempTower.remove(tempTower.size() - 1);
            }
        }
        return top;
    }
    
    public boolean equals(Node n) {
        if (this.tower1.equals(n.tower1) && this.tower2.equals(n.tower2) && this.tower3.equals(n.tower3)) {
            return true;
        } else {
            return false;
        }
    }
}
