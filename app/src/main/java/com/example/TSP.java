package com.example;

public class TSP {
    String megaString = "";
    static int[][] matrix = {{0, 4, 3, 2, 5}, {6, 0, 11, 17, 23}, {1, 2, 0, 5, 45}, {27, 32, 6, 0, 56}, {1, 2, 6, 7, 0}};
    int best = 10000;

    public static void main(String[] args) {

        int[] arr = {1, 2, 3, 4};
        String str = "1234";
        int n = str.length();
        TSP tsp = new TSP();
        tsp.calculatePerm(str, 0, n - 1);
        int weight = calculateWeight(matrix, 1, 3);
        //System.out.println(weight);

    }


    public String swap(String a, int i, int j) {
        String[] routes = new String[10];
        char temp;
        char[] charArray = a.toCharArray();
        temp = charArray[i];
        charArray[i] = charArray[j];
        charArray[j] = temp;
        for (int k = 0; k <= i; k++) {
            routes[k] = String.valueOf(charArray);
        }
        return String.valueOf(charArray);

    }

    private long calcFactorial(int factorial) {

        for(int i=factorial-1; i>0; i--){
            factorial = i * factorial;
        }
        return factorial;
    }


    public void calculatePerm(String str, int l, int r) {
        String[] perm = new String[r];
        if (l == r) {
            megaString = megaString + "0"+ str + "0, ";
            if (megaString.length() == 4*calcFactorial(str.length()) + str.length() * (calcFactorial(str.length()))) {
                System.out.println(megaString);
                String[] allpaths = new String[(int)calcFactorial(str.length())];
                allpaths = megaString.split(",");
                optimalRoute(allpaths, matrix, best);
            } else {
            }
        }

         else {
            for (int i = l; i <= r; i++) {
                str = swap(str, l, i);
                calculatePerm(str, l + 1, r);
                str = swap(str, l, i);

                }
            }

        }



    public static int calculateWeight(int[][] matrix, int destination, int origin) {
        int weight = matrix[destination][origin];
        return weight;
    }

    public static void optimalRoute(String[] allpaths, int[][] matrix, int best) {
        int routeno = 0;
        for (int i = 0; i < allpaths.length-1; i++) {
            int sum = 0;
            String temp[] = allpaths[i].trim().split("");
            for (int j = 0; j < temp.length-1; j++) {
                int weight = calculateWeight(matrix, Integer.parseInt(temp[j]), Integer.parseInt(temp[j + 1]));
                if (weight == 0){

                }
                else{
                    sum = sum + weight;
                }
            }

            if (sum < best){
                best = sum;
                routeno = i;
            }
        }
        System.out.println(best + "+");
        System.out.println(allpaths[routeno]);
    }


}
