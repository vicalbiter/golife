public class TestGL {
    
    public void toBooleanTest(int num, int dim) {
        GLUtilities utilities = new GLUtilities();
        boolean[] test = utilities.toBoolean(num, dim);
        for (int i = 0; i < test.length; i++) {
            if(test[i]) { System.out.print("1"); }
            else { System.out.print("0"); }
        }   
    }
    
    public static void main (String[] args) {
        TestGL testgl = new TestGL();
        testgl.toBooleanTest(7, 5);
    }
}