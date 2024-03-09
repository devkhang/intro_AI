/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package solution;
import java.util.*;
/**
 *
 * @author Admin
 */
public class waterjug {
    public Queue<node> fontier = new LinkedList<>();
    public ArrayList<int[]> explore = new ArrayList<>();
    public node solve(){
        int[] init_state = {0,0};
        node current_node = new node(init_state,null,null);
        fontier.add(current_node);
        System.out.print("Đang xét: ");
        System.out.print("                                ");
        System.out.print("Open Set: ");
        System.out.print("                                ");
        System.out.println("Close Set:");
        while(true){
            if(this.fontier.size()==0){
                return null;
            }
            current_node = this.fontier.remove();
            System.out.print("(" + current_node.state[0] + "," + current_node.state[1] + ")");
            System.out.print("                                ");
            if(!inexplore(current_node.state)){
                explore.add(current_node.state);
            }
            Action[] actions = this.getFeasibleAction(current_node.state);
            for(Action action:actions){
                node child = this.getchild(current_node, action);
                if(!infontier(child)&&!inexplore(child.state)){
                    if(this.isGoal(child)){
                        return child;
                    }
                    this.fontier.add(child);
                }
            }
            System.out.print(frontierToString());
            System.out.print("                                ");
            System.out.println(exploredToString());
        }
    }
    public boolean inexplore(int[] state){
        for(int i=0;i<this.explore.size();i++){
            int[]cur = this.explore.get(i);
            if(cur[0]==state[0]&& cur[1]==state[1]){
                return true;
            }
        }
        return false;
    }
    public boolean infontier(node node){
        for(node curr: this.fontier){
            if(node.state[0]==curr.state[0]&&node.state[1]==node.state[1]){
                return true;
            }
        }
        return false;
    }
    public Action[] getFeasibleAction(int[] state){
        int x = state[0];
        int y =  state[1];
        ArrayList<Action> actionList = new ArrayList<Action>();
        if(x==0){
            actionList.add(new Action("tap","x",4));
        }
        if(y==0){
            actionList.add(new Action("tap","y",3));
        }
        if(x==4){
            actionList.add(new Action("x","ground",4));
        }
        if(y==3){
            actionList.add(new Action("y","ground",3));
        }
        if((x>0)&&(3-y)>0){
            actionList.add(new Action("x","y",Math.min(x,(3-y))));
        }
        if((y>0)&&(4-x)>0){
            actionList.add(new Action("y","x",Math.min(y, (4-x))));
        }
        Action[] arrayAction = new Action[actionList.size()];
        return actionList.toArray(arrayAction);
    }
    public node getchild(node cur,Action action){
        int[] new_state = new int[2];
        new_state[0]=cur.state[0];
        new_state[1]=cur.state[1];
        
        if(action!=null){
            if(action.from == "x"){
                new_state[0]=cur.state[0] - action.quantity;
            }
            if(action.from == "y"){
                new_state[1]=cur.state[1]- action.quantity;
            }
            if(action.to=="x"){
                new_state[0] = cur.state[0] + action.quantity;
            }
            if(action.to=="y"){
                new_state[1] = cur.state[1] + action.quantity;
            }
        }
        node new_node = new node(new_state,cur,action);
        return new_node;
    }
    public boolean isGoal(node node){
        return (node.state[0]==2 || node.state[1]==2);
    }
    public void printsolution(node solution){
        ArrayList<String> step = new ArrayList<String>();
        while(solution.parent!=null){
            step.add("fill "+solution.action.to+" with water from "+solution.action.from+"(ie:"+solution.action.quantity+")"+" New state: " + solution.state [0] + "," + solution.state[1]);
            solution = solution.parent;
        }
        System.out.println("step:");
        step.add("4 liters jug has "+solution.state[0] + " and 3 liters jug has :"+solution.state[1]);
        while(step.size()>0){
            String result = step.remove(step.size()-1);
            System.out.println(result);
        }
    }
        public String frontierToString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (node n : fontier) {
            sb.append("(").append(n.state[0]).append(",").append(n.state[1]).append("), ");
        }
        if (!fontier.isEmpty()) {
            sb.delete(sb.length() - 2, sb.length());
        }
        sb.append("]");
        return sb.toString();
    }

    public String exploredToString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int[] state : explore) {
            sb.append("(").append(state[0]).append(",").append(state[1]).append("), ");
        }
        if (!explore.isEmpty()) {
            sb.delete(sb.length() - 2, sb.length());
        }
        sb.append("]");
        return sb.toString();
    }
}
