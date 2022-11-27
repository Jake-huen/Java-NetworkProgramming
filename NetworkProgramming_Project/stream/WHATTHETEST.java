package stream;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;


public class WHATTHETEST {

    public static void main(String[] args) {

        long startTime = System.nanoTime();

        Double average = 0.0;
        Data_min[] min_data = new Data_min[11];
        Data_max[] max_data = new Data_max[11];
        for (int i =0; i < min_data.length;i++){
            min_data[i]= new Data_min(0,0,0,0,0);
            max_data[i]= new Data_max(0,0,0,0,0);
        }
        TaskMinMax tmm0 = new TaskMinMax(min_data[0], max_data[0], 1);
        TaskMinMax tmm1 = new TaskMinMax(min_data[1], max_data[1], 6);
        TaskMinMax tmm2 = new TaskMinMax(min_data[2], max_data[2], 11);
        TaskMinMax tmm3 = new TaskMinMax(min_data[3], max_data[3], 16);
        TaskMinMax tmm4 = new TaskMinMax(min_data[4], max_data[4], 21);
        TaskMinMax tmm5 = new TaskMinMax(min_data[5], max_data[5], 26);
        TaskMinMax tmm6 = new TaskMinMax(min_data[6], max_data[6], 31);
        TaskMinMax tmm7 = new TaskMinMax(min_data[7], max_data[7], 36);
        TaskMinMax tmm8 = new TaskMinMax(min_data[8], max_data[8], 41);
        TaskMinMax tmm9 = new TaskMinMax(min_data[9], max_data[9], 46);

        tmm0.start();
        tmm1.start();
        tmm2.start();
        tmm3.start();
        tmm4.start();
        tmm5.start();
        tmm6.start();
        tmm7.start();
        tmm8.start();
        tmm9.start();


        try {
            tmm0.join();
            tmm1.join();
            tmm2.join();
            tmm3.join();
            tmm4.join();
            tmm5.join();
            tmm6.join();
            tmm7.join();
            tmm8.join();
            tmm9.join();


        } catch (InterruptedException e) {
            System.err.println(e);
        }

        for (int i = 0; i < min_data.length - 1; i++) {
            min_data[10].sum+= min_data[i].sum;
            min_data[10].count+=min_data[i].count;
            min_data[10].file_no+=min_data[i].file_no;

            if (max_data[i].max > max_data[10].max) {
                max_data[10].max = max_data[i].max;
                max_data[10].c = max_data[i].c;
                max_data[10].d = max_data[i].d;
                max_data[10].row = max_data[i].row;
                max_data[10].col = max_data[i].col;
            }
            if (min_data[i].min < min_data[10].min) {
                min_data[10].min = min_data[i].min;
                min_data[10].c = min_data[i].c;
                min_data[10].d = min_data[i].d;
                min_data[10].row = min_data[i].row;
                min_data[10].col = min_data[i].col;
            }
        }
        max_data[10].sum = min_data[10].sum;
        max_data[10].count = min_data[10].count;
        max_data[10].file_no = min_data[10].file_no;

        System.out.println("c : " + max_data[10].c + " d : " +  max_data[10].d + " row : " + max_data[10].row + " col : " +  max_data[10].col + " max : " +  max_data[10].max);
        System.out.println("c : " + min_data[10].c + " d : " + min_data[10].d + " row : " + min_data[10].row + " col : " + min_data[10].col + " min : " + min_data[10].min);

        System.out.println("Number of missing Files :"+min_data[10].file_no);
        average = min_data[10].sum / min_data[10].count;
        //average = (double) Math.round((average * 10000) / 10000.0);
        System.out.println("avg : " + String.format("%.4f",average));
        long endTime = System.nanoTime();
        System.out.println((endTime - startTime) / 1e9);
    }

    public static class Data_min {

        double min;
        int c, d, row, col;
        double sum=0;
        int count=0;
        int file_no=0;

        Data_min(double min, int c, int d, int row, int col){
            this.min=min;
            this.c = c;
            this.d = d;
            this.col = col;
            this.row = row;

        }
    }

    public static class Data_max {

        double max;
        int c, d, row, col;
        double sum=0;
        int count=0;
        int file_no=0;

        Data_max(double max, int c, int d, int row, int col){
            this.max=max;
            this.c = c;
            this.d = d;
            this.col = col;
            this.row = row;

        }

    }


    static class TaskMinMax extends Thread {
        Data_min min_data;
        Data_max max_data;
        int num;

        TaskMinMax(Data_min min_data , Data_max max_data, int num) {
            this.min_data = min_data;
            this.max_data = max_data;
            this.num = num;
        }

        @Override
        public void run() {

            for (int i = num ; i < num + 5; i++) {
                for (int j = 1; j <= 60; j++) {
                    String path;
                    path = "file%20(c=" + j + ")_(d=" + i + ").txt";

                    try {
                        URL u = new URL("https://home.konkuk.ac.kr/~leehw/Site/nptest/2021/MidTerM/" + path);
                        try (InputStream ins = u.openStream()) {
                            InputStreamReader isr = new InputStreamReader(ins);
                            BufferedReader buf = new BufferedReader(isr);
                            String line;
                            int row_num = 1;
                            while ((line = buf.readLine()) != null) {
                                String[] splitString = line.split("\t");
                                double[] temp = new double[splitString.length];

                                for (int n = 0; n < splitString.length; n++) {
                                    temp[n] = Double.parseDouble(splitString[n]);
                                    min_data.sum+=temp[n];
                                    max_data.sum+=temp[n];
                                    min_data.count++;
                                    max_data.count++;
                                    if (temp[n] > max_data.max) {
                                        max_data.max = temp[n];
                                        max_data.c = j;
                                        max_data.d = i;
                                        max_data.row = row_num;
                                        max_data.col = n + 1;
                                    }
                                    if (temp[n] < min_data.min) {
                                        min_data.min = temp[n];
                                        min_data.c = j;
                                        min_data.d = i;
                                        min_data.row = row_num;
                                        min_data.col = n + 1;
                                    }
                                }

                                row_num++;
                            }
                        }
                    } catch (MalformedURLException ex) {
                        //System.err.println(ex);
                    } catch (IOException ex) {
                        path = "file%20{c=" + j + "}_{d=" + i + "}.txt";
                        try {
                            URL u = new URL("https://home.konkuk.ac.kr/~leehw/Site/nptest/2021/MidTerM/" + path);
                            try (InputStream ins = u.openStream()) {
                                InputStreamReader isr = new InputStreamReader(ins);
                                BufferedReader buf = new BufferedReader(isr);
                                String line;
                                int row_num = 1;
                                while ((line = buf.readLine()) != null) {
                                    String[] splitString = line.split("\t");
                                    double[] temp = new double[splitString.length];

                                    for (int n = 0; n < splitString.length; n++) {
                                        temp[n] = Double.parseDouble(splitString[n]);
                                        min_data.sum+=temp[n];
                                        max_data.sum+=temp[n];
                                        min_data.count++;
                                        max_data.count++;
                                        if (temp[n] > max_data.max) {
                                            max_data.max = temp[n];
                                            max_data.c = j;
                                            max_data.d = i;
                                            max_data.row = row_num;
                                            max_data.col = n + 1;
                                        }
                                        if (temp[n] < min_data.min) {
                                            min_data.min = temp[n];
                                            min_data.c = j;
                                            min_data.d = i;
                                            min_data.row = row_num;
                                            min_data.col = n + 1;
                                        }
                                    }

                                    row_num++;
                                }
                            }
                        } catch (MalformedURLException ex2) {
                            //System.err.println(ex2);
                        } catch (IOException e) {
                            min_data.file_no++;
                            max_data.file_no++;
                            //System.err.println(e);
                        }
                        //System.err.println(ex);
                    }



                }
            }
        }
    }
}
