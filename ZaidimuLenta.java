
package tictactoe;

public class ZaidimuLenta {
    
    private int[][] lenta;
    
   public ZaidimuLenta(int y){
       this.lenta = new int[y][y];
   } 
   
   public int[][] getLenta(){
       return lenta;
   }
}

