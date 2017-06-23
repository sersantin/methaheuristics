package Statistics;

import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Statistics {

    private static final String STATISTICSPATH = System.getProperty("user.dir") + "/src/Statistics/outputs/";
    private Double mBetter;				// Minus cost
    private Double mWorse;				// Mayor cost
    private Double mAverage;			// Cost mAverage

    public Statistics (){
        setDefaultValues();

    }

    public Statistics (ArrayList<Double> results){
        setDefaultValues();

        getBetterResult(results);
        getWorseResult(results);
        getAverageResult(results);
    }

    public void saveStatistics(String fileName, String delimiter)
            throws FileNotFoundException, IOException{

        String content = "";
        File af = new File(STATISTICSPATH + fileName);
        if(af.exists()){
            content = readFile(STATISTICSPATH, fileName);
        }

        writeFile(STATISTICSPATH, fileName,
                content + getmBetter() + delimiter + getmWorse() + delimiter + getmAverage());
    }

    private static String readFile(String path, String fileName)
            throws FileNotFoundException, IOException {

        String string;
        String finalString = "";

        FileReader f = new FileReader(path + fileName);
        if(f != null){
            BufferedReader b = new BufferedReader(f);

            while((string = b.readLine()) != null) {
                finalString += string + "\n";
            }

            b.close();
        }

        return finalString;
    }

    private void writeFile(String path, String fileName, String data) {

        FileWriter file = null;
        try {

            file = new FileWriter(path + fileName);

            file.write(data);

            file.close();

        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    private void setDefaultValues(){
        mBetter = Double.MAX_VALUE;
        mWorse = Double.MIN_VALUE;
        mAverage = 0.0;
    }

    private void getWorseResult(ArrayList<Double> results) {
        for(int i = 0; i < results.size(); i++){
            if(getmWorse() < results.get(i)){
                setmWorse(results.get(i));
            }
        }
    }

    private void getAverageResult(ArrayList<Double> results) {
        for(int i = 0; i < results.size(); i++){
            setmAverage(getmAverage() + results.get(i));
        }

        setmAverage(getmAverage() / results.size());
    }


    private void getBetterResult(ArrayList<Double> results) {
        for(int i = 0; i < results.size(); i++){
            if(getmBetter() > results.get(i)){
                setmBetter(results.get(i));
            }
        }
    }


    public void showStatistics(){
        System.out.println("Better value: " + getmBetter());
        System.out.println("Wortse value: " + getmWorse());
        System.out.println("Average value: " + getmAverage());
    }

    public Double getmBetter() {
        return mBetter;
    }


    public void setmBetter(Double mBetter) {
        this.mBetter = mBetter;
    }


    public Double getmWorse() {
        return mWorse;
    }


    public void setmWorse(Double mWorse) {
        this.mWorse = mWorse;
    }

    public Double getmAverage() {
        return mAverage;
    }

    public void setmAverage(Double mAverage) {
        this.mAverage = mAverage;
    }
}