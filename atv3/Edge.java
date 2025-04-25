package atv3;

import java.util.Objects;

class Edge {
   int u;
   int v;
   double weight;

   public Edge(int var1, int var2) {
      this(var1, var2, 1.0);
   }

   public Edge(int var1, int var2, double weight) {
      this.u = var1;
      this.v = var2;
      this.weight = weight;
   }

   public boolean equals(Object var1) {
      if (this == var1) {
         return true;
      } else if (var1 != null && this.getClass() == var1.getClass()) {
         Edge var2 = (Edge)var1;
         return this.u == var2.u && this.v == var2.v;
      } else {
         return false;
      }
   }

   public int hashCode() {
      return Objects.hash(new Object[]{this.u, this.v});
   }
}