package playground.builder;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

public class Pub<E>  {
   private String ans = "d";

   public void oi() {
       ans = "1";
   }

   protected class Inner {
       public void hi() {
           System.out.println(Pub.this.ans);
       }
   }
}
