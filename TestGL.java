public class TestGL {
    
    private GLUtilities utilities;
    
    public TestGL() {
        utilities = new GLUtilities();
    }
    
    public void toBooleanTest(int num, int dim) {
        boolean[] test = utilities.toBoolean(num, dim);
        for (int i = 0; i < test.length; i++) {
            if(test[i]) { System.out.print("1"); }
            else { System.out.print("0"); }
        }   
    }
    
    public void equalGridTest(int n) {
        GLGrid grid = new GLGrid(n, utilities.interestingGrid(n, 2, 0));
        GLGrid othergrid = new GLGrid(n, utilities.interestingGrid(n, 2, 1));
        if (utilities.equalGrid(n, grid, grid)) { System.out.println("Test 1: Correct"); }
        if (!utilities.equalGrid(n, grid, othergrid)) { System.out.println("Test 2: Correct"); }
    }
    
    public static void main (String[] args) {
        TestGL testgl = new TestGL();
        //testgl.toBooleanTest(7, 5);
        testgl.equalGridTest(6);
    }
}