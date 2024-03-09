/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package solution1;
import java.util.*;
/**
 *
 * @author Admin
 */
public class Solution1 {

    /**
     * @param args the command line arguments
     */
	public static void main(String[] args)
	{
		waterjug jugs = new waterjug();
                node solution_node = jugs.solve(new int[]{0, 0});

		if (solution_node == null) {
			System.out.println("No solution found");
		} else {
			jugs.printsolution(solution_node);
		}
	}   

}
