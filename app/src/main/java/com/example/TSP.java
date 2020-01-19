package com.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import static org.apache.http.protocol.HTTP.USER_AGENT;

public class TSP {
    static String[] destinations = {"BLR", "FCO", "CDG", "LHR", "SVO"};
    String megaString = "";
    static int[][] matrix = new int[destinations.length][destinations.length];
    static String finalRouteNumbers;
    static String[] finalRoute;

    int best = 100000000;

    public static void main(String[] args) {
        for(int i=0; i< destinations.length; i++){
            for(int j=0; j<destinations.length; j++){
                System.out.println(destinations[i] + " " + destinations[j]);
                try {
                    int weight = populateMatrix(destinations[i], destinations[j], "2020-06-15");
                    matrix[i][j] = weight;


                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }

        String str = "";
        for(int i = 1; i<destinations.length; i++){
            str = str + i;
        }
        int n = str.length();
        TSP tsp = new TSP();
        tsp.calculatePerm(str, 0, n - 1);
        System.out.println(finalRouteNumbers.substring(6, 7));

        for(int i =2; i<=finalRouteNumbers.length(); i++){
            int index = Integer.parseInt(finalRouteNumbers.substring(i-1, i));
            finalRoute[i] = destinations[index];

        }

        }
        //System.out.println(weight);


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
                    sum = 1000000;
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
        finalRouteNumbers = allpaths[routeno];
        System.out.println(finalRouteNumbers);
    }

    public static int populateMatrix(String origin, String destination, String date) throws IOException{
        int price = 0;
        if( origin.equals(destination)){
            price = 0;
            System.out.println(price);
        }
        else {

            URL obj = new URL("http://localhost:3000/"+origin+"/"+destination+"/"+date);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("User-Agent", USER_AGENT);
            int responseCode = con.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) { // success
                BufferedReader in = new BufferedReader(new InputStreamReader(
                        con.getInputStream()));
                String inputLine;
                StringBuffer response = new StringBuffer();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                String wholeJson = response.toString();
                if (!wholeJson.equals(null)){
                    price = Integer.parseInt(wholeJson);
                    System.out.println(price);

                }




                // print result
//            System.out.println(response.toString());
            } else {
                System.out.println("GET request not worked");
            }
        }
        return price;
    }


}
