
import com.bonplan.services.RecommendationService;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Achraf
 */
public class Test {
    public static void main(String[] args) {
        RecommendationService rs=new RecommendationService();
        rs.getallrec().forEach(a->System.out.println(a));
    }
    
}
